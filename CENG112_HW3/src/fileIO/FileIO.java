package fileIO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDateTime;

import implementations.ArrayStack;
import interfaces.StackInterface;
import tasks.Task;

public class FileIO {

	 String file = "./sources/tasks.csv";
	 BufferedReader reader = null;
	 String line = "";
	 public StackInterface<Task> readFile() {
		 StackInterface<Task> taskStack = new ArrayStack<>();
	 try {
		 reader = new BufferedReader(new FileReader(file));
		 while((line = reader.readLine()) != null) {

			 String[] row = line.split(",");
			 String dataType = row[0];
			 
			 int burstTime = Integer.parseInt(row[1]);

			 String[] date = row[2].split("/");
			 String[] time = row[3].split(":");
			 LocalDateTime ldt = LocalDateTime.of(Integer.parseInt(date[2]),
					 Integer.parseInt(date[1]),Integer.parseInt(date[0]),
					 Integer.parseInt(time[0]), Integer.parseInt(time[1]));

			 Task t = new Task(dataType, burstTime, ldt);
			 taskStack.push(t);
		 }
	 }catch(Exception e) {
		 System.out.println(e.toString());
	 }
	 return taskStack;
	 }
	 
}
