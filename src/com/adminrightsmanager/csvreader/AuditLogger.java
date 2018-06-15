package com.adminrightsmanager.csvreader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class AuditLogger {
	BufferedWriter bw = null;
	Date now;
	private static String mLogsDirectory = ".\\logs\\";
	private static File dir = new File(mLogsDirectory);
	private static String auditLogFileNameAppender = "-Logs.dat";
	private static String currentDateInDDMMMYYY =
			DateFunctions.getPresentDateInFormatDDMMMYYY();// e.g. 15FEB2018;

	public AuditLogger() {
		now = new Date();
		String generatedFileName = currentDateInDDMMMYYY + auditLogFileNameAppender;
		//System.out.println(generatedFileName);
		File f = new File(mLogsDirectory + generatedFileName);
		try {
			if (!f.exists() | !dir.exists()) {

				dir.mkdir();
				f.createNewFile();
				bw = new BufferedWriter(
						new FileWriter(mLogsDirectory + generatedFileName, true));

			} else {

				bw = new BufferedWriter(
						new FileWriter(mLogsDirectory + generatedFileName, true));

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void appendLogs(String dataToAppend) {

		if (!dataToAppend.isEmpty()) {
			try {
				// APPEND MODE SET HERE

				bw.write("[Date:" + now + "] :\n" + dataToAppend);
				bw.newLine();
				bw.flush();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} finally { // always close the file
				if (bw != null)
					try {
						bw.close();
					} catch (IOException ioe2) {
						// just ignore it
					}
			} // end try/catch/finally

		} // end test()
	}

} // end class