package model;

import java.io.Serializable;

public class Product implements Serializable {

    private Integer id;

    private String name;

    private Double price;

    private Integer merchantId;

    private Integer merchantInventory;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(Integer merchantId) {
        this.merchantId = merchantId;
    }

    public Integer getMerchantInventory() {
        return merchantInventory;
    }

    public void setMerchantInventory(Integer merchantInventory) {
        this.merchantInventory = merchantInventory;
    }

    public static void main(String[] args) {
        Product product = new Product();
        product.setName("fdsads");
    }
}
