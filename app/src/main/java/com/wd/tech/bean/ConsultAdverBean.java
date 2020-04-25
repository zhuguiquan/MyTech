package com.wd.tech.bean;

/*
 *@auther:王彦敏
 *@Date: 2020/4/23
 *@Time:22:53
 *@Description:
 * */
public class ConsultAdverBean {

    /**
     * result : {"content":"八维教育","id":1,"pic":"http://mobile.bwstudent.com/images/tech/ad/bw.png","url":"http://www.bwie.com"}
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
         * content : 八维教育
         * id : 1
         * pic : http://mobile.bwstudent.com/images/tech/ad/bw.png
         * url : http://www.bwie.com
         */

        private String content;
        private int id;
        private String pic;
        private String url;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
