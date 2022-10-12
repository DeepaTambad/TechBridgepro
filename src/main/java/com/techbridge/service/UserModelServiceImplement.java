package com.techbridge.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.techbridge.dao.UserModelDAO;
import com.techbridge.dto.UserDTO;
import com.techbridge.exception.EmailOrMobileNumberAlreadyExistException;
import com.techbridge.model.UserModel;

@Service("usermodelService")
@Transactional
public class UserModelServiceImplement implements UserModelService {

	@Autowired
	@Qualifier("usermodelDao")
	private UserModelDAO usermodelDao;

	@Override
	public List<UserDTO> getAllUsers() {
		List<UserDTO> listdto = new ArrayList<UserDTO>();

		List<UserModel> entitylist = usermodelDao.getAllUsers1();

		entitylist.forEach(entity -> {
			UserDTO userdto = new UserDTO();
			userdto.setUserId(entity.getUserId());
			userdto.setEmail(entity.getEmail());
			userdto.setUserName(entity.getUserName());
			userdto.setPassword(entity.getPassword());
			userdto.setMobileNumber(entity.getMobileNumber());
			userdto.setUserActive(entity.getUserActive());
			userdto.setRole(entity.getRole());

			listdto.add(userdto);
		});

		return listdto;
	}

	@Override
	public UserDTO insertUser(UserDTO dto) throws EmailOrMobileNumberAlreadyExistException {
		UserModel entity = new UserModel();

		entity.setEmail(dto.getEmail());
		entity.setUserName(dto.getUserName());
		entity.setPassword(dto.getPassword());
		entity.setMobileNumber(dto.getMobileNumber());
		entity.setUserActive(dto.getUserActive());
		entity.setRole(dto.getRole());

		UserModel entity1 = usermodelDao.insertUser1(entity);
		dto.setUserId(entity1.getUserId());
		return dto;
	}

	@Override
	public UserDTO updateUser(UserDTO userDto) throws EmailOrMobileNumberAlreadyExistException {

		UserModel entity = new UserModel();

		entity.setUserId(userDto.getUserId());
		entity.setEmail(userDto.getEmail());
		entity.setUserName(userDto.getUserName());
		entity.setPassword(userDto.getPassword());
		entity.setMobileNumber(userDto.getMobileNumber());
		entity.setUserActive(userDto.getUserActive());
		entity.setRole(userDto.getRole());
		usermodelDao.updateUser1(entity);

		return userDto;

	}

	@Override
	public void deleteUser(int userId) {

		usermodelDao.deleteUser1(userId);
	}

	@Override
	public UserDTO getUserById(int userId) {

		UserModel entity = usermodelDao.getUserId(userId);

		UserDTO dto = new UserDTO();

		dto.setUserId(entity.getUserId());
		dto.setUserName(entity.getUserName());
		dto.setEmail(entity.getEmail());
		dto.setPassword(entity.getPassword());
		dto.setMobileNumber(entity.getMobileNumber());
		dto.setUserActive(entity.getUserActive());
		dto.setRole(entity.getRole());

		return dto;
	}

}