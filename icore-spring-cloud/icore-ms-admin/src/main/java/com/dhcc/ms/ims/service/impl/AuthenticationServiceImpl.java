package com.dhcc.ms.ims.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhcc.ms.exception.CommonException;
import com.dhcc.ms.ims.dao.UserDao;
import com.dhcc.ms.ims.dto.request.LoginDtoReq;
import com.dhcc.ms.ims.dto.response.UserDtoResp;
import com.dhcc.ms.ims.po.User;
import com.dhcc.ms.ims.service.AuthenticationService;
import com.dhcc.ms.ims.utils.ImsErrorCodes;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

	@Autowired
	private UserDao userDao;
	@Override
	public UserDtoResp login(LoginDtoReq logonDtoReq) throws CommonException {
		User user = new User();
		user.setUsername(logonDtoReq.getUsername());
		user.setPassword(logonDtoReq.getPassword());
		User userOne =  userDao.selectOne(user);
		if(userOne==null) {
			throw new CommonException(ImsErrorCodes.CODE_120100,ImsErrorCodes.CODE_120100_MSG);
		}
		UserDtoResp userDto = new UserDtoResp();
        BeanUtils.copyProperties(userOne,userDto);
        userDto.setPassword("");
		return userDto;
	}

	@Override
	public void logout() throws CommonException {
		// TODO Auto-generated method stub
		
	}


}
