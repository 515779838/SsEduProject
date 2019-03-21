package com.example.sseduproject.url;

public class Urls {

//    public final static String base_url= "http://99866770.vicp.net:3000/api";//测试环境，手机后台
    public final static String base_url= "http://192.168.0.80:3000/api";//测试环境，手机后台

    public final static String register = base_url + "/v1/users/register";
    public final static String login = base_url + "/v1/users/login";//密码
    public final static String login_captcha = base_url + "/v1/users/login_captcha";//登录（验证码）
    public final static String captcha = base_url + "/v1/users/captcha";//获取验证码
    public final static String forget_password = base_url + "/v1/users/forget_password";//忘记密码

    public final static String update_info = base_url + "/v1/users/update_info";//忘记密码

    public final static String index = base_url + "/v1/base/index";//首页
    public final static String provinces = base_url + "/v1/current_schools/provinces";//所在学校——省份
    public final static String target_schools = base_url + "/v1/current_schools/target_schools";//GET 所在学校
    public final static String professions = base_url + "/v1/current_schools/professions";//GET 专业


    public final static String target_school_provinces = base_url + "/v1/schools/target_school_provinces";//
    public final static String target_schools2 = base_url + "/v1/schools/target_schools";
    public final static String target_schools_search = base_url + "/v1/schools/target_schools_search";
    public final static String target_school_professions = base_url + "/v1/schools/target_school_professions";
    public final static String target_school_professions_search = base_url + "/v1/schools/target_school_professions_search";

    public final static String select_target_school_profession = base_url + "/v1/users/select_target_school_profession";
    public final static String user_info = base_url + "/v1/users/user_info";
    public final static String open_wx_login = base_url + "/v1/users/open_wx_login";




}
