package model;

import java.util.Date;

public class AccountRecords {

    public static final int EXPENDITURE = -1;
    public static final int EARNING = 1;

    private int inOrOut;

    private String type;

    private Integer source;

    private Double amount;

    private Date payTime;

    private Integer expendAccount;

    private String expendAccountType;

    private Integer earnAccount;

    private String earnAccountType;


    public int getInOrOut() {
        return inOrOut;
    }

    public void setInOrOut(int inOrOut) {
        this.inOrOut = inOrOut;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Integer getExpendAccount() {
        return expendAccount;
    }

    public void setExpendAccount(Integer expendAccount) {
        this.expendAccount = expendAccount;
    }

    public String getExpendAccountType() {
        return expendAccountType;
    }

    public void setExpendAccountType(String expendAccountType) {
        this.expendAccountType = expendAccountType;
    }

    public Integer getEarnAccount() {
        return earnAccount;
    }

    public void setEarnAccount(Integer earnAccount) {
        this.earnAccount = earnAccount;
    }

    public String getEarnAccountType() {
        return earnAccountType;
    }

    public void setEarnAccountType(String earnAccountType) {
        this.earnAccountType = earnAccountType;
    }
}
