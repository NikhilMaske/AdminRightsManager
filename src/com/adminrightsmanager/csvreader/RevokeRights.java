package com.adminrightsmanager.csvreader;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import com.adminrightsmanager.taskmanager.ConcreteTask;
import com.adminrightsmanager.taskmanager.Task;

/**
 * The Class RevokeRights.
 */
public class RevokeRights {

	/** The sdf. */
	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	/** The m present date. */
	Date mPresentDate = DateFunctions.setTimeToMidnight(Calendar.getInstance().getTime());

	/** The m end date from user. */
	Date mEndDateFromUser = null;

	/** The current vector. */
	Vector<Task> currentVector = null;

	/** The m list of host name. */
	Vector<Task> mListOfHostName = new Vector<Task>();

	/** The m task. */
	ConcreteTask mTask = new ConcreteTask();

	/**
	 * Gets the revoke rights user list.
	 *
	 * @param getVector
	 *            the get vector
	 * @return the revoke rights user list
	 * @throws Exception
	 *             the exception
	 */
	public Vector<Task> getRevokeRightsUserList(Vector<Task> getVector) throws Exception {
		currentVector = getVector;
		System.out.println("Date instance:::::::::::" + (mPresentDate) + "\n\n");
		for (int i = 0; i < currentVector.size(); i++) {
			mEndDateFromUser = sdf.parse(currentVector.get(i).getEndDate());
			System.out
					.println("End Date After Parsing:::::::::::::::" + mEndDateFromUser);
			if (mPresentDate.after(mEndDateFromUser)) {
				System.out.println("mPresentDate is after mEndDateFromUser");
				System.out.println("Removing entry for::::::::::::::"
						+ currentVector.get(i).getHostName() + ":" + i);

				System.out.println("Rights already removed\n\n");

			} else if (mPresentDate.before(mEndDateFromUser)) {
				System.out.println("mPresentDate is before mEndDateFromUser");
				System.out.println("Removing entry for:::::::::::::"
						+ currentVector.get(i).getHostName() + ":" + i);
				System.out.println(
						"let this skip , because days remaining to remove rights\n\n");
			} else if (mPresentDate.equals(mEndDateFromUser)) {
				System.out.println("mPresentDate is equal to mEndDateFromUser");
				System.out.println("need to remove rights\n\n");
				mTask.setTask(currentVector.get(i).getHostName(),
						currentVector.get(i).getIpAddress(),
						currentVector.get(i).getStartDate(),
						currentVector.get(i).getEndDate(), currentVector.get(i).getxOS());
				mListOfHostName.add(mTask);
			} else {
				System.out.println("How to get here?");
			}
		}
		System.out.println(mListOfHostName.size());
		for (int j = 0; j < mListOfHostName.size(); j++) {
			System.out.println("Rights Needs to remove for::::::::::"
					+ mListOfHostName.get(j).getHostName());
		}
		return mListOfHostName;
	}

	/**
	 * Gets the invoke rights user list.
	 *
	 * @param getVector
	 *            the get vector
	 * @return the invoke rights user list
	 * @throws Exception
	 *             the exception
	 */
	public Vector<Task> getInvokeRightsUserList(Vector<Task> getVector) throws Exception {
		currentVector = getVector;
		System.out.println("Date instance:::::::::::" + (mPresentDate) + "\n\n");
		for (int i = 0; i < currentVector.size(); i++) {
			mEndDateFromUser = sdf.parse(currentVector.get(i).getEndDate());
			System.out
					.println("End Date After Parsing:::::::::::::::" + mEndDateFromUser);
			if (mPresentDate.after(mEndDateFromUser)) {
				System.out.println("mPresentDate is after mEndDateFromUser");
				System.out.println("Removing entry for::::::::::::::"
						+ currentVector.get(i).getHostName() + ":" + i);

				System.out.println("Rights already removed\n\n");

			} else if (mPresentDate.before(mEndDateFromUser)) {
				System.out.println("mPresentDate is before mEndDateFromUser");
				System.out.println("Removing entry for:::::::::::::"
						+ currentVector.get(i).getHostName() + ":" + i);
				System.out.println(
						"let this skip , because days remaining to remove rights\n\n");
			} else if (mPresentDate.equals(mEndDateFromUser)) {
				System.out.println("mPresentDate is equal to mEndDateFromUser");
				System.out.println("need to remove rights\n\n");
				mTask.setTask(currentVector.get(i).getHostName(),
						currentVector.get(i).getIpAddress(),
						currentVector.get(i).getStartDate(),
						currentVector.get(i).getEndDate(), currentVector.get(i).getxOS());
				mListOfHostName.add(mTask);
			} else {
				System.out.println("How to get here?");
			}
		}
		System.out.println(mListOfHostName.size());
		for (int j = 0; j < mListOfHostName.size(); j++) {
			System.out.println("Rights Needs to remove for::::::::::"
					+ mListOfHostName.get(j).getHostName());
		}
		return mListOfHostName;
	}

	/**
	 * The main method.
	 *
	 * @param args
	 *            the arguments
	 * @throws Exception
	 *             the exception
	 */
	public static void main(String args[]) throws Exception {
		RevokeRights r = new RevokeRights();
		AppCSVReader csvReaderObject = new AppCSVReader(".\\test.csv");
		r.getRevokeRightsUserList(csvReaderObject.readCSV());
	}
}