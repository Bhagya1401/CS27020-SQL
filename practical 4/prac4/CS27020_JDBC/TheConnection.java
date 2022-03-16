import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class TheConnection {
	private Connection connection = null;

	public  Connection getConnection() throws SQLException {
		String url = "jdbc:postgresql://db.dcs.aber.ac.uk:5432/cs27020_21_22_bhw";
		String uid = "bhw";
		String password = "@Nirmana123!";
		if (connection == null || connection.isClosed()) {
			connection =
					DriverManager.getConnection(url,uid,password);
		}

		return connection;
	}
	
	public  void close() {
		if (connection != null){
			try {
				connection.close();
				connection = null;
			} catch (SQLException e) {
				e.printStackTrace();
			};
		}
	}
}
