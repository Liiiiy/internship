package util;

import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDemo {

    public static void main(String[] args) throws SQLException {


        for (int i = 0; i < 15; i++) {
            Connection conn = JDBCUtils.getConnection();
            System.out.println(conn);
            JDBCUtils.close(null,conn);
        }
    }


}
