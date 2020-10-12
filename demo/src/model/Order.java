package model;

import java.util.Date;

public class Order {

    public static final Integer ORDER_CREATED = 0;
    public static final Integer ORDER_PAID = 1;
    public static final Integer PAY_STATUS_UNPAID = 0;
    public static final Integer PAY_STATUS_PAID = 1;
    public static final Integer PAY_BY_CARD = 1;
    public static final Integer PAY_BY_ALIAPAY= 2;
    public static final Integer PAY_BY_WECHAT = 3;


    private Integer id;

    private Integer detailsNum;

    private Double totalPrice;

    private Integer purchaserId;

    private String receiver;

    private String receiverContactNum;

    private String receiverAddress;

    private Date createTime;

    private Integer  orderStatus;

    private Integer paymentStatus;

    private Integer paymentType;

    private Date payTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDetailsNum() {
        return detailsNum;
    }

    public void setDetailsNum(Integer detailsNum) {
        this.detailsNum = detailsNum;
    }

    public Double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getPurchaserId() {
        return purchaserId;
    }

    public void setPurchaserId(Integer purchaserId) {
        this.purchaserId = purchaserId;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getReceiverContactNum() {
        return receiverContactNum;
    }

    public void setReceiverContactNum(String receiverContactNum) {
        this.receiverContactNum = receiverContactNum;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(Integer paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Integer getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(Integer paymentType) {
        this.paymentType = paymentType;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public static Integer getOrderCreated() {
        return ORDER_CREATED;
    }

    public static Integer getOrderPaid() {
        return ORDER_PAID;
    }

    public static Integer getPayStatusUnpaid() {
        return PAY_STATUS_UNPAID;
    }

    public static Integer getPayStatusPaid() {
        return PAY_STATUS_PAID;
    }

    public static Integer getPayByCard() {
        return PAY_BY_CARD;
    }

    public static Integer getPayByAliapay() {
        return PAY_BY_ALIAPAY;
    }

    public static Integer getPayByWechat() {
        return PAY_BY_WECHAT;
    }
}
