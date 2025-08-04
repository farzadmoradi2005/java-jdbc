import java.sql.*;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();
        String URL = "jdbc:postgresql://localhost:5432/test";
        String USER = "farzad";
        String PASS = "12345";
        try(Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            System.out.println("Connected to PostgreSQL database");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if(input.equals("insert")){
            Connection conn = DriverManager.getConnection(URL , USER , PASS);
            String name = in.nextLine();
            int age = in.nextInt();
            int ID = in.nextInt();
            String sql = "INSERT INTO programmer (name, age, \"ID\") VALUES (?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setInt(3, ID);
            ps.executeUpdate();
        }
        if(input.equals("delet")){
            Connection conn = DriverManager.getConnection(URL , USER , PASS);
            int id = in.nextInt();
            String sql = "DELETE FROM programmer WHERE \"ID\" = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.executeUpdate();
        }
        if(input.equals("select")){
            Connection conn = DriverManager.getConnection(URL , USER , PASS);
            String sql = "SELECT * FROM programmer";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                int id = rs.getInt("ID");
                String name = rs.getString("name");
                System.out.println(name + " " + id);
            }
        }
    }
}