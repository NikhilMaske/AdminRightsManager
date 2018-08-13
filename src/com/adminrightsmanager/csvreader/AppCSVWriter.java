package com.adminrightsmanager.csvreader;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import com.adminrightsmanager.app.App;
import com.adminrightsmanager.taskmanager.Task;

/**
 * The Class AppCSVWriter.
 */
public class AppCSVWriter {

	/** The file. */
	File file = null;

	/** The bw. */
	BufferedWriter bw = null;

	/** The fw. */
	FileWriter fw = null;

	/**
	 * Instantiates a new app CSV writer.
	 *
	 * @param fileName
	 *            the file name
	 */
	public AppCSVWriter(String fileName) {
		file = new File(fileName);
		try {
			// if file doesnt exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			// true = append file
			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);
			bw.write(App.FILE_HEADER);
			bw.close();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Merge test and processed csv.
	 *
	 * @param pickupFileName
	 *            the pickup file name
	 * @param processedFileName
	 *            the processed file name
	 * @throws FileNotFoundException
	 *             the file not found exception
	 * @throws Exception
	 *             the exception
	 */
	public void mergeTestAndProcessedCsv(String pickupFileName, String processedFileName)
			throws FileNotFoundException, Exception {
		Vector<Task> pickUpVector = new Vector<Task>();
		Vector<Task> processedVector = new Vector<Task>();
		String recordData = null;
		// if file doesnt exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}

		// true = append file
		fw = new FileWriter(file.getAbsoluteFile(), true);
		bw = new BufferedWriter(fw);

		pickUpVector = new AppCSVReader(pickupFileName).readRawDataFromCSV();
		processedVector = new AppCSVReader(processedFileName).readRawDataFromCSV();
		try {
			for (int i = 0; i < pickUpVector.size(); i++) {

				for (int j = 0; j < processedVector.size(); j++) {
					System.out.println(
							"Processed::::" + processedVector.get(j).getHostName() + " , "
									+ processedVector.get(j).getIpAddress() + " , "
									+ processedVector.get(j).getStartDate() + " , "
									+ processedVector.get(j).getEndDate() + " , "
									+ processedVector.get(j).getGrantedDate() + " , "
									+ processedVector.get(j).getRevokedDate() + " , "
									+ processedVector.get(j).getxOS());
					if (pickUpVector.get(i).getHostName().toString()
							.equals(processedVector.get(j).getHostName().toString())) {

						if (!(processedVector.get(j).getRevokedDate().toString()
								.isEmpty())) {
							pickUpVector.get(i).setRevokedDate(
									processedVector.get(j).getRevokedDate());
							break;
						} else if (!(processedVector.get(j).getGrantedDate().toString()
								.isEmpty())) {
							pickUpVector.get(i).setGrantedDate(
									processedVector.get(j).getGrantedDate());

							break;
						}

					}

				}
				System.out.println("Pickup::::" + pickUpVector.get(i).getHostName()
						+ " , " + pickUpVector.get(i).getIpAddress() + " , "
						+ pickUpVector.get(i).getStartDate() + " , "
						+ pickUpVector.get(i).getEndDate() + " , "
						+ pickUpVector.get(i).getGrantedDate() + " , "
						+ pickUpVector.get(i).getRevokedDate() + " , "
						+ pickUpVector.get(i).getxOS());
			}

			for (int i = 0; i < pickUpVector.size(); i++) {
				recordData = pickUpVector.get(i).getHostName().toString() + ","
						+ pickUpVector.get(i).getIpAddress().toString() + ","
						+ pickUpVector.get(i).getStartDate().toString() + ","
						+ pickUpVector.get(i).getEndDate().toString() + ","
						+ pickUpVector.get(i).getGrantedDate().toString() + ","
						+ pickUpVector.get(i).getRevokedDate().toString() + ","
						+ pickUpVector.get(i).getxOS().toString() + "\n";
				bw.write(recordData);
			}

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

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 * @throws FileNotFoundException
	 *             the file not found exception
	 * @throws Exception
	 *             the exception
	 */
	public static void main(String[] args) throws FileNotFoundException, Exception {
		AppCSVWriter wrt = new AppCSVWriter(".\\testnew.csv");
		wrt.mergeTestAndProcessedCsv(".\\test.csv", ".\\processedCsv.csv");
	}

}