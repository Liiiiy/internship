package dao.impl;

import dao.IOrderDetailsDao;
import model.OrderDetails;
import org.omg.CORBA.INTERNAL;

import java.util.LinkedList;
import java.util.List;

public class OrderDetailsDao extends BaseDao<OrderDetails> implements IOrderDetailsDao {
    @Override
    public int createOrderDetails(OrderDetails orderDetails) {

        int createdNum = this.insertObj(orderDetails);
        return createdNum;
    }

    @Override
    public int createOrderDetails(List<OrderDetails> orderDetails) {

        int createdNum = this.insertMulObj(orderDetails);
        return createdNum;
    }

    @Override
    public int deleteOrderDetails(OrderDetails orderDetails) {

        int deletedNum = this.deleteObjById(orderDetails.getId());
        return 0;
    }

    @Override
    public int deleteOrderDetails(List<OrderDetails> orderDetails) {

        List<Integer> idList = new LinkedList<>();
        for (OrderDetails orderDetail : orderDetails) {
            idList.add(orderDetail.getId());
        }

        int deletedNum = this.deleteObjById(idList);
        return deletedNum;
    }

    @Override
    public int updateOrderDetails(OrderDetails orderDetails) {

        int updatedNum = this.updateObjById(orderDetails);
        return updatedNum;
    }

    @Override
    public List<OrderDetails> selectByOrderId(Integer id) {

        String sql = "select * from order_details where order_id = " + id;
        List<OrderDetails> orderDetails = this.selectMulObj(sql);
        return orderDetails;
    }

    @Override
    public List<OrderDetails> selectByProductId(Integer id) {

        String sql = "select * from order_details where product_id = " + id;
        List<OrderDetails> orderDetails = this.selectMulObj(sql);
        return orderDetails;
    }

    @Override
    public List<OrderDetails> selectByMulInfo(OrderDetails orderDetails) {

        List<OrderDetails> resultList = this.selectByAndInfo(orderDetails);
        return null;
    }

}
