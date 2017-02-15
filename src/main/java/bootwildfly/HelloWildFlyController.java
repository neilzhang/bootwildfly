package bootwildfly;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWildFlyController {

	@RequestMapping("hello")
	public String sayHello() throws SQLException {
		String name = "";

		Connection conn = null;
		String sql;
		String url = "jdbc:mysql://10.5.4.112:3306/hello?"
				+ "user=root&password=123456&useUnicode=true&characterEncoding=UTF8";

		try {
			Class.forName("com.mysql.jdbc.Driver");

			conn = DriverManager.getConnection(url);
			Statement stmt = conn.createStatement();
			sql = "select name from user";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				name = rs.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			conn.close();
		}

		return ("Hello " + name + ", SpringBoot on Wildfly");
	}
}