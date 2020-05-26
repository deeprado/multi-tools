package com.deep.shiro_permission.service;

import com.deep.shiro_permission.po.ActiveUser;
import com.deep.shiro_permission.po.SysPermission;
import com.deep.shiro_permission.po.SysUser;

import java.util.List;


/**
 * 
 * <p>Title: SysService</p>
 * <p>Description: 认证授权服务接口</p>
 */
public interface SysService {
	
	//根据用户的身份和密码 进行认证，如果认证通过，返回用户身份信息
	public ActiveUser authenticat(String userCode, String password) throws Exception;
	
	//根据用户账号查询用户信息
	public SysUser findSysUserByUserCode(String userCode)throws Exception;
	
	//根据用户id查询权限范围的菜单
	public List<SysPermission> findMenuListByUserId(String userid) throws Exception;
	
	//根据用户id查询权限范围的url
	public List<SysPermission> findPermissionListByUserId(String userid) throws Exception;
}
