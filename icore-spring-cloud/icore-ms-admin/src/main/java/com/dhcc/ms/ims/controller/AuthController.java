package com.dhcc.ms.ims.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dhcc.ms.exception.CommonException;
import com.dhcc.ms.ims.dto.request.LoginDtoReq;
import com.dhcc.ms.ims.dto.response.UserDtoResp;
import com.dhcc.ms.ims.service.AuthenticationService;
import com.dhcc.ms.ims.session.Session;
import com.dhcc.ms.ims.utils.Constants;
import com.dhcc.ms.utils.dto.BaseMetaDto;
import com.dhcc.ms.utils.dto.BaseObjectDto;

@RestController
public class AuthController {
	private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);
    
    @Autowired
    private AuthenticationService authService;
    @Autowired
    private Session session;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public BaseObjectDto<UserDtoResp> login(@Valid @RequestBody LoginDtoReq loginDtoReq,
            HttpServletRequest request) throws CommonException {
    	UserDtoResp loginDtoResp = new UserDtoResp();
    	loginDtoResp = authService.login(loginDtoReq);
    	session.setAttribute(request, Constants.USER_SESSION_KEY, loginDtoResp);
        return new BaseObjectDto<UserDtoResp>(loginDtoResp);
    }


    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    @ResponseBody
    public BaseMetaDto logout(HttpServletRequest request) {
    	UserDtoResp userVo = (UserDtoResp)session.getAttribute(request, Constants.USER_SESSION_KEY);
        if (userVo != null) {
            try {
                authService.logout();
            } catch (CommonException e) {
            	LOGGER.error("logout failed!",e);
            }
        }
        session.invalidate(request);
        return new BaseMetaDto();
    }
}
