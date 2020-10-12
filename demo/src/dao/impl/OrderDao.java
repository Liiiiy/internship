package dao.impl;

import dao.IOrderDao;
import model.Order;
import model.OrderDetail;

import java.util.List;

public class OrderDao extends BaseDao<Order> implements IOrderDao {


    @Override
    public int createOrder(Order order) {

        int createdNum = this.insertObj(order);
        return createdNum;
    }

    @Override
    public int createOrders(List<Order> orders) {

        int createdNum = this.insertMulObj(orders);
        return 0;
    }

    @Override
    public int deleteById(Integer id) {

        int deletedNum = this.deleteObjById(id);
        return deletedNum;
    }


    @Override
    public int modifyOrder(Order order) {

        int modifiedNum = this.updateObjById(order);
        return modifiedNum;
    }

    @Override
    public List<Order> selectAllOrders() {

        String sql = "select * from order";
        List<Order> orders = this.selectMulObj(sql);
        return orders;
    }

    @Override
    public Order selectById(Integer id) {

        String sql = "select * from internship_stroe.order where id = " + id;
        Order order = this.selectObj(sql);
        return order;
    }

    @Override
    public List<Order> selectByPurchaser(Integer id) {

        String sql = "select * from order where purchaser_id = " + id;
        List<Order> orders = this.selectMulObj(sql);
        return orders;
    }

    @Override
    public List<Order> selectByStatus(Integer status) {

        String sql = "select * from order where status = " + status;
        List<Order> orders = this.selectMulObj(sql);
        return orders;
    }

    @Override
    public List<Order> selectByMulInfo(Order order) {

        List<Order> orders = this.selectByAndInfo(order);
        return orders;
    }

}
