package com.adminrightsmanager.taskmanager;

import java.util.Vector;

import com.adminrightsmanager.app.App;

public class TaskManager implements Runnable {

	private Thread mWorkerThread;

	public Vector<Task> mTaskQueue;

	private TaskResponseHandler mHandler;

	private static boolean mThreadFlag = true;

	public TaskManager(TaskResponseHandler handler) {
		mHandler = handler;
		mTaskQueue = new Vector<>();
		mWorkerThread = new Thread(this);
		mWorkerThread.start();
	}

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

	public void clearAllTasks() {
		synchronized (mTaskQueue) {
			System.out.println("removed all tasks from queue");
			mTaskQueue.clear();
		}
	}
	
	public static void interupt() {
		mThreadFlag = false;
	}

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
			//if queue is empty this will be last execution cycle.
			if (mTaskQueue.size() <= 0) {
				mThreadFlag = false;
				App.processStatus="completed";
				TaskManager.interupt();
				
			}
		}
		return task;
	}

}
