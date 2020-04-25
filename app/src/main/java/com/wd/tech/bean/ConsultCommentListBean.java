package com.wd.tech.bean;

import java.util.List;

/*
 *@auther:王彦敏
 *@Date: 2020/4/23
 *@Time:22:44
 *@Description:
 * */
public class ConsultCommentListBean {

    /**
     * result : [{"commentTime":1587630219000,"content":"6465664","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-04-22/20200422003552.jpg","id":2796,"infoId":1,"nickName":"花花公子","userId":1402},{"commentTime":1587437386000,"content":"不错","headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","id":2792,"infoId":1,"nickName":"袁晨冉","userId":1401},{"commentTime":1587129302000,"content":"加油","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-04-17/20200417204020.png","id":2788,"infoId":1,"nickName":"幻影月缺醉几何","userId":1383},{"commentTime":1580561980000,"content":"非常好","headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","id":2684,"infoId":1,"nickName":"{{nickName}}","userId":1193},{"commentTime":1580561946000,"content":"非常好","headPic":"http://mobile.bwstudent.com/images/tech/default/tech.jpg","id":2683,"infoId":1,"nickName":"{{nickName}}","userId":1193}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * commentTime : 1587630219000
         * content : 6465664
         * headPic : http://mobile.bwstudent.com/images/tech/head_pic/2020-04-22/20200422003552.jpg
         * id : 2796
         * infoId : 1
         * nickName : 花花公子
         * userId : 1402
         */

        private long commentTime;
        private String content;
        private String headPic;
        private int id;
        private int infoId;
        private String nickName;
        private int userId;

        public long getCommentTime() {
            return commentTime;
        }

        public void setCommentTime(long commentTime) {
            this.commentTime = commentTime;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getInfoId() {
            return infoId;
        }

        public void setInfoId(int infoId) {
            this.infoId = infoId;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }
    }
}
