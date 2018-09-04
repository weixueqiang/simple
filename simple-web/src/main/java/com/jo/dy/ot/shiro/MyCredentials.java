package com.jo.dy.ot.shiro;

import java.io.UnsupportedEncodingException;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;

import com.jo.dy.ot.enums.LoginTypeEnmu;
import com.jo.dy.ot.util.MySimpleHash;
import com.jo.dy.ot.util.SHA1;

public class MyCredentials extends SimpleCredentialsMatcher {

	private Integer hashIterations;
	
	@Override
	public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
		MyUsernamePasswordToken myToken=(MyUsernamePasswordToken)token;
		MyAuthenticationInfo myInfo=(MyAuthenticationInfo)info;
		LoginTypeEnmu type = myToken.getType();
		if(LoginTypeEnmu.PASSWORD.equals(type)) {
			ByteSource salt = myInfo.getCredentialsSalt();
			String hash = MySimpleHash.get(myToken.getPassword(), salt,getHashIterations());
			try {
				hash=SHA1.digest(new String(myToken.getPassword())
						+new String(myInfo.getCredentialsSalt().getBytes(),"utf-8"));
			} catch (UnsupportedEncodingException e) {
				   //...
			}
			if(StringUtils.equals(hash, (String)myInfo.getCredentials())) {
				return true;
			}
		}else {
			return true;
		}
		
		return false;
	}

	public Integer getHashIterations() {
		return hashIterations;
	}

	public void setHashIterations(Integer hashIterations) {
		this.hashIterations = hashIterations;
	}
	
}
