package com.nb.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class FutureTest {

	ExecutorService service = Executors.newCachedThreadPool();
	ReturnSomething retSomething = new ReturnSomething();

	public void futureTest() throws InterruptedException {

		Future<String> future = service.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				return retSomething.returnDummy();
			}
		});

		System.out.println("wait till future is back.." + future.isDone());

		try {
			String returnedValue = future.get();
			System.out.println("what does the future hold::" + returnedValue);
		} catch (ExecutionException ex) {
			service.shutdown();
		}
	}

	class ReturnSomething {
		public String returnDummy() {
			return "dummy";
		}
	}

	public static void main(String[] args) throws InterruptedException {
		FutureTest test = new FutureTest();
		test.futureTest();
	}
}
