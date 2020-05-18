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

    //资讯
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
    //VIP
    String FIND_USER_INTEGRAL="techApi/user/verify/v1/findUserIntegral";//查询用户积分
    String FIND_VIP_COMMODITY="techApi/tool/v1/findVipCommodityList";//查询所有会员商品
    String BUY_VIP = "techApi/tool/verify/v1/buyVip";//用户购买VIP
    String PAY_VIP = "techApi/tool/verify/v1/pay";//支付

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
    //收藏
    String Base_Info_Collection="techApi/user/verify/v1/findAllInfoCollection";// get page  count
    //关注
    String Base_Find_Follow="techApi/user/verify/v1/findFollowUserList";// get  page count
    //通知
    String Base_Tong_Zhi="techApi/tool/verify/v1/findSysNoticeList"; // get  page  count
    //我的帖子
    String MY_Post = "techApi/community/verify/v1/findMyPostById";
    //删除帖子
    String DELETE_POST = "techApi/community/verify/v1/deletePost";
    //根据手机号
    String BASE_SEUSER_BYPHONE="techApi/user/verify/v1/findUserByPhone";
    //创建群聊
    String BASE_CREATE_GROUP="techApi/group/verify/v1/createGroup";
    //查询用户任务列表
    String TASK_LIST = "techApi/user/verify/v1/findUserTaskList";
    //做任务
    String DO_TASK = "techApi/user/verify/v1/doTheTask";
    //签到
    String USER_SIGN = "techApi/user/verify/v1/userSign";
    //当天签到状态
    String FIND_USER_SIGN = "techApi/user/verify/v1/findUserSignStatus";
    //查询当月签到日期
    String FIND_RECORDING = "techApi/user/verify/v1/findUserSignRecording";
    //查询用户积分
    String USER_INTEGRAL = "techApi/user/verify/v1/findUserIntegral";
    //用户积分详情
    String USER_RECORD = "techApi/user/verify/v1/findUserIntegralRecord";
    //查询连续签到日期
    String SIGN_DAY = "techApi/user/verify/v1/findContinuousSignDays";

    //查询好友信息
    String BASE_FRIENDINFO_ID="techApi/user/verify/v1/queryFriendInformation";
    //查询好友对话记录
    String BASE_CHAT="techApi/chat/verify/v1/findDialogueRecordPageList";
    //查询好友聊天记录
    String BASE_CHATHISTORY="techApi/chat/verify/v1/findChatRecordPageList";
    //发送消息
    String BASE_SEND_MSG="techApi/chat/verify/v1/sendMessage";
    //查询所有加入的群组
    String BASE_ALLGROUPS="techApi/group/verify/v1/findUserJoinedGroup";
    //查看群组聊天记录
    String BASE_GROUP_HISTORY="techApi/group/verify/v1/findGroupChatRecordPage";
    //查询群组详细信息
    String BASE_GROUP_DETAILS="techApi/group/verify/v1/findGroupInfo";
    //退出群组
    String BASE_BACK_GROUP="techApi/group/verify/v1/retreat";
    //解散群组
    String BASE_DELETE_GROUP="techApi/group/verify/v1/disbandGroup";
    //修改群简介
    String BASE_UPDATE_JIANJIE="techApi/group/verify/v1/modifyGroupDescription";
    //申请进群
    String BASE_ADD_GROUP="techApi/group/verify/v1/applyAddGroup";
    //群通知
    String BASE_GROUP_NOTICE="techApi/group/verify/v1/findGroupNoticePageList";
    //审核群申请
    String BASE_AUDIT="techApi/group/verify/v1/reviewGroupApply";
    //检测是否是我的好友
    String BASE_ISFRIEND="techApi/chat/verify/v1/checkMyFriend";
    //添加好友
    String BASE_ADD_FRIEND="techApi/chat/verify/v1/addFriend";
    //查询群组所有用户
    String BASE_QUERY_ALLUSERS="techApi/group/verify/v1/findGroupMemberList";
    //调整群成员角色
    String BASE_UPDATE_GROUP="techApi/group/verify/v1/modifyPermission";
    //移除群成员
    String BASE_DETELE_GROUPUSER="techApi/group/verify/v1/removeGroupMember";
    //邀请加群
    String BASE_INVITE_GROUP="techApi/group/verify/v1/inviteAddGroup";
    //删除好友
    String BASE_DELETE_FRIEND="techApi/chat/verify/v1/deleteFriendRelation";
    //删除好友聊天记录
    String BASE_DELETE_HISTORY="techApi/chat/verify/v1/deleteChatRecord";

}
