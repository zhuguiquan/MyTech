package com.wd.tech.bean;

/*
 *@auther:王彦敏
 *@Date: 2020/4/29
 *@Time:19:19
 *@Description:
 * */
public class BuyVipBean {
    /**
     * orderId : 20181019120947890
     * message : 下单成功
     * status : 0000
     */

    private String orderId;
    private String message;
    private String status;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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
}
