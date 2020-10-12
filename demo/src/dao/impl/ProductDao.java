package dao.impl;

import dao.IBaseDao;
import dao.IProductDao;
import model.Product;

import java.util.List;


public class ProductDao extends BaseDao<Product> implements IProductDao {


    @Override
    public int addProduct(Product product) {

        int addedNum = this.insertObj(product);
        return addedNum;
    }

    @Override
    public int deleteProduct(Product product) {

        int deletedNum = this.deleteObjById(product.getId());
        return deletedNum;
    }

    @Override
    public int updateProduct(Product product) {

        int updatedNum = this.updateObjById(product);
        return updatedNum;
    }

    @Override
    public List<Product> selectAllProducts() {

        String sql = "select * from product";
        List<Product> products =  this.selectMulObj(sql);
        return products;
    }

    @Override
    public List<Product> selectProductsById(List<Integer> idList) {
        StringBuilder sql = new StringBuilder();
        sql.append("select * from product where id in (");
        for (int i = 0; i < idList.size(); i++) {
            if (i != 0) {
                sql.append(", ");
            }
            sql.append(idList.get(i));
        }
        sql.append(")");

        List<Product> products = this.selectMulObj(sql.toString());

        return products;
    }

    @Override
    public List<Product> selectByName(String name) {

        String sql = "select * from product where name = " + name;
        List<Product> products = this.selectMulObj(sql);
        return products;
    }

    @Override
    public List<Product> selectByMerchantId(Integer id) {

        String sql = "select * from product where merchant_id = " + id;
        List<Product> products = this.selectMulObj(sql);
        return products;
    }

    @Override
    public List<Product> selectByMulInfo(Product product) {

        List<Product> products = this.selectByAndInfo(product);
        return null;
    }

}
