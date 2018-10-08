package simple.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ThreadTest {

	@Test
	public void test() {
//		Thread curThread = Thread.currentThread();
//		curThread.setDaemon(true);
//		Thread t=new Thread(new Tests());
		System.err.println(invodMethod());
	}
	public static void main(String[] args) {
		System.err.println(new ThreadTest().invodMethod());
	}
	
	
	public  String invodMethod() {
		Tests target = new Tests();
		ArrayList<String> list = new ArrayList<String>();
		target.setList(list);
		ArrayList<String> list2 = new ArrayList<String>();
		target.setList2(list2);
		Thread t=new Thread(target);
		t.start();
		while(true) {
			try {
				Thread.sleep(500);
				System.out.println("等待结束!");
			} catch (InterruptedException e) {
				   e.printStackTrace();
			}
			if(target.getList2()==null) {
				break;
			}
		}
		System.out.println(list);
		return "ok";
	}
	
}

class Tests implements Runnable{

	private List<String> list;
	private List<String> list2;
	
	public List<String> getList() {
		return list;
	}

	public void setList2(List<String> list2) {
		this.list2 = list2;
	}
	public List<String> getList2() {
		return list2;
	}
	
	public void setList(List<String> list) {
		this.list = list;
	}

	@Override
	public void run() {
			try {
				Thread.sleep(3000);
				list2=null;
				list.add("ok");
				System.out.println("结束!");
			} catch (InterruptedException e) {
				   e.printStackTrace();
			}
	}
	
}
