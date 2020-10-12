package dao;

import model.Product;

import java.util.List;

public interface IProductDao extends IBaseDao<Product> {

    int addProduct(Product product);

    int deleteProduct(Product product);

    int updateProduct(Product product);

    List<Product> selectAllProducts();

    List<Product> selectProductsById(List<Integer> idList);

    List<Product> selectByName(String name);

    List<Product> selectByMerchantId(Integer id);

    List<Product> selectByMulInfo(Product product);

}
