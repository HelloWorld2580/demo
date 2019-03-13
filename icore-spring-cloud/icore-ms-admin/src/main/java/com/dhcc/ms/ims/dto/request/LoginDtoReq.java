
package com.dhcc.ms.ims.dto.request;

import javax.validation.constraints.NotNull;

public class LoginDtoReq {
	@NotNull
    String username;
	@NotNull
    String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
