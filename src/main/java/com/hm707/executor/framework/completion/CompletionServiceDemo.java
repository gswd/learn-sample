package com.hm707.executor.framework.completion;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ExecutorCompletionService 可以获取到已经完成任务线程的返回值，而不需要等待。
 * 在其内部维护了一个BlockingQueue<Future<V>> 用来保存已完成任务的返回值。
 *
 * 如果自己维护一个List<Future<V>> 使用循环调用 Future#get() 这样不能做到完成一个任务马上处理的需求，
 * 因为get()会阻塞，前面的任务没完成时，后面的任务可能已经完成了。
 */
public class CompletionServiceDemo {

	public static class Task implements Callable<Integer> {
		private int i;

		Task(int i) {
			this.i = i;
		}

		@Override
		public Integer call() throws Exception {
			System.out.println("thread " + i + " start");
			Thread.sleep(new Random().nextInt(5000));
			System.out.println(Thread.currentThread().getName() + "   " + i);
			return i;
		}
	}

	public void run() {
		ExecutorService pool = Executors.newFixedThreadPool(10);
		CompletionService<Integer> completionService = new ExecutorCompletionService<>(pool);
		try {
			for (int i = 0; i < 10; i++) {
				completionService.submit(new CompletionServiceDemo.Task(i));
			}
			for (int i = 0; i < 10; i++) {
				// take 方法等待下一个结果并返回 Future 对象。
				// poll 不等待，有结果就返回一个 Future 对象，否则返回 null。
				System.out.println(completionService.take().get());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		} finally {
			pool.shutdown();
		}
	}

	public static void main(String[] args) {
		new CompletionServiceDemo().run();
	}
}