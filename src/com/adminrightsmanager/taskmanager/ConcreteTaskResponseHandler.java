package com.adminrightsmanager.taskmanager;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.adminrightsmanager.app.App;
import com.adminrightsmanager.csvreader.AppCSVWriter;
import com.adminrightsmanager.csvreader.AuditLogger;

public class ConcreteTaskResponseHandler implements TaskResponseHandler {

	BufferedWriter bw = null;
	FileWriter fw = null;
	File processedFile = null;

	public ConcreteTaskResponseHandler() throws IOException {
		processedFile = new File(App.ProcessedFILENAME);
		// if file doesnt exists, then create it
		if (!processedFile.exists()) {
			processedFile.createNewFile();
		}

		// true = append file
		fw = new FileWriter(processedFile.getAbsoluteFile(), true);
		bw = new BufferedWriter(fw);
		bw.write(App.FILE_HEADER);
		bw.close();
		fw.close();
	}
	public void extractDataAndInsertIntoCsv(Task task) throws IOException {

		String recordData = task.getHostName().toString() + ","
				+ task.getIpAddress().toString() + "," + task.getStartDate().toString()
				+ "," + task.getEndDate().toString() + ","
				+ task.getGrantedDate().toString() + ","
				+ task.getRevokedDate().toString() + "," + task.getxOS().toString()
				+ "\n";
		try {
			fw = new FileWriter(processedFile.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			bw.write(recordData);
			System.out.println("Done");

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}
		}

	}
	@Override
	public void handleResponse(Task task, String result) {
		
		System.out.println("Handling response for...");
		System.out.println(task.getHostName());
		System.out.println(result);
		try {
			extractDataAndInsertIntoCsv(task);
			AuditLogger auditLogger = new AuditLogger();
			auditLogger.appendLogs(result);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
