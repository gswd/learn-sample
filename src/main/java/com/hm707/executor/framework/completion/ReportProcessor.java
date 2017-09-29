package com.hm707.executor.framework.completion;

import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ReportProcessor implements Runnable {
	private CompletionService<String> service;
	private boolean end;
	public ReportProcessor (CompletionService<String> service){
		this.service=service;
		end=false;
	}
	@Override
	public void run() {
		while (!end){
			try {
				//poll()：检查是否有任何Future对象在队列中。如果列队是空的，它立即返回null。否则，它返回第一个元素，并从列队中删除它。
				//take()：这个方法，检查是否有任何Future对象在队列中。如果队列是空的，它阻塞线程直到队列有一个元素。
				// 		  当队列有元素，它返回第一元素，并从列队中删除它。
				Future<String> result=service.poll(20, TimeUnit.SECONDS);
				if (result!=null) {
					String report=result.get();
					System.out.printf("ReportReceiver: Report Received:%s\n",report);
				}
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		System.out.printf("ReportSender: End\n");
	}
	public void setEnd(boolean end) {
		this.end = end;
	}

}
