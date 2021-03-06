package com.adminrightsmanager.filestructure;

import java.io.File;
import java.io.IOException;

/**
 * The Class ArrangeFiles.
 */
public class ArrangeFiles {

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	public static void main(String[] args) throws IOException {

	}

	/**
	 * Move file into specific folder.
	 *
	 * @param fileName1
	 *            the file name 1
	 * @param fileName2
	 *            the file name 2
	 * @param folderName
	 *            the folder name
	 * @throws InterruptedException
	 *             the interrupted exception
	 */
	public static void moveFileIntoSpecificFolder(String fileName1, String fileName2,
			String folderName) throws InterruptedException {
		File file1 = new File(fileName1);
		File file2 = new File(fileName2);
		File folder = new File(folderName);
		if (!folder.exists()) {
			folder.mkdir();// create dir
		}
		// renaming the file and moving it to a new location

		if (file1.renameTo(new File(".\\" + folderName + "\\" + fileName1))
				&& file1.renameTo(new File(".\\" + folderName + "\\" + fileName2))) {
			// if file copied successfully then delete the original file
			file1.delete();
			file2.delete();
			// new File(App.NewPickupFILENAME).renameTo(new File (App.PickUpFILENAME));
			System.out.println("File moved successfully");
		} else {
			System.out.println("Failed to move the file");
		}
	}

}
