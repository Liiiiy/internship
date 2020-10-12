package dao;

import com.sun.org.apache.xpath.internal.operations.Or;
import model.Order;

import java.util.List;

public interface IOrderDao extends IBaseDao<Order> {

    int createOrder(Order order);

    int createOrders(List<Order> orders);

    int deleteById(Integer id);

    int modifyOrder(Order order);

    List<Order> selectAllOrders();

    Order selectById(Integer id);

    List<Order> selectByPurchaser(Integer id);

    List<Order> selectByStatus(Integer status);

    List<Order> selectByMulInfo(Order order);



}
