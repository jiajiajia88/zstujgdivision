package com.szy.cache;

import com.szy.util.MD5Util;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

/**
 * 用户会话缓存信息
 * Created by shizhouyong on 2016/12/2.
 */
public class Session {

    private int userId;
    private String number;
    private String name;
    private long loginTime;
    private String verify;
    private int limit;
    private transient String key;

    private static String prefix = "^Sess_io@n";
    private static Random random = new Random();

    //todo:临时方式，加密方式后期改动
    public String createSessionKey(int userId) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        verify =  String.format("%08x", random.nextInt(0x7FFFFFFF)) ;
        key = MD5Util.md5(prefix + userId);
        return key + verify;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(long loginTime) {
        this.loginTime = loginTime;
    }

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
