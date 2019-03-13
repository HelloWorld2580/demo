package com.dhcc.ms.ims.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.ms.exception.CommonException;
import com.dhcc.ms.ims.dto.request.UserChangePwDtoReq;
import com.dhcc.ms.ims.dto.request.UserDtoPageReq;
import com.dhcc.ms.ims.dto.request.UserDtoReq;
import com.dhcc.ms.ims.dto.response.UserDtoResp;
import com.dhcc.ms.ims.po.User;
import com.dhcc.ms.ims.service.UserService;
import com.dhcc.ms.ims.session.Session;
import com.dhcc.ms.ims.utils.Constants;
import com.dhcc.ms.ims.utils.ImsErrorCodes;
import com.dhcc.ms.utils.ErrorCodes;
import com.dhcc.ms.utils.dto.BaseListDto;
import com.dhcc.ms.utils.dto.BaseMetaDto;
import com.dhcc.ms.utils.dto.BaseObjectDto;
import com.dhcc.ms.utils.dto.BasePageDto;
import com.dhcc.ms.utils.dto.ListRespDto;
import com.dhcc.ms.utils.dto.MetaDataDto;

@RestController
@RequestMapping(value = "/users")
public class UserController {
	@Autowired
	private Session session;
	//标准的REST接口书写方式-开始
/*  @RequestMapping(value = "/user", method = RequestMethod.PUT)
	List<User> addUser(@RequestBody User user) {
		return null;
	}
	
	@RequestMapping(value = "/{user}", method = RequestMethod.DELETE)
	public boolean deleteUser(@PathVariable String userId) {
		return false;
	}
	
	@RequestMapping(value = "/{user}", method = RequestMethod.POST)
	public User updateUser(@PathVariable String userId) {
		User user = new User();
		return null;
	}

	@RequestMapping(value = "/{user}", method = RequestMethod.POST)
	public User getUser(@PathVariable String userId) {
		User user = new User();
		return null;
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	List<User> getAllUsers(@PathVariable String userId) {
		return null;
	}*/
	//标准的REST接口书写方式-结束
	
	
	@Autowired
	private UserService userService;
	//偏业务型系统的REST接口书写方式
	@RequestMapping(value = "/adduser", method = RequestMethod.POST)
	List<User> addUser(@RequestBody UserDtoResp user) {
		return null;
	}
	
	@RequestMapping(value = "/deleteuser", method = RequestMethod.POST)
	public boolean deleteUser(@PathVariable String userId) {
		return false;
	}
	
	@RequestMapping(value = "/updateuser", method = RequestMethod.PATCH)
	public BaseMetaDto updateUser(@RequestBody UserDtoReq user,HttpServletRequest request) throws CommonException {
		UserDtoResp userSession = (UserDtoResp)session.getAttribute(request, Constants.USER_SESSION_KEY);
		user.setUserId(userSession.getUserId());
		int count = userService.updateUser(user);
		BaseMetaDto resp = new BaseMetaDto();
		if(count!=1) {
			throw new CommonException(ImsErrorCodes.CODE_120200,ImsErrorCodes.CODE_120200_MSG);
		}
		return resp;
	}
	
	@RequestMapping(value = "/changepasswd", method = RequestMethod.PATCH)
	public BaseMetaDto changepasswd(@RequestBody UserChangePwDtoReq userChangPw,HttpServletRequest request) throws CommonException {
		UserDtoResp userSession = (UserDtoResp)session.getAttribute(request, Constants.USER_SESSION_KEY);
		String userId = userSession.getUserId();
		UserDtoResp user = userService.getUser(userId);
		BaseMetaDto resp = new BaseMetaDto();
		if(!user.getPassword().equals(userChangPw.getOldPassword())) {
			throw new CommonException(ImsErrorCodes.CODE_120201,ImsErrorCodes.CODE_120201_MSG);
		}
		if(!userChangPw.getPassword().equals(userChangPw.getPasswordRepeat())) {
			throw new CommonException(ImsErrorCodes.CODE_120202,ImsErrorCodes.CODE_120202_MSG);
		}
		UserDtoReq userNew = new UserDtoReq();
		userNew.setUserId(userId);
		userNew.setPassword(userChangPw.getPassword());
		int count = userService.updateUser(userNew);
		if(count!=1) {
			throw new CommonException(ImsErrorCodes.CODE_120200,ImsErrorCodes.CODE_120200_MSG);
		}
		return resp;
	}

	@RequestMapping(value = "/getuser/{userId}", method = RequestMethod.GET)
	public BaseObjectDto<UserDtoResp> getUser(@PathVariable String userId) throws CommonException {
		UserDtoResp user = userService.getUser(userId);
		user.setPassword("");
		return new BaseObjectDto<UserDtoResp>(user);
	}

	@RequestMapping(value = "/getusers", method = RequestMethod.GET)
	BaseListDto<UserDtoResp> getAllUsers() throws CommonException {
		List<UserDtoResp> userlist = userService.getAllUsers();
		ListRespDto<UserDtoResp> userListResp = new ListRespDto<UserDtoResp>(userService.getAllUsers(),userlist.size());
		return new BaseListDto<UserDtoResp>(userListResp);
	}
	
	@RequestMapping(value = "/getusersbypage", method = RequestMethod.POST)
	BaseListDto<UserDtoResp> getUsersByPage(@RequestBody UserDtoPageReq page) throws CommonException {
		ListRespDto<UserDtoResp> userListResp = userService.getUsersByPage(page);
		return new BaseListDto<UserDtoResp>(userListResp);
	}
}
