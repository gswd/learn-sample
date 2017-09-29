package com.hm707.executor.framework.cancel;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 如果任务被取消则get()方法将抛出CancellationException异常
 */
public class Main {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		ThreadPoolExecutor executor=(ThreadPoolExecutor)Executors.newCachedThreadPool();
		Task task=new Task();
		System.out.printf("Main: Executing the Task\n");
		Future<String> result=executor.submit(task);
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.printf("Main: Canceling the Task\n");

		//mayInterruptIfRunning 为true表示正在运行的线程会被打断
		boolean cancelResult = result.cancel(true);
		System.out.println("cancel result : " + cancelResult);

		//如果这个任务 已经完成 或 之前的已被取消 或 由于其他原因不能被取消，则该方法返回false表示取消失败。
		boolean cancelAgainResult = result.cancel(true);
		System.out.println("cancel again result : " + cancelAgainResult);

		System.out.printf("Main: Canceled: %s\n",result.isCancelled());
		System.out.printf("Main: Done: %s\n",result.isDone());
		executor.shutdown();
		System.out.printf("Main: The executor has finished\n");

	}
}
