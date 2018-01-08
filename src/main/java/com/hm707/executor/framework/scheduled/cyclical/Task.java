package com.hm707.executor.framework.scheduled.cyclical;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

public class Task implements Runnable {

	private String name;

	public Task(String name) {
		this.name = name;
	}

	@Override
	public void run() {
		System.out.printf("%s: Task starting at : %s\n",name,new Date());
		//try {
		//	System.out.println(1/0);
		//} catch (Exception e) {
		//	e.printStackTrace();
		//	throw new RuntimeException();
		//}
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
