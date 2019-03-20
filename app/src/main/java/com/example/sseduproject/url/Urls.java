package com.example.sseduproject.url;

import android.content.Context;



public class Urls {

    public final static String base_url= "http://99866770.vicp.net:3000/api";//测试环境，手机后台
//    public final static String base_url= "http://192.168.0.80:8888/api";//测试环境，手机后台

    public final static String register = base_url + "/v1/users/register";
    public final static String login = base_url + "/v1/users/login";//密码
    public final static String login_captcha = base_url + "/v1/users/login_captcha";//登录（验证码）
    public final static String captcha = base_url + "/v1/users/captcha";//获取验证码
    public final static String forget_password = base_url + "/v1/users/forget_password";//忘记密码


    public final static String index = base_url + "/v1/base/index";//忘记密码



}
