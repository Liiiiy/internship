package service;

import model.Merchant;
import model.Order;
import model.request.LoginInfo;
import model.request.OrderInfo;
import model.request.PayInfo;
import service.inf.MerchantService;

import java.util.LinkedList;
import java.util.List;

public class Test {

    public static void main(String[] args) {

        testLoginMerchant();
    }

    public static void testCreateOrder() {

        List<OrderInfo.ProductInfo> list = new LinkedList<>();

        OrderInfo.ProductInfo productInfo = new OrderInfo.ProductInfo();
        productInfo.setProductId(6);
        productInfo.setQuantity(3);

        OrderInfo.ProductInfo productInfo2 = new OrderInfo.ProductInfo();
        productInfo2.setProductId(5);
        productInfo2.setQuantity(2);

        list.add(productInfo);
        list.add(productInfo2);

        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setPurchaserId(2);
        orderInfo.setProductList(list);
        orderInfo.setReceiver("Amelia");
        orderInfo.setReceiverContactNum("15885696347");
        orderInfo.setReceiverAddress("Building");

        OrderServiceImpl orderService = new OrderServiceImpl();
        orderService.createOrder(orderInfo);
    }

    public static boolean testPayOrder() {
        boolean payStatus = false;

        PayInfo payInfo = new PayInfo();
        payInfo.setOrderId(30);
        payInfo.setAmountPaid(0.0);
        payInfo.setPayType(Order.PAY_BY_ALIAPAY);

        OrderServiceImpl orderService = new OrderServiceImpl();
        payStatus = orderService.payForOrder(payInfo);

        return payStatus;
    }

    public static void testRegisterMerchant() {
        Merchant merchant = new Merchant();
        merchant.setOwnerName("Troye Sivan");
        merchant.setStoreName("Strawberry & Cigarette");
        merchant.setAccount("FOOL");
        merchant.setPassword("12345678");

        MerchantServiceImpl merchantService = new MerchantServiceImpl();
        Merchant mer = merchantService.registerMerchant(merchant);

        System.out.println("Owner Name: " + mer.getOwnerName());
        System.out.println("Store Name: " + mer.getStoreName());
        System.out.println("Account: " + mer.getAccount());
    }

    public static void testLoginMerchant() {
        LoginInfo loginInfo = new LoginInfo();

        loginInfo.setAccount("FOOL");
        loginInfo.setPassword("12345678");

        MerchantService merchantService = new MerchantServiceImpl();
        Merchant merchant = merchantService.loginMerchant(loginInfo);

        System.out.println("Merchant Account: " + merchant.getAccount());
        System.out.println("Store Name: " + merchant.getStoreName());
        System.out.println("Owner Name: " + merchant.getOwnerName());
    }

}
