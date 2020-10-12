
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.Statement;

public class jdbcDemo {

    // JDBC 驱动名 及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/internship_stroe";

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "981016.Ly";

    public static void main(String[] args) throws Exception {
        Connection conn = null;
        Statement stmt = null;
        try {
            // 注册 JDBC 驱动
            // 把Driver类装载进jvm
            Class.forName("com.mysql.jdbc.Driver");

            // 打开链接
            System.out.println("连接数据库...");
            conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);

            // 执行查询
            System.out.println("实例化Statement对...");
            stmt = (Statement) conn.createStatement();
            String sql = "SELECT * FROM customer";
            ResultSet rs = stmt.executeQuery(sql);

            while (rs.next()) {
                // 通过字段检索
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String sex = rs.getString("sex");
                String contactNum = rs.getString("contact_num");
                // 输出数据
                System.out.print("ID: " + id);
                System.out.print(", 姓名: " + name);
                System.out.print(",性别: " + sex);
                System.out.print(",电话：" + contactNum);
                System.out.print("\n");
            }
            //更新update

            String update_sql = "update  cusomer set address=\"jerry\", contact_num =\"0123\"  where id=2";
            stmt.execute(update_sql);

            //增加insert
            String inset_sql1 = "insert into cusomer (name, sex, contact_num) values(\"jom\", \"M\",  \"123864979\")";
            int i = stmt.executeUpdate(inset_sql1);

            //增加insert
            String inset_sql2 = "insert into cusomer (name, sex, contact_num) values(\"Tom\", \"F\",  \"123864999\")";
            int i1 = stmt.executeUpdate(inset_sql2);

            //增加insert
            String inset_sql3 = "insert into cusomer (name, sex, contact_num) values(\"Jane\", \"F\",  \"111111111\")";
            int i2 = stmt.executeUpdate(inset_sql3);

            //删除 delete
            String delete_sql = "delete from cusomer where id=3";
            stmt.execute(delete_sql);

            // 完成后关闭
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            // 处理 JDBC 错误
            se.printStackTrace();
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
        System.out.println("jdbc_test_end!");
}

}
