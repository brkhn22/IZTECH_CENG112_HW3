package tasks;

import java.time.LocalDateTime;

public class BurstTask extends Task {

	public BurstTask(String taskType, int burstTime, LocalDateTime arrivalDate) {
		super(taskType, burstTime, arrivalDate);
	}
	
	@Override
	public int compareTo(Task o) {
		int burstTimeOffSet = getBurstTime() - o.getBurstTime();
		if(burstTimeOffSet == 0) {
			return compareDate(o);
		}else
			return burstTimeOffSet;
	}
	
}
