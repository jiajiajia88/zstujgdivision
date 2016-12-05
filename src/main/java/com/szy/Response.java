package com.szy;

/**
 * 响应
 * Created by shizhouyong on 2016/12/2.
 */
public class Response {
    protected int retCode = 0;
    protected String retInfo;

    public String getRetInfo() {
        return retInfo;
    }
    public void setRetInfo(String retInfo) {
        this.retInfo = retInfo;
    }
    public int getRetCode() {
        return retCode;
    }
    public void setRetCode(int retcode) {
        this.retCode = retcode;
    }
}
