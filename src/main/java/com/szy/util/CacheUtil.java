package com.szy.util;

import com.szy.ZstuJgDivisionApplication;
import com.szy.cache.PlanLock;
import com.szy.cache.Session;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 由于学校数据库不支持redis，故此处缓存采用内存实现
 * Created by shizhouyong on 2016/12/2.
 */
@Component("cacheUtil")
public class CacheUtil {

    private static final String SESSION_PREF = "S_";
    private static final long SESSION_EXPIRE = 24 * 3600;

    public static final String LOCK_PREF = "L_";
    public static final long LOCK_EXPIRE = 10 * 60;

    public static final String UPLOAD_PREF = "U_";
    public static final long UPLOAD_EXPIRE =  10 * 60;

    private static Map<String, Session> sessionMap = new HashMap<>();
    private static Map<String, PlanLock> lockMap = new HashMap<>();

    public static CacheUtil create(){
        return ZstuJgDivisionApplication.getBean(CacheUtil.class);
    }

    public void delSeeion(String ss){
        sessionMap.remove(SESSION_PREF + ss);
    }

    public void setSession(Session session){
        setSession(session.getKey(),session,SESSION_EXPIRE);
    }

    public void setSession(String ss,Session session,long expire){
        try {
            String key = SESSION_PREF + ss;
            sessionMap.put(key,session);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Session getSession(String ss){
        return sessionMap.get(SESSION_PREF + ss);
    }

    public void delLock(String ll){
        lockMap.remove(LOCK_PREF + ll);
    }

    public void setPlanLock(int planId,PlanLock planLock){
        String key = LOCK_PREF + planId;
        lockMap.put(key,planLock);
    }

    public PlanLock getPlanLock(int planId){
        return lockMap.get(LOCK_PREF + planId);
    }
}
