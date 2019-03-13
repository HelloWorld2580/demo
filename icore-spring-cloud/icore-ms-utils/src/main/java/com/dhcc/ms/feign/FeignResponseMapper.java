package com.dhcc.ms.feign;

import java.lang.reflect.Type;

import feign.Response;

public interface FeignResponseMapper {
    Response map(Response response, Type type);
}
