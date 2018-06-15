package com.adminrightsmanager.filestructure;

import java.io.File;
import java.io.IOException;

import com.adminrightsmanager.app.App;

public class ArrangeFiles {
	public static void main(String[] args) throws IOException {
		

	}
	public static void moveFileIntoSpecificFolder(String fileName1, String fileName2 , String folderName) throws InterruptedException {
		File file1 = new File(fileName1);
		File file2 = new File(fileName2);
		File folder =new File(folderName);
		if(!folder.exists()) {
			folder.mkdir();//create dir
		}
		// renaming the file and moving it to a new location
		
		if (file1.renameTo(new File(".\\"+folderName+"\\"+fileName1)) &&file1.renameTo(new File(".\\"+folderName+"\\"+fileName2))) {
			// if file copied successfully then delete the original file
			file1.delete();
			file2.delete();
		//	new File(App.NewPickupFILENAME).renameTo(new File (App.PickUpFILENAME));
			System.out.println("File moved successfully");
		} else {
			System.out.println("Failed to move the file");
		}
	}

}
