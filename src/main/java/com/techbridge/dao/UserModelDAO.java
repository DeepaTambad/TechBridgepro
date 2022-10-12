package com.techbridge.dao;

import java.util.List;

import com.techbridge.model.UserModel;


public interface UserModelDAO {
	
	List<UserModel> getAllUsers1();

	UserModel insertUser1(UserModel user);

	UserModel updateUser1(UserModel entity);
	
	void deleteUser1(int userId);
	
	UserModel getUserId(Integer userId);
}
	
