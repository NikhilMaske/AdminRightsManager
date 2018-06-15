package com.adminrightsmanager.taskmanager;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.ObjectInputStream.GetField;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.adminrightsmanager.csvreader.DateFunctions;

public class ExecutorUtil {
	protected static SimpleDateFormat sdfExecutor = new SimpleDateFormat("dd-MM-yyyy");
		
	public static String execute(Task task) {
		String mPresentDate = DateFunctions.getPresentDateInFormatddmmyyyy();
		StringBuffer resultString = new StringBuffer();

		if (task != null) {

			String commandString = task.getCommandString();
			System.out.println("cmd::::"+commandString);
			String consoleString = "";

			try {
				
				Process p = Runtime.getRuntime().exec(commandString);

				BufferedReader logReader =
						new BufferedReader(new InputStreamReader(p.getInputStream()));

				BufferedReader errorReader =
						new BufferedReader(new InputStreamReader(p.getErrorStream()));
				
				// read the output from the command
				resultString.append("Execution logs :{");
				while ((consoleString = logReader.readLine()) != null) {
					resultString.append(consoleString).append("\n");
				}
				resultString.append("}\n");
				// read any errors from the attempted command
				resultString.append("Errors encountered :{");
				while ((consoleString = errorReader.readLine()) != null) {
					resultString.append(consoleString).append("\n");
				}
				resultString.append("}\n");
				if(commandString.contains("add")) {//to add
					task.setGrantedDate(mPresentDate);
				}
				if(commandString.contains("remove")) { // to remove
					task.setRevokedDate(mPresentDate);
				}
			} catch (Throwable e) {
				e.printStackTrace();
			}
		}

		return resultString.toString();
	}
}
