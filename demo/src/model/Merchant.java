package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Merchant {

    public static String DIGEST = "MD5";

    private Integer id;

    private String ownerName;

    private String merchantAddress;

    private String contactNum;

    private Date createTime;

    private String storeName;

    private String contactName;

    private String account;

    private String password;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getMerchantAddress() {
        return merchantAddress;
    }

    public void setMerchantAddress(String merchantAddress) {
        this.merchantAddress = merchantAddress;
    }

    public String getContactNum() {
        return contactNum;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


/*
    public static void main(String[] args) throws ParseException {
        Merchant merchant = new Merchant();
        merchant.setContactName("Aaaaa");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(sdf.format(new Date()));

        Date date = sdf.parse("2020092309:39:36");
        merchant.setCreateTime(date);

        merchant.setContactName("fjashfsa");

    }
*/
}
