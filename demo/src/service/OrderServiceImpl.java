package service;

import dao.IOrderDao;
import dao.IOrderDetailsDao;
import dao.IProductDao;
import dao.impl.OrderDao;
import dao.impl.OrderDetailsDao;
import dao.impl.ProductDao;
import exception.BaseException;
import model.Order;
import model.OrderDetails;
import model.Product;
import model.request.OrderInfo;
import model.request.PayInfo;
import org.apache.commons.collections.CollectionUtils;
import util.StringUtils;

import java.util.*;

public class OrderServiceImpl {

    IOrderDao orderDao = new OrderDao();
    IProductDao productDao = new ProductDao();
    IOrderDetailsDao orderDetailsDao = new OrderDetailsDao();

    public Order createOrder(OrderInfo orderInfo) {

        checkOrderInfo(orderInfo);

        // 初始化订单信息
        Order order = new Order();
        order.setPurchaserId(orderInfo.getPurchaserId());
        order.setReceiver(orderInfo.getReceiver());
        order.setReceiverContactNum(orderInfo.getReceiverContactNum());
        order.setReceiverAddress(orderInfo.getReceiverAddress());
        order.setDetailsNum(orderInfo.getProductList().size());

        // 生成订单，并获取订单号
        orderDao.createOrder(order);
        Integer orderId = order.getId();

        // 获取商品idList,及其对应quantity
        // 获取商品列表
        List<Integer> idList = new LinkedList<>();
        Map<Integer, Integer> quantityMap = new HashMap<>();

        for (OrderInfo.ProductInfo productInfo : orderInfo.getProductList()) {
            idList.add(productInfo.getProductId());
            quantityMap.put(productInfo.getProductId(), productInfo.getQuantity());
        }
        List<Product> products = productDao.selectProductsById(idList);
        Double totalPrice = 0.0;

        // 生成orderdetails
        List<OrderDetails> orderDetails = new LinkedList<>();
        for (Product product : products) {
            OrderDetails orderDetail = new OrderDetails();
            orderDetail.setOrderId(orderId);
            orderDetail.setProductId(product.getId());
            orderDetail.setPrice(product.getPrice());
            orderDetail.setQuantity(quantityMap.get(product.getId()));
            orderDetails.add(orderDetail);

            // 计算订单总金额
            totalPrice += product.getPrice() * quantityMap.get(product.getId());
        }
        orderDetailsDao.createOrderDetails(orderDetails);

        order.setOrderStatus(Order.ORDER_CREATED);
        order.setPaymentStatus(Order.PAY_STATUS_UNPAID);
        order.setTotalPrice(totalPrice);
        orderDao.updateObjById(order);

        return order;
    }

    public boolean payForOrder(PayInfo payInfo) {

        boolean payStatus = false;
        Order order = new Order();

        if (checkPayInfo(payInfo)) {
            order.setId(payInfo.getOrderId());
            order.setPayTime(new Date());
            order.setPaymentStatus(Order.PAY_STATUS_PAID);
            order.setPaymentType(payInfo.getPayType());
            orderDao.updateObjById(order);
        }

        payStatus = Order.PAY_STATUS_PAID.equals(orderDao.selectById(payInfo.getOrderId()).getPaymentStatus());
        if (payStatus) {
            order.setOrderStatus(Order.ORDER_PAID);
            orderDao.updateObjById(order);
        }

        return payStatus;
    }



    private static void checkOrderInfo(OrderInfo orderInfo) {
        if (orderInfo == null) {
            throw new BaseException(-1, "对不起，请填写订单信息");
        }
        if (CollectionUtils.isEmpty(orderInfo.getProductList())) {
            throw new BaseException(-1 , "对不起，请添加商品信息");
        }
        if (orderInfo.getPurchaserId() == null) {
            throw new BaseException(-1 , "对不起，请添加购买人");
        }
        if (StringUtils.isEmpty(orderInfo.getReceiver())) {
            throw new BaseException(-1 , "对不起，请添加收货人");
        }
        if (StringUtils.isEmpty(orderInfo.getReceiverContactNum())) {
            throw new BaseException(-1 , "对不起，请添加收货人联系方式");
        }
        if (StringUtils.isEmpty(orderInfo.getReceiverAddress())) {
            throw new BaseException(-1 , "对不起，请添加收货地址");
        }
    }

    // 检查支付信息，并核对实付金额是否匹配订单金额
    private static boolean checkPayInfo(PayInfo payInfo) {
        if (payInfo == null) {
            throw new BaseException(-1, "请传入支付信息");
        }
        if (payInfo.getAmountPaid() == null) {
            throw new BaseException(-1, "请传入付款金额");
        }
        if (payInfo.getOrderId() == null) {
            throw new BaseException(-1, "请指定支付的订单");
        }

        Order order = new OrderDao().selectById(payInfo.getOrderId());
        boolean balance = payInfo.getAmountPaid().equals(order.getTotalPrice());
        return balance;
    }

}
