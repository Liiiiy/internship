package util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;



import javax.sql.DataSource;


public class JDBCUtils {

    private static DataSource ds;

    static {

        try {
            // 加载配置文件
            Properties pro = new Properties();
            InputStream is = JDBCUtils.class.getClassLoader().getResourceAsStream("druid.properties");
            pro.load(is);

            // 获取DataResource
            ds = DruidDataSourceFactory.createDataSource(pro);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     *  获取连接
     */
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    /**
     * 释放资源
     */
    public static void close(ResultSet rs , Statement stmt, Connection conn){

        try {

            if(rs != null){
                rs.close();
            }

            if(stmt != null){
                stmt.close();
            }

            if(conn != null){
                conn.close();   //归还连接
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void close(Statement stmt, Connection conn){

        close(null,stmt,conn);
    }








}
