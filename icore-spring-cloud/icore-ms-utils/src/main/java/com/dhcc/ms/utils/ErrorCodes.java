package com.dhcc.ms.utils;

public interface ErrorCodes {
	//错误码错误消息规范：前两位代表微服务类型，中间两位代表业务类型，后两位代表错误类型，000000代表成功
	
	//00开头代表系统相关级别错误码定义
    String CODE_000000 = "000000";
    String CODE_000000_MSG = "SUCCESS";
    String CODE_000001 = "000001";
    String CODE_000001_MSG = "系统错误";
    String CODE_000002 = "000002";
    String CODE_000002_MSG = "数据不存在";
    String CODE_000003 = "000003";
    String CODE_000003_MSG = "服务降级，暂时无法提供服务";
    String CODE_000004 = "000004";
    String CODE_000004_MSG = "数据格式不合法";

}
