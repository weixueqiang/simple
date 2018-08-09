package com.jo.dy.ot.job;
 
 
import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
 
public class CustomerFollowElasticJob  implements SimpleJob{
 
	@Override
	public void execute(ShardingContext context) {
		System.out.println("定时器执行.......我还会回来的!");
	}
	
	

}


