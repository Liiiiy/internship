package dao;

import model.OrderDetails;

import java.util.List;

public interface IOrderDetailsDao extends IBaseDao<OrderDetails> {

    int createOrderDetails(OrderDetails orderDetails);

    int createOrderDetails(List<OrderDetails> orderDetails);

    int deleteOrderDetails(OrderDetails orderDetails);

    int deleteOrderDetails(List<OrderDetails> orderDetails);

    int updateOrderDetails(OrderDetails orderDetails);

    List<OrderDetails> selectByOrderId(Integer id);

    List<OrderDetails> selectByProductId(Integer id);

    List<OrderDetails> selectByMulInfo(OrderDetails orderDetails);

}
