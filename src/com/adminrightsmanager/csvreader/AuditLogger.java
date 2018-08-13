package com.adminrightsmanager.csvreader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * The Class AuditLogger.
 */
public class AuditLogger {

	/** The bw. */
	BufferedWriter bw = null;

	/** The now. */
	Date now;

	/** The m logs directory. */
	private static String mLogsDirectory = ".\\logs\\";

	/** The dir. */
	private static File dir = new File(mLogsDirectory);

	/** The audit log file name appender. */
	private static String auditLogFileNameAppender = "-Logs.dat";

	/** The current date in DDMMMYYY. */
	private static String currentDateInDDMMMYYY =
			DateFunctions.getPresentDateInFormatDDMMMYYY();// e.g. 15FEB2018;

	/**
	 * Instantiates a new audit logger.
	 */
	public AuditLogger() {
		now = new Date();
		String generatedFileName = currentDateInDDMMMYYY + auditLogFileNameAppender;
		// System.out.println(generatedFileName);
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

	/**
	 * Append logs.
	 *
	 * @param dataToAppend
	 *            the data to append
	 */
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