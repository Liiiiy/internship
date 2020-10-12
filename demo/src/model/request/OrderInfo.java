package model.request;

import model.Order;
import model.OrderDetails;

import java.util.Date;
import java.util.List;

public class OrderInfo extends Order {

    private List<ProductInfo> productList;

    public List<ProductInfo> getProductList() {
        return productList;
    }

    public void setProductList(List<ProductInfo> productList) {
        this.productList = productList;
    }

    public static class ProductInfo {

        private Integer productId;

        private Integer quantity;

        public Integer getProductId() {
            return productId;
        }

        public void setProductId(Integer productId) {
            this.productId = productId;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

    }
}
