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
    String CONSULT_BANNER="techApi/information/v1/bannerShow";//轮播图展示 get无参
    String CONSULT_CONSULTLIST="techApi/information/v1/infoRecommendList";//资讯推荐列表展示
    String CONSULT_DETAILS="techApi/information/v1/findInformationDetails";//资讯详情展示
    String CONSULT_MODULE="techApi/information/v1/findAllInfoPlate";//所有板块查询
    String CONSULT_SHARE="techApi/information/v1/updateInfoShareNum";//资讯分享
    String CONSULT_DIANZAN="techApi/information/verify/v1/addGreatRecord";//资讯点赞
    String CONSULT_CANCEL="techApi/user/verify/v1/cancelCollection";//取消点赞
    String CONSULT_COMMENT="techApi/information/verify/v1/addInfoComment";//评论
    String Base_Collecation="techApi/user/verify/v1/addCollection";//收藏  post  infoId
    String Base_Cancal_Collecation="techApi/user/verify/v1/cancelCollection";//取消收藏  delete infoId
    String CONSULT_COMMENTLIST="techApi/information/v1/findAllInfoCommentList";//资讯评论列表
    String CONSULT_TITLE="techApi/information/v1/findInformationByTitle";//根据标题模糊查询
    String CONSULT_ADVERTISING="techApi/information/v1/findInfoAdvertising";//资讯广告
    String CONSULT_INTEGRAL="techApi/information/verify/v1/infoPayByIntegral";//积分兑换
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
    //社区列表
    String BASE_COMMUNITYLIST="techApi/community/v1/findCommunityList";
    //点赞
    String BASE_COMMUNITY_ZAN="techApi/community/verify/v1/addCommunityGreat";
    //取消点赞
    String BASE_DELETE_ZAN="techApi/community/verify/v1/cancelCommunityGreat";
    //发表帖子
    String BASE_POST="techApi/community/verify/v1/releasePost";
    //社区用户评论列表
    String BASE_COMMUNI_PL="techApi/community/v1/findCommunityUserCommentList";
    //社区评论
    String BASE_FILM="techApi/community/verify/v1/addCommunityComment";
    //查询用户帖子
    String BASE_USER_COM="techApi/community/verify/v1/findUserPostById";


}
