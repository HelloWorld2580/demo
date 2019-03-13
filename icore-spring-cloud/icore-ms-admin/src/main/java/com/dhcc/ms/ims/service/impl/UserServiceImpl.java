package com.dhcc.ms.ims.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.dhcc.ms.exception.CommonException;
import com.dhcc.ms.ims.dao.UserDao;
import com.dhcc.ms.ims.dto.request.UserDtoPageReq;
import com.dhcc.ms.ims.dto.request.UserDtoReq;
import com.dhcc.ms.ims.dto.response.UserDtoResp;
import com.dhcc.ms.ims.po.User;
import com.dhcc.ms.ims.service.UserService;
import com.dhcc.ms.utils.ErrorCodes;
import com.dhcc.ms.utils.dto.BasePageDto;
import com.dhcc.ms.utils.dto.ListRespDto;
import com.github.abel533.entity.Example;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public List<UserDtoResp> getAllUsers() throws CommonException {
		List<User> userlist = userDao.select(null);
		if(userlist.isEmpty()) {
			throw new CommonException(ErrorCodes.DATA_NOT_EXIST_CODE_KEY,ErrorCodes.DATA_NOT_EXIST_MSG_KEY);
		}
		List<UserDtoResp> userDtoList = new ArrayList<>();
		for(User user:userlist){
            UserDtoResp userDto = new UserDtoResp();
            BeanUtils.copyProperties(user,userDto);
            userDto.setPassword("");
            userDtoList.add(userDto);
        }
		return userDtoList;
	}
	
	@Override
	public UserDtoResp getUser(String userId) throws CommonException {
		User user = new User();
		user.setUserId(userId);
		User userOne =  userDao.selectOne(user);
		if(userOne==null) {
			throw new CommonException(ErrorCodes.DATA_NOT_EXIST_CODE_KEY,ErrorCodes.DATA_NOT_EXIST_MSG_KEY);
		}
		UserDtoResp userDto = new UserDtoResp();
        BeanUtils.copyProperties(userOne,userDto);
		return userDto;
	}

	@Override
	public ListRespDto<UserDtoResp> getUsersByPage(UserDtoPageReq page) throws CommonException {
		PageHelper.startPage(page.getPageIndex(), page.getPageSize());
		Example example = new Example(User.class);
		if(!StringUtils.isEmpty(page.getName())) {
			example.or().andLike("nickname", "%"+page.getName()+"%");
			example.or().andLike("username", "%"+page.getName()+"%");
			example.or().andLike("name", "%"+page.getName()+"%");
		}
		List<User> userlist = userDao.selectByExample(example);
		if(userlist.isEmpty()) {
			throw new CommonException(ErrorCodes.DATA_NOT_EXIST_CODE_KEY,ErrorCodes.DATA_NOT_EXIST_MSG_KEY);
		}
		PageInfo<User> pageInfo = new PageInfo<User>(userlist);
		List<UserDtoResp> userDtoList = new ArrayList<>();
		for(User user:userlist){
            UserDtoResp userDto = new UserDtoResp();
            BeanUtils.copyProperties(user,userDto);
            userDto.setPassword("");
            userDtoList.add(userDto);
        }
		ListRespDto<UserDtoResp> userListResp = new ListRespDto<UserDtoResp>(userDtoList,pageInfo.getTotal());
		return userListResp;
	}

	@Override
	public int updateUser(UserDtoReq user) throws CommonException {
		User userPo = new User();
		BeanUtils.copyProperties(user,userPo);
		int count = userDao.updateByPrimaryKeySelective(userPo);
		return count;
	}

}
