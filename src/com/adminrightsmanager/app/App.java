package com.adminrightsmanager.app;

import java.io.File;
import java.util.Vector;

import com.adminrightsmanager.csvreader.AppCSVReader;
import com.adminrightsmanager.csvreader.AppCSVWriter;
import com.adminrightsmanager.csvreader.DateFunctions;
import com.adminrightsmanager.filestructure.ArrangeFiles;
import com.adminrightsmanager.taskmanager.ConcreteTaskResponseHandler;
import com.adminrightsmanager.taskmanager.Task;
import com.adminrightsmanager.taskmanager.TaskManager;

/**
 * The Class App.
 */
public class App {

	/** The Constant ProcessedFILENAME. */
	public static final String ProcessedFILENAME = ".\\processedCsv.csv";

	/** The Constant PickUpFILENAME. */
	public static final String PickUpFILENAME = ".\\test.csv";

	/** The Constant NewPickupFILENAME. */
	public static final String NewPickupFILENAME = ".\\testnew.csv";

	/** The Constant FILE_HEADER. */
	public static final String FILE_HEADER =
			"HOSTNAME,IPADDRESS,STARTDATE,ENDDATE,GRANTED,REVOKED,OSX" + "\n";

	/** The process status. */
	public static String processStatus = "NA";

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 * @throws Exception
	 *             the exception
	 */
	public static void main(String args[]) throws Exception {
		Vector<Task> tempVector = new Vector<Task>();
		AppCSVReader csvReaderObject = new AppCSVReader(PickUpFILENAME);
		AppCSVWriter csvWrtiterObject = new AppCSVWriter(NewPickupFILENAME);
		String systemPath = System.getProperty("java.library.path");
		Boolean systemPathSetFlag = false;
		systemPathSetFlag = System.getProperty("java.library.path")
				.contentEquals(System.getProperty("user.dir"));
		if (!System.getProperty("java.library.path").toString()
				.contentEquals(System.getProperty("user.dir"))) {
			System.out.println(
					"[ System Property Not Defined to Execute Third Party Application [Possible Guess : System Variable Not Defined] ]\n");
			systemPath += ";" + System.getProperty("user.dir");
			System.setProperty("java.library.path", systemPath);

		}
		// csv reader returns vector
		ConcreteTaskResponseHandler mhandler = new ConcreteTaskResponseHandler();
		TaskManager taskManager = new TaskManager(mhandler);
		tempVector = csvReaderObject.readCSV();
		if (!tempVector.isEmpty()) {
			taskManager.addTasks(tempVector);// add all vector elements to queue

			do {
				Thread.sleep(1000);
			} while (taskManager.mTaskQueue.size() > 0);

			if (processStatus.equals("completed") && taskManager.mTaskQueue.size() <= 0) {
				csvWrtiterObject.mergeTestAndProcessedCsv(App.PickUpFILENAME,
						App.ProcessedFILENAME);
				if (!new File(PickUpFILENAME).exists()
						&& !new File(NewPickupFILENAME).exists()) {
					Thread.sleep(3000);
				}
				ArrangeFiles.moveFileIntoSpecificFolder(PickUpFILENAME.replace(".\\", ""),
						NewPickupFILENAME.replace(".\\", ""),
						DateFunctions.getPresentDateInFormatDDMMMYYY() + "Folder");
			}
		} else {
			System.out.println("there are no task to proceed with");
			Thread.interrupted();
		}

	}
}
