package com.jo.dy.ot.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.jo.dy.ot.dao.PermissionMapper;
import com.jo.dy.ot.entity.Permission;
import com.jo.dy.ot.entity.PermissionExample;
import com.jo.dy.ot.entity.PermissionExample.Criteria;
import com.jo.dy.ot.enums.StatusEnum;
import com.jo.dy.ot.service.PermissionService;
import com.jo.dy.ot.util.Result;

@Service("permissionService")
public class PermissionServiceImpl implements PermissionService {

	@Resource
	private PermissionMapper permissionMapper;
	
	@Override
	@Transactional(rollbackFor=Exception.class)
	public Result save(Permission permission) {
		Result result = new Result();
		PermissionExample example=new PermissionExample();
		Criteria criteria = example.createCriteria();
		criteria.andCodeEqualTo(permission.getCode());
		criteria.andStatusEqualTo(StatusEnum.NORMAL.toString());
		List<Permission> list = permissionMapper.selectByExample(example);
		if(!CollectionUtils.isEmpty(list)) {
			if(list.size()>1) {
				result.fail("保存失败！未知错误");
				return result;
			}
			if(permission.getId()==null || permission.getId().compareTo(list.get(0).getId())!=0) {
				result.fail("已存在该code的权限了");
				return result;
			}
		}
		if(permission.getId()==null) {
			permissionMapper.insert(permission);
		}else {
			permissionMapper.updateByPrimaryKeySelective(permission);
		}
		
		return result;
	}

	@Override
	@Transactional(rollbackFor=Exception.class)
	public void saveWithTranction() {
		String s="s1";
		Permission record=new Permission(null, s, s, s, new Date());
		permissionMapper.insertSelective(record);
		int i = 1/0;
	}

	@Override
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.NEVER)
	public void neverNeedTranction() {
		String s="s1";
		Permission record=new Permission(null, s, s, s, new Date());
		permissionMapper.insertSelective(record);
	}

	@Override
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.REQUIRES_NEW)
	public void requiresNewTranction() {
		String s="s1";
		Permission record=new Permission(null, s, s, s, new Date());
		permissionMapper.insertSelective(record); 
		int i = 1/0;
	}

	@Override
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.NESTED)
	public void nestedTranction() {
		String s="s1";
		Permission record=new Permission(null, s, s, s, new Date());
		permissionMapper.insertSelective(record); 
	}

	@Override
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.SUPPORTS)
	public void supportsTranction() {
		String s="s1";
		Permission record=new Permission(null, s, s, s, new Date());
		permissionMapper.insertSelective(record); 
	}

	@Override
	@Transactional(rollbackFor=Exception.class,propagation=Propagation.NOT_SUPPORTED)
	public void notSupportedTranction() {
		String s="s1";
		Permission record=new Permission(null, s, s, s, new Date());
		permissionMapper.insertSelective(record);
		int i=1/0;
	}

}
