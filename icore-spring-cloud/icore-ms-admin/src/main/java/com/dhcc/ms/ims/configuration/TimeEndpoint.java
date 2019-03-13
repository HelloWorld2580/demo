package com.dhcc.ms.ims.configuration;

import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author WangPeng
 * @ClassName: TimeEndpoint
 * @Description: 自定义Actuator节点
 * @Package com.dhcc.ms.ims.configuration
 * Copyright DHC CO.LTD 2018
 * @date 2018/4/26
 */
@ConfigurationProperties(prefix = "endpoints.time")
public class TimeEndpoint extends AbstractEndpoint<Map<String, Object>> {
    public TimeEndpoint() {
        super("time",false);
    }

    @Override
    public Map<String, Object> invoke() {
        Map<String, Object> result = new HashMap<String, Object>();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        String date = df.format(new Date());// new Date()为获取当前系统时间，也可使用当前时间戳
        result.put("time", date.toString());
        result.put("timestamp", System.currentTimeMillis());
        return result;
    }
}
