import java.sql.*;

public class ViewModules {
    private TheConnection myConnection = new TheConnection();
    public static void main(String[] args) {
        ViewModules v = new ViewModules();
       // v.printCodes();
       // v.printTitle();
      // v.printLevel(1);
    }

    public void printCodes() {
        try {
            Connection conn = myConnection.getConnection();
            Statement stmnt = conn.createStatement();
            ResultSet res = stmnt.executeQuery("select code from module");
            while (res.next()) {
                System.out.println(res.getString(1));
            }
//            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void printTitle() {
        try {
            Connection conn = myConnection.getConnection();
            Statement stmnt = conn.createStatement();
            ResultSet res = stmnt.executeQuery("select title,code from module");
            while (res.next()) {
                System.out.println(res.getString(1) +" (" + res.getString(2) +")");
            }
//            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void printLevel(int i) {
        try {
            Connection conn = myConnection.getConnection();
            Statement stmnt = conn.createStatement();
            ResultSet res = stmnt.executeQuery("select title,code from module");
            while (res.next()) {
                if ((Character.getNumericValue(res.getString(2).charAt(2))) == i){
                System.out.println(res.getString(1));
            }
            }
//            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

}
