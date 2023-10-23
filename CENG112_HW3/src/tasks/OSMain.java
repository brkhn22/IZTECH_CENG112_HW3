package tasks;

import java.time.LocalDateTime;
import fileIO.FileIO;
import implementations.*;
import interfaces.*;

public class OSMain {

	
	private StackInterface<Task>taskStack;
	private PriorityQueueInterface<PriorityTask> waitingPriorityLine;
	private SortedListInterface<BurstTask> pileOfWaitingBurstTime;
	public OSMain() {
		// get tasks that will be managed by the OS.
		taskStack = new FileIO().readFile();
		waitingPriorityLine = new PriorityQueue<>();
		pileOfWaitingBurstTime = new LinkedSortedList<>();
	}
	public static void main(String[] args) {
		OSMain os = new OSMain();
		
		// create sorted task list that sorts the tasks in order to their arrival time.
		SortedListInterface<Task> taskList = new LinkedSortedList<>();
		while(!os.taskStack.isEmpty()) 
			taskList.add(os.taskStack.pop());
		// display sorted task list
		os.displayTaskList(taskList);
		// add tasks to priority queue as PriorityTask
		for( int i = 1; i <= taskList.getLength();i++) {
			Task t = taskList.getEntry(i);
			PriorityTask pt = new PriorityTask(t.getTaskType(), t.getBurstTime(),t.getArrivaldate());
			os.waitingPriorityLine.add(pt);
		}
		// add tasks to pile of waiting burst time as BurstTask.
		for( int i = 1; i <= taskList.getLength();i++) {
			Task t = taskList.getEntry(i);
			BurstTask bt = new BurstTask(t.getTaskType(), t.getBurstTime(),t.getArrivaldate());
			os.pileOfWaitingBurstTime.add(bt);
		}
		// display waiting priority line and pile of waiting burst time respect to the execution time.
		os.displayWaitingLine(os.waitingPriorityLine);
		os.displaySortedLine(os.pileOfWaitingBurstTime);
		
		// execute waiting priority line and pile of waiting burst time.
		os.executeWaitingLine(os.waitingPriorityLine);
		os.executePileOfBurstTime(os.pileOfWaitingBurstTime);
		
		// clear the task list and task stack.
		taskList.clear();
		os.taskStack.clear();
	}
	
	private void displayTaskList(SortedListInterface<Task> list) {
		System.out.println("********Displaying Tasks********");
		for( int i = 1; i <= list.getLength();i++) {
			Task t = list.getEntry(i);
			LocalDateTime ltd = t.getArrivaldate();
			String time = String.format("%d:%02d", ltd.getHour(), ltd.getMinute());
			String date = ltd.getMonth()+ "/"+ltd.getDayOfMonth()+"/"+ltd.getYear();
			System.out.println(t.getTaskType()+" has "+t.getBurstTime()+" burst time on "
			+date+" "+time);
		}
		System.out.println("********************************");
	}
	private void displayWaitingLine(PriorityQueueInterface<PriorityTask> queue) {
		int executionTimer = 0;
		System.out.println("********Waiting Line********");
		for(int i = 1; i <= queue.getSize();i++)
		{
			
			Task t = queue.getEntry(i);
			LocalDateTime ltd = t.getArrivaldate();
			String time = String.format("%d:%02d", ltd.getHour(), ltd.getMinute());
			String date = ltd.getMonth()+ "/"+ltd.getDayOfMonth()+"/"+ltd.getYear();
			int burstTime = t.getBurstTime();
			System.out.println("The task named "+t.getTaskType()+" will be executed at "+
			executionTimer+", and has "+burstTime+" burst time on "+
			date+" "+time);
			// add burst time to executionTimer.
			executionTimer+= burstTime;
		}
		System.out.println("****************************");
	}
	private void executeWaitingLine(PriorityQueueInterface<PriorityTask> queue) {
		boolean running = !queue.isEmpty();
		int executionTimer = 0;
		int executedTaskCounter = 0;
		System.out.println("Execution of waiting line has started.");
		while(running) {
			if(executionTimer <= 0) {
				PriorityTask currentTask = queue.dequeue();
				// gets non-terminated task.
				while(currentTask.isTerminated() && !queue.isEmpty())
					currentTask = queue.dequeue();
				if(currentTask != null) {
					executionTimer = currentTask.getBurstTime();
					executedTaskCounter++;
				}
				if((executedTaskCounter % 5) == 0)
					displayRemainingWaitingLine(queue);
			}
			
			executionTimer--;
			running = !(executionTimer <= 0 && queue.isEmpty());
		}
		System.out.println("All tasks have been executed in the waiting line.");
	}
	private void displayRemainingWaitingLine(PriorityQueueInterface<PriorityTask> queue) {
		if(queue.isEmpty()) {
			System.out.println("There is no task left in the priority queue.");
			return;
		}
		System.out.println("********Remaining Waiting Line********");
		for(int i = 1; i <= queue.getSize();i++)
		{
			
			Task t = queue.getEntry(i);
			LocalDateTime ltd = t.getArrivaldate();
			String time = String.format("%d:%02d", ltd.getHour(), ltd.getMinute());
			String date = ltd.getMonth()+ "/"+ltd.getDayOfMonth()+"/"+ltd.getYear();
			int burstTime = t.getBurstTime();
			System.out.println("The task named "+t.getTaskType()+" has "+burstTime+" burst time on "+
			date+" "+time);
		}
		System.out.println("****************************");
	}
	private void displayRemainingSortedLine(SortedListInterface<BurstTask> list) {
		if(list.isEmpty()) {
			System.out.println("There is no task left in the pile of waiting burst time.");
			return;
		}
		System.out.println("********Remaining Sorted List********");
		for(int i = 1; i <= list.getLength();i++)
		{
			Task t = list.getEntry(i);
			LocalDateTime ltd = t.getArrivaldate();
			String time = String.format("%d:%02d", ltd.getHour(), ltd.getMinute());
			String date = ltd.getMonth()+ "/"+ltd.getDayOfMonth()+"/"+ltd.getYear();
			int burstTime = t.getBurstTime();
			System.out.println("The task named "+t.getTaskType()+" has "+burstTime+
					" burst time on "+date+" "+time);
		}
		System.out.println("****************************");
	}
	private void displaySortedLine(SortedListInterface<BurstTask> list) {
		int executionTimer = 0;
		System.out.println("********Sorted List**********");
		for(int i = 1; i <= list.getLength();i++)
		{
			Task t = list.getEntry(i);
			LocalDateTime ltd = t.getArrivaldate();
			String time = String.format("%d:%02d", ltd.getHour(), ltd.getMinute());
			String date = ltd.getMonth()+ "/"+ltd.getDayOfMonth()+"/"+ltd.getYear();
			int burstTime = t.getBurstTime();
			System.out.println("The task named "+t.getTaskType()+" will be executed at "+
			executionTimer+", and has "+burstTime+" burst time on "+
			date+" "+time);
			// add burst time to executionTimer.
			executionTimer+= burstTime;
		}
		System.out.println("****************************");
	}
	private void executePileOfBurstTime(SortedListInterface<BurstTask> list) {
		boolean running = !list.isEmpty();
		int executionTimer = 0;
		int executedTaskCounter = 0;
		System.out.println("Execution of pile of waiting burst time has started.");
		while(running) {
			if(executionTimer <= 0) {
				BurstTask currentTask = list.remove(1);
				if(currentTask != null) {
					executionTimer = currentTask.getBurstTime();
					executedTaskCounter++;
				}
				// display remaining task list
				if((executedTaskCounter % 5) == 0)
					displayRemainingSortedLine(list);
			}
			
			executionTimer--;
			running = !(executionTimer <= 0 && list.isEmpty());
		}
		System.out.println("All tasks have been executed in the pile of burst time.");
	}

}
