package com.szy.service;

/**
 *
 * Created by Administrator on 2016/10/18.
 */
public interface StudentInfoService {

    String getPhoneNumber(String number) throws Exception;

    void updatePhoneNUmber(String number, String phoneNumber) throws Exception;
}
