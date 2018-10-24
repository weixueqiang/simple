package com.jo.dy.ot.service.test;

import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.jo.dy.ot.entity.Permission;
import com.jo.dy.ot.enums.StatusEnum;
import com.jo.dy.ot.service.PermissionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "classpath:spring/*.xml" })
public class PermissionServiceTest {

	@Resource
	private PermissionService permissionService;

	@Test
	public void test() {
		System.err.println(permissionService + "\n");
	}

	@Test
	public void save() {
		Permission permission = new Permission();
		permission.setCode("user:list");
		permission.setName("自动生成测试..");
		permission.setStatus(StatusEnum.NORMAL.toString());
		permission.setCreateTime(new Date());
		permissionService.save(permission);
	}

}
