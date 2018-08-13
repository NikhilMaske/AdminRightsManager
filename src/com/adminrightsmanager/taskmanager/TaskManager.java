package com.adminrightsmanager.taskmanager;

import java.util.Vector;

import com.adminrightsmanager.app.App;

/**
 * The Class TaskManager.
 */
public class TaskManager implements Runnable {

	/** The m worker thread. */
	private Thread mWorkerThread;

	/** The m task queue. */
	public Vector<Task> mTaskQueue;

	/** The m handler. */
	private TaskResponseHandler mHandler;

	/** The m thread flag. */
	private static boolean mThreadFlag = true;

	/**
	 * Instantiates a new task manager.
	 *
	 * @param handler
	 *            the handler
	 */
	public TaskManager(TaskResponseHandler handler) {
		mHandler = handler;
		mTaskQueue = new Vector<>();
		mWorkerThread = new Thread(this);
		mWorkerThread.start();
	}

	/**
	 * Adds the task.
	 *
	 * @param task
	 *            the task
	 */
	public void addTask(Task task) {
		if (task == null) {
			System.out.println("task is null");
		} else {
			synchronized (mTaskQueue) {
				mTaskQueue.add(task);
				mTaskQueue.notify();
			}
		}
	}

	/**
	 * Adds the tasks.
	 *
	 * @param vector
	 *            the vector
	 */
	public void addTasks(Vector<Task> vector) {
		if (vector == null) {
			System.out.println("task is null");
		} else {
			synchronized (mTaskQueue) {
				mTaskQueue.addAll(vector);
				mTaskQueue.notify();
			}
		}
	}

	/**
	 * Clear all tasks.
	 */
	public void clearAllTasks() {
		synchronized (mTaskQueue) {
			System.out.println("removed all tasks from queue");
			mTaskQueue.clear();
		}
	}

	/**
	 * Interupt.
	 */
	public static void interupt() {
		mThreadFlag = false;
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {

		while (mThreadFlag) {
			// fetch task
			Task task = getNextRequest();

			// execute task
			String result = ExecutorUtil.execute(task);

			// send results
			mHandler.handleResponse(task, result);
		}
	}

	/**
	 * Gets the next request.
	 *
	 * @return the next request
	 */
	private Task getNextRequest() {
		Task task = null;
		synchronized (mTaskQueue) {
			if (mTaskQueue.size() <= 0) {
				try {
					System.out.println("Waiting for new Task.");
					mTaskQueue.wait();

				} catch (InterruptedException ie) {
					System.out.println("Error occurred while waiting for new task.");
				}
			}
			task = mTaskQueue.get(0);
			mTaskQueue.remove(0);
			// if queue is empty this will be last execution cycle.
			if (mTaskQueue.size() <= 0) {
				mThreadFlag = false;
				App.processStatus = "completed";
				TaskManager.interupt();

			}
		}
		return task;
	}

}
