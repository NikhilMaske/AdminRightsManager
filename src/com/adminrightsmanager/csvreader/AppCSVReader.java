package com.adminrightsmanager.csvreader;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.Vector;

import com.adminrightsmanager.taskmanager.ConcreteTask;
import com.adminrightsmanager.taskmanager.Task;

public class AppCSVReader extends ConcreteTask {
	BufferedReader br = null;
	String line = "";
	String cvsSplitBy = ",";
	Vector<Task> vector = new Vector<Task>();

	public AppCSVReader(String csvFile) throws Exception {
		br = new BufferedReader(new FileReader(csvFile));
	}
	public Vector<Task> readCSV() {
		Boolean addTaskFlag = false;
		Boolean notNullValueInCsvFlag = false;
		try {

			while ((line = br.readLine()) != null) {
				// use comma as separator
				String column[] = line.split(cvsSplitBy);
				notNullValueInCsvFlag = checkValuesShouldNotContainsNull(column);
				if (column[0].equalsIgnoreCase("HOSTNAME")) {
					continue;
				} else if (notNullValueInCsvFlag) {
					// create csvline object to store values
					ConcreteTask newTask = new ConcreteTask();

					// add values from csv to csvLine object
					newTask.setHostName(column[0]);
					newTask.setIpAddress(column[1]);
					newTask.setStartDate(column[2]);
					newTask.setEndDate(column[3]);
					if (column[4] == null) {
						newTask.setGrantedDate(null);
					} else {
						newTask.setGrantedDate(column[4]);
					}
					if (column[5] == null) {
						newTask.setRevokedDate(null);
					} else {
						newTask.setRevokedDate(column[5]);
					}
					newTask.setxOS(column[6]);

					try {
						addTaskFlag = checkTaskToBeAddedOrNot(newTask);
						if (addTaskFlag) {
							// adding csv objects to a Vector
							vector.add(newTask);
							System.out.println(
									"need to add task for:" + newTask.getHostName());
						} 
						else {
							System.out.println(
									"need to Skip task for:" + newTask.getHostName());
						}
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return vector;
	}
	public Vector<Task> readRawDataFromCSV() throws FileNotFoundException {
		Boolean notNullValueInCsvFlag = false;
		try {

			while ((line = br.readLine()) != null) {
				// use comma as separator
				String column[] = line.split(cvsSplitBy);
				notNullValueInCsvFlag = checkValuesShouldNotContainsNull(column);
				if (column[0].equalsIgnoreCase("HOSTNAME")) {
					continue;
				} else if (notNullValueInCsvFlag) {
					// create csvline object to store values
					ConcreteTask newTask = new ConcreteTask();

					// add values from csv to csvLine object
					newTask.setHostName(column[0]);
					newTask.setIpAddress(column[1]);
					newTask.setStartDate(column[2]);
					newTask.setEndDate(column[3]);
					if (column[4] == null) {
						newTask.setGrantedDate(null);
					} else {
						newTask.setGrantedDate(column[4]);
					}
					if (column[5] == null) {
						newTask.setRevokedDate(null);
					} else {
						newTask.setRevokedDate(column[5]);
					}
					newTask.setxOS(column[6]);
					vector.add(newTask);
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return vector;
	}
	public void printList(Vector<Task> vector) {
		for (int i = 0; i < vector.size(); i++) {
			System.out.println("" + vector.get(i).getHostName() + " , "
					+ vector.get(i).getIpAddress() + " , " + vector.get(i).getStartDate()
					+ " , " + vector.get(i).getEndDate() + " , " + vector.get(i).getxOS()
					+ "]");
		}
	}
	public static Boolean checkValuesShouldNotContainsNull(String[] csvColumn) {
		Boolean checkFlag = true;
		if (csvColumn[0] == null) {
			checkFlag = false;
			System.out.println("Host name is null");
		} else if (csvColumn[1] == null) {
			checkFlag = false;
			System.out.println("Ip Address is null");
		} else if (csvColumn[2] == null) {
			checkFlag = false;
			System.out.println("Start Date is null");
		} else if (csvColumn[3] == null) {
			checkFlag = false;
			System.out.println("EndDate is null");
		} else if (csvColumn[4] == null) {
			checkFlag = false;
			System.out.println("OS type is null");
		}
		return checkFlag;
	}
	public static Boolean checkTaskToBeAddedOrNot(ConcreteTask task)
			throws ParseException {
		Boolean flag = false;
		mStartDateFromUser = sdf.parse(task.getStartDate());
		mEndDateFromUser = sdf.parse(task.getEndDate());
		if (mPresentDate.after(mStartDateFromUser)
				&& mPresentDate.before(mEndDateFromUser)) {
			// the rights should be added if granted column is null
			// and if granted column is not null then don't add this task to vector
			// as end date is before present date
			if (task.getGrantedDate().toString().isEmpty() | task.getGrantedDate() ==null) {
				flag = true;
				System.out.println("====Remove rights for==== :"+task.getHostName());
				task.setCommandString("remove");
			} else {
				flag = false;
			}
		} else if (mPresentDate.before(mStartDateFromUser)
				&& mPresentDate.before(mEndDateFromUser)) {
			// rights is to be added in future for this entry hence make flag as false
			// not to add entry into vector
			if (task.getRevokedDate().toString().isEmpty() | task.getRevokedDate() == null) {
				flag = true;
				task.setCommandString("remove");
				System.out.println("====Remove rights for==== :"+task.getHostName());
			} else {
				flag = false;
			}
		} else if (mPresentDate.equals(mStartDateFromUser)
				&& mPresentDate.before(mEndDateFromUser)) {
			// the rights have to be added for the user
			// present date is equal to start date of user and end date is before the
			// present date hence marking flag as true
			flag = true;
			task.setCommandString("add");
			System.out.println("===Add rights for=== :"+task.getHostName());

		} else if (mPresentDate.after(mStartDateFromUser)
				&& mPresentDate.equals(mEndDateFromUser)) {
			// the start date is after present date and end date is equal to present date
			// so rights have to remove and task needs to be added in vector
			flag = true;
			task.setCommandString("add");
			System.out.println("===Add rights for=== :"+task.getHostName());
		} else if (mPresentDate.after(mStartDateFromUser)
				&& mPresentDate.after(mEndDateFromUser)) {
			// if start date and end date is in past the entry is old
			// then don't add this entry into vector

			// the end date of user is in past but and revoked column contains revoked
			// date if not then flag should be true to add task into queue
			if (task.getRevokedDate().toString().isEmpty() | task.getRevokedDate() == null) {
				flag = true;
				task.setCommandString("remove");
				System.out.println("====Remove rights for==== :"+task.getHostName());
			} else {
				flag = false;
			}
		} else {
			System.out.println("Unxpected Condtion , Please Contact Support with logs");
			System.out.println("There is no hostname which needs to be processed");
		}

		return flag;
	}

	
	public static void main(String args[]) throws Exception {

		AppCSVReader readobj = new AppCSVReader(".\\test.csv");
		readobj.printList(readobj.readRawDataFromCSV());
	}
}