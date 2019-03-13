package com.dhcc.ms.ims.service;

import java.util.List;

import com.dhcc.ms.exception.CommonException;
import com.dhcc.ms.ims.dto.request.UserDtoPageReq;
import com.dhcc.ms.ims.dto.request.UserDtoReq;
import com.dhcc.ms.ims.dto.response.UserDtoResp;
import com.dhcc.ms.utils.dto.BasePageDto;
import com.dhcc.ms.utils.dto.ListRespDto;

public interface UserService {
	
	public List<UserDtoResp> getAllUsers() throws CommonException;
	public UserDtoResp getUser(String userId) throws CommonException;
	public ListRespDto<UserDtoResp> getUsersByPage(UserDtoPageReq page) throws CommonException;
	public int updateUser(UserDtoReq user) throws CommonException;
}
