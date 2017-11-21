package com.zhuye.hougong.contants;

/**
 * Created by zzzy on 2017/11/20.
 */

public class Contants {
    public static final String BASE_URL = "http://192.168.0.136/gong";
    //登录注册接口
    //退出
    public static final String logout = BASE_URL+"/index.php/home/passportapi/logout";
    public static final String forget_code = BASE_URL+"/index.php/home/passportapi/forget_code";
    public static final String forget_pass = BASE_URL+"/index.php/home/passportapi/forget_pass";
    public static final String LOGIN_URL = BASE_URL+"/index.php/home/passportapi/mobile_login";
    public static final String REGEIST_URL = BASE_URL+"/index.php/home/passportapi/mobile_register";
    public static final String GET_REGEIST_URL = BASE_URL+"/index.php/home/passportapi/code";

    //个人中心接口
    //个人资料
    public static final String information = BASE_URL+"/index.php/home/user/information";
    //意见反馈
    public static final String feedback = BASE_URL+"/index.php/home/user/feedback";
    //修改用户头像
    public static final String user_face = BASE_URL+"/index.php/home/user/face";
    //用户隐身
    public static final String stealth = BASE_URL+"/index.php/home/user/stealth";

}
