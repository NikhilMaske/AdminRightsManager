package com.adminrightsmanager.taskmanager;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.adminrightsmanager.csvreader.DateFunctions;

public class ConcreteTask implements Task {

	String mHostName;

	String mIpAddress;

	String mStartDate;

	String mEndDate;

	String xOS;

	String granted;

	String revoked;
	
	String command;
	
	protected static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	protected static Date mPresentDate = DateFunctions.getPresentDate();

	protected static Date mEndDateFromUser = null;
	
	protected static Date mStartDateFromUser = null;

	public String getGrantedDate() {
		return granted;
	}
	public void setGrantedDate(String granted) {
		this.granted = granted;
	}
	public String getRevokedDate() {
		return revoked;
	}
	public void setRevokedDate(String revoked) {
		this.revoked = revoked;
	}

	public void setTask(String mHostName, String mIpAddress, String mStartDate,
			String mEndDate, String xOS) {
		this.mHostName = mHostName;
		this.mIpAddress = mIpAddress;
		this.mStartDate = mStartDate;
		this.mEndDate = mEndDate;
		this.xOS = xOS;
	}
	public String getxOS() {
		return xOS;
	}

	public void setxOS(String xOS) {
		this.xOS = xOS;
	}

	public String getHostName() {
		return mHostName;
	}

	public void setHostName(String mHostName) {
		this.mHostName = mHostName;
	}

	public String getIpAddress() {
		return mIpAddress;
	}

	public void setIpAddress(String mIpAddress) {
		this.mIpAddress = mIpAddress;
	}

	public String getStartDate() {
		return mStartDate;
	}

	public void setStartDate(String mStartDate) {
		this.mStartDate = mStartDate;
	}

	public String getEndDate() {
		return mEndDate;
	}

	public void setEndDate(String mEndDate) {
		this.mEndDate = mEndDate;
	}
	public void setCommandString(String command) {
		this.command = command;
	}
	public String getCommandString() {
		String command = "PsExec64.exe \\10.11.31.165 cmd";
//		try {
//			mEndDateFromUser = sdf.parse(mEndDate);
//			mStartDateFromUser = sdf.parse(mStartDate);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		if (mPresentDate.after(mEndDateFromUser)) {
//			System.out.println("mPresentDate is after mEndDateFromUser");
//			System.out.println("Rights already removed for ::" + mHostName + "\n\n");
//
//		} else if (mPresentDate.before(mEndDateFromUser)) {
//			System.out.println("mPresentDate is before mEndDateFromUser");
//			System.out
//					.println("let this skip , because days remaining to remove rights:: "
//							+ mHostName + "\n\n");
//		} else if (mPresentDate.equals(mEndDateFromUser)) {
//			System.out.println("mPresentDate is equal to mEndDateFromUser");
//			System.out.println("need to remove rights for:: " + mHostName + "\n\n");
//			// if (xOS.equalsIgnoreCase("x86")) {
//			// command = "PsExec.exe \\\\" + mIpAddress
//			// + "net localgroup Administrators\"\r\n" + "infostretch\\"
//			// + mHostName + "\" /add";
//			// } else if (xOS.equalsIgnoreCase("x64")) {
//			// command = "PsExec64.exe \\\\" + mIpAddress
//			// + " net localgroup Administrators\"\r\n" + "infostretch\\"
//			// + mHostName + "\" /add";
//			// } else {
//			// System.out.println("invalid xOS defined in CSV for hostname" + mHostName);
//			// }
//		} else {
//			System.out.println("How to get here?");
//		}
//
//		if (mPresentDate.equals(mStartDateFromUser)) {
//			System.out.println("mPresentDate is equals to mStartDateFromUser");
//			System.out.println("Need to Add rights for " + mHostName);
//		}
		if(this.command.equals("remove")) {
			command = "PsExec64.exe \\10.11.31.165 cmd remove";
			//assign command to remove rights
		}else if(this.command.equals("add")) {
			command = "PsExec64.exe \\10.11.31.165 cmd add";
			//assign command to add rights
		}else {
			System.out.println("invalid command");
		}
		return command;

	}

}
