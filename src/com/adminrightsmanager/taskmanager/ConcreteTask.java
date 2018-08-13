package com.adminrightsmanager.taskmanager;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.adminrightsmanager.csvreader.DateFunctions;

/**
 * The Class ConcreteTask.
 */
public class ConcreteTask implements Task {

	/** The m host name. */
	String mHostName;

	/** The m ip address. */
	String mIpAddress;

	/** The m start date. */
	String mStartDate;

	/** The m end date. */
	String mEndDate;

	/** The x OS. */
	String xOS;

	/** The granted. */
	String granted;

	/** The revoked. */
	String revoked;

	/** The command. */
	String command;

	/** The sdf. */
	protected static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	/** The m present date. */
	protected static Date mPresentDate = DateFunctions.getPresentDate();

	/** The m end date from user. */
	protected static Date mEndDateFromUser = null;

	/** The m start date from user. */
	protected static Date mStartDateFromUser = null;

	/*
	 * (non-Javadoc)
	 * @see com.adminrightsmanager.taskmanager.Task#getGrantedDate()
	 */
	public String getGrantedDate() {
		return granted;
	}

	/*
	 * (non-Javadoc)
	 * @see com.adminrightsmanager.taskmanager.Task#setGrantedDate(java.lang.String)
	 */
	public void setGrantedDate(String granted) {
		this.granted = granted;
	}

	/*
	 * (non-Javadoc)
	 * @see com.adminrightsmanager.taskmanager.Task#getRevokedDate()
	 */
	public String getRevokedDate() {
		return revoked;
	}

	/*
	 * (non-Javadoc)
	 * @see com.adminrightsmanager.taskmanager.Task#setRevokedDate(java.lang.String)
	 */
	public void setRevokedDate(String revoked) {
		this.revoked = revoked;
	}

	/**
	 * Sets the task.
	 *
	 * @param mHostName
	 *            the m host name
	 * @param mIpAddress
	 *            the m ip address
	 * @param mStartDate
	 *            the m start date
	 * @param mEndDate
	 *            the m end date
	 * @param xOS
	 *            the x OS
	 */
	public void setTask(String mHostName, String mIpAddress, String mStartDate,
			String mEndDate, String xOS) {
		this.mHostName = mHostName;
		this.mIpAddress = mIpAddress;
		this.mStartDate = mStartDate;
		this.mEndDate = mEndDate;
		this.xOS = xOS;
	}

	/*
	 * (non-Javadoc)
	 * @see com.adminrightsmanager.taskmanager.Task#getxOS()
	 */
	public String getxOS() {
		return xOS;
	}

	/*
	 * (non-Javadoc)
	 * @see com.adminrightsmanager.taskmanager.Task#setxOS(java.lang.String)
	 */
	public void setxOS(String xOS) {
		this.xOS = xOS;
	}

	/*
	 * (non-Javadoc)
	 * @see com.adminrightsmanager.taskmanager.Task#getHostName()
	 */
	public String getHostName() {
		return mHostName;
	}

	/*
	 * (non-Javadoc)
	 * @see com.adminrightsmanager.taskmanager.Task#setHostName(java.lang.String)
	 */
	public void setHostName(String mHostName) {
		this.mHostName = mHostName;
	}

	/*
	 * (non-Javadoc)
	 * @see com.adminrightsmanager.taskmanager.Task#getIpAddress()
	 */
	public String getIpAddress() {
		return mIpAddress;
	}

	/*
	 * (non-Javadoc)
	 * @see com.adminrightsmanager.taskmanager.Task#setIpAddress(java.lang.String)
	 */
	public void setIpAddress(String mIpAddress) {
		this.mIpAddress = mIpAddress;
	}

	/*
	 * (non-Javadoc)
	 * @see com.adminrightsmanager.taskmanager.Task#getStartDate()
	 */
	public String getStartDate() {
		return mStartDate;
	}

	/*
	 * (non-Javadoc)
	 * @see com.adminrightsmanager.taskmanager.Task#setStartDate(java.lang.String)
	 */
	public void setStartDate(String mStartDate) {
		this.mStartDate = mStartDate;
	}

	/*
	 * (non-Javadoc)
	 * @see com.adminrightsmanager.taskmanager.Task#getEndDate()
	 */
	public String getEndDate() {
		return mEndDate;
	}

	/*
	 * (non-Javadoc)
	 * @see com.adminrightsmanager.taskmanager.Task#setEndDate(java.lang.String)
	 */
	public void setEndDate(String mEndDate) {
		this.mEndDate = mEndDate;
	}

	/**
	 * Sets the command string.
	 *
	 * @param command
	 *            the new command string
	 */
	public void setCommandString(String command) {
		this.command = command;
	}

	/*
	 * (non-Javadoc)
	 * @see com.adminrightsmanager.taskmanager.Task#getCommandString()
	 */
	public String getCommandString() {
		String command = "PsExec64.exe \\10.11.31.165 cmd";
		// try {
		// mEndDateFromUser = sdf.parse(mEndDate);
		// mStartDateFromUser = sdf.parse(mStartDate);
		// } catch (ParseException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		//
		// if (mPresentDate.after(mEndDateFromUser)) {
		// System.out.println("mPresentDate is after mEndDateFromUser");
		// System.out.println("Rights already removed for ::" + mHostName + "\n\n");
		//
		// } else if (mPresentDate.before(mEndDateFromUser)) {
		// System.out.println("mPresentDate is before mEndDateFromUser");
		// System.out
		// .println("let this skip , because days remaining to remove rights:: "
		// + mHostName + "\n\n");
		// } else if (mPresentDate.equals(mEndDateFromUser)) {
		// System.out.println("mPresentDate is equal to mEndDateFromUser");
		// System.out.println("need to remove rights for:: " + mHostName + "\n\n");
		// // if (xOS.equalsIgnoreCase("x86")) {
		// // command = "PsExec.exe \\\\" + mIpAddress
		// // + "net localgroup Administrators\"\r\n" + "infostretch\\"
		// // + mHostName + "\" /add";
		// // } else if (xOS.equalsIgnoreCase("x64")) {
		// // command = "PsExec64.exe \\\\" + mIpAddress
		// // + " net localgroup Administrators\"\r\n" + "infostretch\\"
		// // + mHostName + "\" /add";
		// // } else {
		// // System.out.println("invalid xOS defined in CSV for hostname" + mHostName);
		// // }
		// } else {
		// System.out.println("How to get here?");
		// }
		//
		// if (mPresentDate.equals(mStartDateFromUser)) {
		// System.out.println("mPresentDate is equals to mStartDateFromUser");
		// System.out.println("Need to Add rights for " + mHostName);
		// }
		if (this.command.equals("remove")) {
			command = "PsExec64.exe \\10.11.31.165 cmd remove";
			// assign command to remove rights
		} else if (this.command.equals("add")) {
			command = "PsExec64.exe \\10.11.31.165 cmd add";
			// assign command to add rights
		} else {
			System.out.println("invalid command");
		}
		return command;

	}

}
