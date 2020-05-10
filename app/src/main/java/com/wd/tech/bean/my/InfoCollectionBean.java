package com.wd.tech.bean.my;

import java.util.List;

/**
 * Time:2020/4/29 0029下午 9:04202004
 * 邮箱:2094158527@qq.com
 * 作者:朱贵全
 * 类功能:我的收藏
 */
public class InfoCollectionBean {

    /**
     * result : [{"createTime":1588666641000,"id":4366,"infoId":5,"thumbnail":"https://img.huxiucdn.com/article/cover/201808/28/092219882657.jpg?imageView2/1/w/710/h/400/|imageMogr2/strip/interlace/1/quality/85/format/jpg","title":"华大基因\u201c圈地\u201d真相"},{"createTime":1588083814000,"id":4310,"infoId":65,"thumbnail":"http://www.itbear.com.cn/upload/2020-04/200424150031177.png","title":"2019年Q3智能音箱数据发布，天猫精灵再次国内第一"}]
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
         * createTime : 1588666641000
         * id : 4366
         * infoId : 5
         * thumbnail : https://img.huxiucdn.com/article/cover/201808/28/092219882657.jpg?imageView2/1/w/710/h/400/|imageMogr2/strip/interlace/1/quality/85/format/jpg
         * title : 华大基因“圈地”真相
         */

        private long createTime;
        private int id;
        private int infoId;
        private String thumbnail;
        private String title;

        public long getCreateTime() {
            return createTime;
        }

        public void setCreateTime(long createTime) {
            this.createTime = createTime;
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

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
