package util;

import com.mysql.jdbc.Connection;
import model.Merchant;
import model.Product;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseUtils {

    // JDBC 驱动名 及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/internship_stroe";

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "981016.Ly";

/*    public static Connection connect() throws SQLException {

        Connection conn = null;

        try {
            // 注册 JDBC 驱动
            // 把Driver类装载进jvm
            Class.forName("com.mysql.jdbc.Driver");
            // 打开链接
            conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);

        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (conn != null)
                conn.close();
        }

        return conn;
    }*/

    public static void connectAndExecute(String sql) throws SQLException {
        Connection conn = null;
        Statement stmt = null;

        try {
            // 注册 JDBC 驱动
            // 把Driver类装载进jvm
            Class.forName("com.mysql.jdbc.Driver");
            // 打开链接
            conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            stmt.execute(sql);

        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (stmt != null)
                stmt.close();
            if (conn != null)
                conn.close();
        }

    }

    public  static Merchant connectAndCheckMerchantDetails(String sql) throws SQLException{

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        Merchant merchant = new Merchant();

        try {
            // 注册 JDBC 驱动
            // 把Driver类装载进jvm
            Class.forName("com.mysql.jdbc.Driver");
            // 打开链接
            conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                merchant.setId(rs.getInt("id"));
                merchant.setOwnerName(rs.getString("owner_name"));
                merchant.setMerchantAddress(rs.getString("merchant_address"));
                merchant.setContactNum(rs.getString("contactNum"));
                merchant.setCreateTime(rs.getDate("create_time"));
                merchant.setStoreName(rs.getString("store_name"));
                merchant.setContactName(rs.getString("contact_name"));
                merchant.setAccount(rs.getString("account"));
                merchant.setPassword(rs.getString("password"));
            }

        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (stmt != null)
                stmt.close();
            if (conn != null)
                conn.close();
        }

        return merchant;
    }

    public  static Product connectAndCheckProductDetails(String sql) throws SQLException {

        Product product = new Product();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // 注册 JDBC 驱动
            // 把Driver类装载进jvm
            Class.forName("com.mysql.jdbc.Driver");
            // 打开链接
            conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();

            rs = stmt.executeQuery(sql);

            while (rs.next()) {
                product.setId(rs.getInt("id"));
                product.setName(rs.getString("name"));
                product.setPrice(rs.getDouble("price"));
                product.setMerchantId(rs.getInt("merchant_id"));
                product.setMerchantInventory(rs.getInt("merchant_inventory"));
            }

        } catch (Exception e) {
            // 处理 Class.forName 错误
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (stmt != null)
                stmt.close();
            if (conn != null)
                conn.close();
        }

        return product;
    }

}
