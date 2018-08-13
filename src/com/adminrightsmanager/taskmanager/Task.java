package com.adminrightsmanager.taskmanager;

/**
 * The Interface Task.
 */
public interface Task {

	/** The m host name. */
	String mHostName = null;

	/** The m ip address. */
	String mIpAddress = null;

	/** The m start date. */
	String mStartDate = null;

	/** The m end date. */
	String mEndDate = null;

	/** The x OS. */
	String xOS = null;

	/** The m granted date. */
	String mGrantedDate = null;

	/** The m revoked date. */
	String mRevokedDate = null;

	/**
	 * Gets the granted date.
	 *
	 * @return the granted date
	 */
	public String getGrantedDate();

	/**
	 * Sets the granted date.
	 *
	 * @param granted
	 *            the new granted date
	 */
	public void setGrantedDate(String granted);

	/**
	 * Gets the revoked date.
	 *
	 * @return the revoked date
	 */
	public String getRevokedDate();

	/**
	 * Sets the revoked date.
	 *
	 * @param revoked
	 *            the new revoked date
	 */
	public void setRevokedDate(String revoked);

	/**
	 * Gets the command string.
	 *
	 * @return the command string
	 */
	public String getCommandString();

	/**
	 * Sets the host name.
	 *
	 * @param string
	 *            the new host name
	 */
	public void setHostName(String string);

	/**
	 * Sets the ip address.
	 *
	 * @param string
	 *            the new ip address
	 */
	public void setIpAddress(String string);

	/**
	 * Sets the start date.
	 *
	 * @param string
	 *            the new start date
	 */
	public void setStartDate(String string);

	/**
	 * Sets the end date.
	 *
	 * @param string
	 *            the new end date
	 */
	public void setEndDate(String string);

	/**
	 * Gets the host name.
	 *
	 * @return the host name
	 */
	public String getHostName();

	/**
	 * Gets the ip address.
	 *
	 * @return the ip address
	 */
	public String getIpAddress();

	/**
	 * Gets the start date.
	 *
	 * @return the start date
	 */
	public String getStartDate();

	/**
	 * Gets the end date.
	 *
	 * @return the end date
	 */
	public String getEndDate();

	/**
	 * Gets the x OS.
	 *
	 * @return the x OS
	 */
	public String getxOS();

	/**
	 * Sets the x OS.
	 *
	 * @param string
	 *            the new x OS
	 */
	public void setxOS(String string);
}
