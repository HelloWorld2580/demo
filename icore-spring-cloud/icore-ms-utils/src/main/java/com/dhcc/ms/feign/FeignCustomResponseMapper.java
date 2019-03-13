package com.dhcc.ms.feign;

import java.io.IOException;
import java.lang.reflect.Type;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.dhcc.ms.exception.CommonException;
import com.dhcc.ms.filters.ServiceDataFilter;
import com.dhcc.ms.http.wrapper.ResettableStreamResponseWrapper;
import com.dhcc.ms.utils.CommonConstants;
import com.dhcc.ms.utils.ErrorCodes;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import feign.Response;
import feign.Util;

public class FeignCustomResponseMapper implements FeignResponseMapper {
    private static Logger LOGGER = LoggerFactory.getLogger(FeignCustomResponseMapper.class);

    public Response map(Response response, Type type) {
        try {
            String body = Util.toString(response.body().asReader());
            checkException(body);
            Response responseOri = response.toBuilder().body(body.getBytes()).build();
            return responseOri;
        } catch (CommonException e) {
            throw e;
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    private void checkException(String responseBody) {
        JsonObject responseJsonData = new JsonObject();
        if(!StringUtils.isEmpty(responseBody)) {
            responseJsonData = new JsonParser().parse(responseBody).getAsJsonObject();
            JsonElement element = responseJsonData.get(CommonConstants.DATAGRAM_ERROR_CODE);
            if(element !=null && !element.getAsString().equalsIgnoreCase(ErrorCodes.CODE_000000)) {
                //Got an exception
                throw new CommonException(responseJsonData.get(CommonConstants.DATAGRAM_ERROR_CODE).getAsString(),responseJsonData.get(CommonConstants.DATAGRAM_ERROR_MESSAGE).getAsString());
            }
        }
    }

}
