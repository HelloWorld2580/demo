package com.dhcc.ms.ims.dto.request;

import javax.validation.constraints.NotNull;

public class UserChangePwDtoReq {
	private String userId;
	@NotNull
    private String oldPassword;
	@NotNull
	private String password;
	@NotNull
	private String passwordRepeat;

	public String getOldPassword() {
		return oldPassword;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPasswordRepeat() {
		return passwordRepeat;
	}
	public void setPasswordRepeat(String passwordRepeat) {
		this.passwordRepeat = passwordRepeat;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	

}
