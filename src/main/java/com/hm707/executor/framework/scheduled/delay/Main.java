package com.hm707.executor.framework.scheduled.delay;

import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Main {

	public static void main(String[] args) {
		ScheduledThreadPoolExecutor executor = (ScheduledThreadPoolExecutor)Executors.newScheduledThreadPool(1);
		System.out.printf("Main: Starting at: %s\n", new Date());
		for (int i = 0; i < 5; i++) {
			Task task = new Task("Task " + i);
			executor.schedule(task, i + 1, TimeUnit.SECONDS);
		}

		//setExecuteExistingDelayedTasksAfterShutdownPolicy=false表示shutdown后未执行的任务不再执行
		//executor.setExecuteExistingDelayedTasksAfterShutdownPolicy(false);
		executor.shutdown();
		try {
			executor.awaitTermination(1, TimeUnit.DAYS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Main: Ends at: %s\n", new Date());

	}
}
