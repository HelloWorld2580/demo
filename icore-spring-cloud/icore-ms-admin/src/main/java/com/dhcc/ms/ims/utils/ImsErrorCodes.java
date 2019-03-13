package com.dhcc.ms.ims.utils;

public interface ImsErrorCodes {
	//错误码错误消息规范：前两位代表微服务类型，中间两位代表业务类型，后两位代表错误类型，000000代表成功;
	//全局错误码请查看com.dhcc.cms.utils.ErrorCodes.java
	    
    //12：综合管理平台
    //12：用户服务，01：用户认证相关，00：用户密码错误错误码
    String CODE_120100 = "120100";
    String CODE_120100_MSG = "用户名密码错误";
    //12：用户服务，02：用户信息修改相关，00：用户密码错误错误码
    String CODE_120200 = "120200";
    String CODE_120200_MSG = "用户信息修改失败";
    String CODE_120201 = "120201";
    String CODE_120201_MSG = "用户旧密码错误";
    String CODE_120202 = "120202";
    String CODE_120202_MSG = "两次密码不匹配";
    
    //              03：服务实例相关
    String CODE_120300 = "120300";
    String CODE_120300_MSG = "服务实例权重修改失败";

}
