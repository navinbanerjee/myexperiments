package com.nb.concurrency;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantTest {
	
	class MultithreadedObject {
		
		private final ReentrantLock lock = new ReentrantLock();
		
		public void multithreadedLogic(){
			System.out.println("is locked?" + lock.isLocked());
			
			if(lock.isLocked()){
				System.out.println("current thread name if locked:" + Thread.currentThread().getName());
				boolean lockAcquired = lock.tryLock();
				
				System.out.println("did we get the lock:" + lockAcquired);
			}else{
				System.out.println("current thread name if not locked already:" + Thread.currentThread().getName());
				lock.lock();
			}
			
			try{
//				System.out.println("lock acquired");
				System.out.println("current thread name:" + Thread.currentThread().getName());
				Thread.sleep(2000);
				System.out.println("is anybody waiting :: " + lock.getQueueLength());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				System.out.println("unlock called");
				if(lock.isLocked())
					lock.unlock();
			}
		}
	}
	
	class ThreadToTest implements Runnable{
		
		private MultithreadedObject mtObject = new MultithreadedObject();
		
		public ThreadToTest() {
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public void run() {
			mtObject.multithreadedLogic();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		
		ReentrantTest.ThreadToTest t = new ReentrantTest().new ThreadToTest();
		
		Thread thread1 = new Thread(t, "Thread 1");
		thread1.start();
		Thread.sleep(1000);
		Thread thread2 = new Thread(t, "Thread 2");
		thread2.start();
		
		Thread.sleep(5000);
	}

}
