package com.wd.tech.weight;

/**
 * date:2020/4/18
 * author:朱贵全(Lenovo)
 * function:
 */
public interface MyUrls {
    //公共网址
    String BASE_URL="https://mobile.bwstudent.com/";
    String BASE_LOGIN="techApi/user/v1/login";
    String REGISTER_URL = "techApi/user/v1/register";//注册  post请求 参数 phone nickName pwd（加密后的）
    String BASE_BYID = "techApi/user/verify/v1/getUserInfoByUserId";//根据用户id查询信息  getHeader请求
    //微信登录
    String WEIXINLOG_URL="techApi/user/v1/weChatLogin";
    //查询好友通知
    String BASE_FRIEND_NOTICE="techApi/chat/verify/v1/findFriendNoticePageList";
    //查询分组
    String BASE_FIND_ALLGROUP="techApi/chat/verify/v1/findFriendGroupList";
    //查询分组下所有好友信息
    String BASE_FINDMAN_BYGROUP="techApi/chat/verify/v1/findFriendListByGroupId";
    //查询我的好友列表 get searchName
    String BASE_FIND_FRIEND="techApi/chat/verify/v1/searchFriend";




}
