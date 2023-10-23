package tasks;

import java.time.LocalDateTime;

public class PriorityTask extends Task {

	private boolean terminated = false;
	public PriorityTask(String taskType, int burstTime, LocalDateTime arrivalDate) {
		super(taskType, burstTime, arrivalDate);
	}
	
	@Override
	public int compareTo(Task o) {
		// other's priority level - this tasks's priority level.
		int priorityOffSet = o.getPriorityLevel() - getPriorityLevel();
		if(priorityOffSet == 0) {
			return compareDate(o);
		}else
			return priorityOffSet;
	}

	public boolean isTerminated() {
		return terminated;
	}

	public void setTerminated(boolean terminated) {
		this.terminated = terminated;
	}
	
	
}
