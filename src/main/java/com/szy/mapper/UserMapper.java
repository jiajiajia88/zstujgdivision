package com.szy.mapper;

import com.szy.entity.UserManager;
import com.szy.entity.UserStudent;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户相关持久层接口
 * Created by Administrator on 2016/10/16.
 */
@Mapper
public interface UserMapper {

    /**
     * 向tb_user_student插入学生用户
     * @param students
     * @throws Exception
     */
    void insertUserStudent(UserStudent students) throws Exception;

    /**
     * 查找出所有学生
     * @return
     * @throws Exception
     */
    UserStudent findUserStudentsAll() throws Exception;

    /**
     * 根据用户名判断用户是否存在
     * @param number
     * @return
     * @throws Exception
     */
    int ifExistsStudent(String number) throws Exception;

    /**
     * 根据用户名密码判断密码是否正确
     * @param number
     * @param pwd
     * @return
     * @throws Exception
     */
    int ifExistsStudentWithPwd(String number, String pwd) throws Exception;

    /**
     * 根据用户名获得映射
     */
    UserStudent findUserStudentByNumber(String number) throws Exception;

    /**
     * 修改密码
     * @param pwd
     * @param number
     * @throws Exception
     */
    void updateStudentPwd(String number,String pwd) throws Exception;


    /**
     * 添加管理员\教师用户信息
     * @param userManager
     * @throws Exception
     */
    void insertUserManager(UserManager userManager) throws Exception;

    /**
     * 查找出所有管理员\教师用户信息
     * @return
     * @throws Exception
     */
    UserManager findUserManagersAll() throws Exception;

    /**
     * 判断是否存在管理员\教师用户
     * @param number
     * @return
     * @throws Exception
     */
    int ifExistsManager(String number) throws Exception;

    /**
     * 根据用户名密码判断密码是否正确
     * @param number
     * @param pwd
     * @return
     * @throws Exception
     */
    int ifExistsManagerWithPwd(String number, String pwd) throws Exception;

    /**
     * 根据管理员\教师用户名映射用户信息
     * @param number
     * @return
     * @throws Exception
     */
    UserManager findUserManagerByNumber(String number) throws Exception;

    /**
     * 更新管理员账号密码
     * @param pwd
     * @throws Exception
     */
    void updateManagerPwd(String number,String pwd) throws Exception;

    /**
     * 修改管理员信息
     * @param userManager
     * @throws Exception
     */
    void updateManager(UserManager userManager) throws Exception;

    /**
     * 删除管理员账号
     * @param id
     * @throws Exception
     */
    void deleteManager(int id) throws Exception;
}
