package com.adminrightsmanager.taskmanager;

/**
 * The Interface TaskResponseHandler.
 */
public interface TaskResponseHandler {

	/**
	 * Handle response.
	 *
	 * @param task
	 *            the task
	 * @param result
	 *            the result
	 */
	public void handleResponse(Task task, String result);

}
