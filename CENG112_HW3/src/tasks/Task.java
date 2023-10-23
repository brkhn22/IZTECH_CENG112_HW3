package tasks;

import java.time.LocalDateTime;

public class Task implements interfaces.Comparable<Task> {
	
	protected String taskType;
	protected int burstTime;
	protected LocalDateTime arrivalDate;


	public Task(String taskType, int burstTime, LocalDateTime arrivalDate) {
		this.taskType = taskType;
		this.burstTime = burstTime;
		this.arrivalDate = arrivalDate;
	}


	@Override
	public int compareTo(Task o) {
		return compareDate(o);
	}
	
	public int compareDate(Task o) {
		LocalDateTime comparison = o.getArrivaldate();
		int yearOffSet = arrivalDate.getYear() - comparison.getYear();
		if(yearOffSet == 0) {
			int dayOffSet = arrivalDate.getDayOfYear() - comparison.getDayOfYear();
			if(dayOffSet == 0) {
				int hourOffSet = arrivalDate.getHour() - comparison.getHour();
				if(hourOffSet == 0) {
					int minuteOffSet = arrivalDate.getMinute() - comparison.getMinute();
					return minuteOffSet;
				}else
					return hourOffSet;
			}else
				return dayOffSet;
		}else
			return yearOffSet;
	}

	public int getPriorityLevel() {
		int level;
		switch(taskType.toLowerCase()) {
			case "security management":
				level = 6;
				break;
			case "process management":
				level = 5;
				break;
			case "memory management":
				level = 4;
				break;
			case "user management":
				level = 3;
				break;
			case "device management":
				level = 2;
				break;
			case "file management":
				level = 1;
				break;
			default:
				level = 0;
				break;
		}
		return level;
	}
	public String getTaskType() {
		return taskType;
	}


	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}


	public int getBurstTime() {
		return burstTime;
	}


	public void setBurstTime(int burstTime) {
		this.burstTime = burstTime;
	}


	public LocalDateTime getArrivaldate() {
		return arrivalDate;
	}


	public void setArrivaldate(LocalDateTime arrivaldate) {
		this.arrivalDate = arrivaldate;
	}

	
	
}
