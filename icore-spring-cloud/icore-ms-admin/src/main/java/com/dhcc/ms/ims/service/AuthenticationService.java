
package com.dhcc.ms.ims.service;

import com.dhcc.ms.exception.CommonException;
import com.dhcc.ms.ims.dto.request.LoginDtoReq;
import com.dhcc.ms.ims.dto.response.UserDtoResp;

public interface AuthenticationService {
    public UserDtoResp login(LoginDtoReq logonDtoReq) throws CommonException;
    public void logout() throws CommonException;
}
