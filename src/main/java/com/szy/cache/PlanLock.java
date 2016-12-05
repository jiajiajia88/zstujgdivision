package com.szy.cache;

/**
 * 分流计划改写时的只读锁
 * Created by shizhouyong on 2016/12/2.
 */
public class PlanLock {

    private int userId;

    private int planId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }
}
