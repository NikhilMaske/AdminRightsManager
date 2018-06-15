package com.adminrightsmanager.taskmanager;

public interface Task {

	String mHostName = null;

	String mIpAddress = null;

	String mStartDate = null;

	String mEndDate = null;

	String xOS = null;

	String mGrantedDate = null;

	String mRevokedDate = null;
	
	public String getGrantedDate();
	
	public void setGrantedDate(String granted);
	
	public String getRevokedDate();
	
	public void setRevokedDate(String revoked);
	
	public String getCommandString();

	public void setHostName(String string);

	public void setIpAddress(String string);

	public void setStartDate(String string);

	public void setEndDate(String string);

	public String getHostName();

	public String getIpAddress();

	public String getStartDate();

	public String getEndDate();

	public String getxOS();

	public void setxOS(String string);
}
