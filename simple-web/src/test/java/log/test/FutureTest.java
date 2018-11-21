package log.test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

import org.junit.Test;

public class FutureTest {

	@Test
	public void name() throws InterruptedException, ExecutionException {
		ExecutorService ex = Executors.newSingleThreadExecutor();
		FutureTask<String> futureTask = new FutureTask<String>(new Callable<String>() {
			@Override
			public String call() throws Exception {
				System.out.println("执行任务中");
				Thread.sleep(5000);
				return "任务执行完成";
			}
		});

		ex.execute(futureTask);
		System.out.println("其它任务执行");
		System.out.println("其它任务执行完成");
		System.out.println(futureTask.get());
		ex.shutdown();
	}

	@Test
	public void submit() throws InterruptedException, ExecutionException {
		ExecutorService ex = Executors.newSingleThreadExecutor();
		Future<String> submit = ex.submit(new Callable<String>() {
			@Override
			public String call() throws Exception {
				System.out.println("执行任务中");
				Thread.sleep(5000);
				return "任务执行完成";
			}
		});
		System.out.println(submit.get());
		ex.shutdown();
	}

}
