import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class NewRegistration {

    private TheConnection myConnection = new TheConnection();

    public static void main(String[] args) {
        NewRegistration v = new NewRegistration();
        v.insertOne("CS21120","1012345678");
    }
    public void insertOne(String module,String student) {
        try {
            Connection conn = myConnection.getConnection();
            Statement stmnt = conn.createStatement();
            PreparedStatement insert = conn.prepareStatement("INSERT INTO reg (student,module) VALUES (?,?)");
            insert.setString(1,student);
            insert.setString(2,module);
            insert.executeUpdate();
//            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
