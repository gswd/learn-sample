package com.hm707.java8.stream.forker;

import java.util.Spliterator;
import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;

/**
 * Created by LH on 2017/11/17.
 */
public class BlockingQueueSpliterator<T> implements Spliterator<T> {

	private final BlockingQueue<T> q;

	public BlockingQueueSpliterator(BlockingQueue<T> q) {
		this.q = q;
	}

	@SuppressWarnings("SingleStatementInBlock")
	@Override
	public boolean tryAdvance(Consumer<? super T> action) {

		T t;
		while (true) {
			try {
				t = q.take();
				break;
			} catch (InterruptedException e) {
			}
		}

		if (t != ForkingStreamConsumer.END_OF_STREAM) {
			action.accept(t);
			return true;
		}

		return false;
	}

	@Override
	public Spliterator<T> trySplit() {
		return null;
	}

	@Override
	public long estimateSize() {
		return 0;
	}

	@Override
	public int characteristics() {
		return 0;
	}
}
