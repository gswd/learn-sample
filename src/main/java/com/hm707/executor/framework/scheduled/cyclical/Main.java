package com.hm707.executor.framework.scheduled.cyclical;

import java.util.Date;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


public class Main {

	public static void main(String[] args) {
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

		System.out.printf("Main: Starting at: %s\n", new Date());

		Task task = new Task("Task");

		/**
		 * 以固定的周期执行任务
		 * 如果任务发生异常，则后续的任务不会继续执行.
		 * 任务的执行时间如果超过指定周期，则后续的执行会延迟，但不会不执行.
		 */
		//ScheduledFuture<?> result = executor.scheduleAtFixedRate(task, 1, 2, TimeUnit.SECONDS);

		/**
		 * scheduleWithFixedDelay 给定一个初始执行的延迟时间，然后每次任务完成后相隔指定延迟时间后再次执行
		 */
		ScheduledFuture<?> result = executor.scheduleWithFixedDelay(task, 1, 2, TimeUnit.SECONDS);
		for (int i = 0; i < 20; i++) {
			System.out.printf("Main: Delay: %d\n", result.getDelay(TimeUnit.MILLISECONDS));
			//线程睡眠500毫秒
			try {
				TimeUnit.MILLISECONDS.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}


		((ScheduledThreadPoolExecutor) executor).setContinueExistingPeriodicTasksAfterShutdownPolicy(true);

		executor.shutdown();
		//try {
		//	TimeUnit.SECONDS.sleep(5);
		//} catch (InterruptedException e) {
		//	e.printStackTrace();
		//}
		//System.out.println("isDone : " + result.isDone());
		System.out.printf("Main: Finished at: %s\n", new Date());

	}
}
