package service.inf;

import model.Order;
import model.request.OrderInfo;
import model.request.PayInfo;

public interface OrderService {

    Order createOrder(OrderInfo orderInfo);

    boolean payForOrder(PayInfo payInfo);

}
