package com.wd.tech.bean.xiaoxi;

/**
 * date:2020/4/29 0029
 * author:朱贵全(Administrator)
 * function:根据手机号查询用户信息
 */
public class InfoSeleFriendBean {

    /**
     * result : {"birthday":823190400000,"email":"1145886822@qq.com","headPic":"http://mobile.bwstudent.com/images/tech/head_pic/2020-04-17/20200417200148.png","integral":10,"nickName":"诗和远方","phone":"18532186702","sex":1,"signature":"剑与远征","userId":1372,"userName":"q60Anb18532186702","whetherFaceId":2,"whetherVip":2}
     * message : 查询成功
     * status : 0000
     */

    private ResultBean result;
    private String message;
    private String status;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

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

    public static class ResultBean {
        /**
         * birthday : 823190400000
         * email : 1145886822@qq.com
         * headPic : http://mobile.bwstudent.com/images/tech/head_pic/2020-04-17/20200417200148.png
         * integral : 10
         * nickName : 诗和远方
         * phone : 18532186702
         * sex : 1
         * signature : 剑与远征
         * userId : 1372
         * userName : q60Anb18532186702
         * whetherFaceId : 2
         * whetherVip : 2
         */

        private long birthday;
        private String email;
        private String headPic;
        private int integral;
        private String nickName;
        private String phone;
        private int sex;
        private String signature;
        private int userId;
        private String userName;
        private int whetherFaceId;
        private int whetherVip;

        public long getBirthday() {
            return birthday;
        }

        public void setBirthday(long birthday) {
            this.birthday = birthday;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public int getIntegral() {
            return integral;
        }

        public void setIntegral(int integral) {
            this.integral = integral;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public int getWhetherFaceId() {
            return whetherFaceId;
        }

        public void setWhetherFaceId(int whetherFaceId) {
            this.whetherFaceId = whetherFaceId;
        }

        public int getWhetherVip() {
            return whetherVip;
        }

        public void setWhetherVip(int whetherVip) {
            this.whetherVip = whetherVip;
        }
    }
}
