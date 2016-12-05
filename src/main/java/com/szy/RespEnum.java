package com.szy;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 *
 * Created by shizhouyong on 2016/12/2.
 */
public enum RespEnum {

    //成功
    SUCCESS(0,"success"),

    /*
	 * 公共错误码
	 */
    PARAMETER_MiSS		(0x00001000, "miss required parameter"),	//缺少必要参数
    REQUST_TIMEOUT		(0x00001001, "request time out"),			//请求超时
    UNKNOWN_ERROR		(0x00001002, "unknown error"),				//未知错误
    NOT_LOGIN			(0x00001003, "not logged in"),				//未登录

    /*
	 * 业务错误码
	 */
    NO_ACCESS           (0x00010001, "no access"),                  //没有权限
    NO_USERNAME	(0x00010002, "no username"),		//登录用户名或者密码错误
    NAME_DUPLICATE		(0x00010003, "name duplicate"),				//用户名重复
    PASSWD_ERR			(0x00010004, "password error"),				//密码错误



    END(0x7FFFFFFF, "end");	//最大错误码

    private Response response = new Response();

    private RespEnum(int code, String info) {
        response.setRetCode(code);
        response.setRetInfo(info);
    }

    public Response getResponse(){
        return response;
    }

    public int getRetCode() {
        return response.getRetCode();
    }

    public String getRetInfo() {
        return response.getRetInfo();
    }

    @JsonValue
    public Response toMap() {
        return response;
    }

    @Override
    public String toString(){
        return JSON.toJSONString(response);
    }
}
