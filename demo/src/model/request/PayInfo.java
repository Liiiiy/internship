package model.request;

import dao.impl.OrderDao;
import model.Order;

public class PayInfo extends Order {

    private Integer orderId = getId();

    private Double amountPaid;

    private int payType;

    public int getPayType() {
        return payType;
    }

    public void setPayType(int payType) {
        this.payType = payType;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

}
