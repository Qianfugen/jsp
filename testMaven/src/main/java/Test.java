import java.sql.*;

public class Test {
    public static void main(String[] args) {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection conn = null;

        {
            try {
                conn = DriverManager.getConnection("jdbc:oracle:thin:@47.100.61.199:1521:orcl", "qianfg", "123456");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        String sql = "select name from t_user";

        PreparedStatement ps = null;
        ResultSet rs = null;

        {
            try {
                ps = conn.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                    System.out.println(rs.getString("name"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                    if (ps != null) {
                        ps.close();
                    }
                    if (conn != null) {
                        conn.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
