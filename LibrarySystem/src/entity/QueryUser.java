/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
import java.awt.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 *
 * @author quangns
 */
class QueryUser {
    private static String ID;
    private static String FN;
    private static String LN;
    private static String UN;
    private static String PW;
    private static String CID;
    private static String Role;
    private static String Str;
    private static String Str2;

    public QueryUser(String FN, String LN, String UN, String PW) {
        this.FN = FN;
        this.LN = LN;
        this.UN = UN;
        this.PW = PW;
    }

    public QueryUser(String Str) {
        this.Str = Str;
    }
    
    public QueryUser(String Str, String Str2) {
        this.Str = Str;
        this.Str2 = Str2;
    }
    
    public QueryUser() {
    }
    
    //Add a account into system
    public static void InsertUser() throws SQLException{
        try(Connection conn = ConnectSQL.connectsql()){
            Statement st = conn.createStatement();
            st.executeUpdate("INSERT INTO user(FirstName, LastName, UserName, PassWord, Role) VALUES ('" + FN+ "','" + LN+"','"+UN+"','"+PW+"','user')");
            conn.close();
        }
        catch(Exception e) {
            System.out.print("Do not connect to DB - Error: " +e);
        }
    }
    
    //Search account with UserName
    public static ArrayList<String> SearchUserName() throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()) {
            Statement st = conn.createStatement();
            ResultSet rs;
            ArrayList<String> user = new ArrayList<String>();
            rs = st.executeQuery("SELECT * FROM user WHERE UserName = '" + Str + "'");
            while (rs.next()) {
                String username = rs.getString("UserName");
                String password = rs.getString("PassWord");
                user.add(username);
                user.add(password);
            }
            conn.close();
            return user;
        }
        catch(Exception e) {
            System.out.println("Do not connect to DB - Error: " +e);
        }
        return null;
    }
    
    //Search account with Name of User
    public static ResultSet SearchName() throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()) {
            Statement st = conn.createStatement();
            ResultSet rs;
            
            rs = st.executeQuery("SELECT * FROM user WHERE LastName = '" + Str + "'");
            while (rs.next()) {
                String Username = rs.getString("UserName");
                System.out.println(Username);
            }
            conn.close();
            return rs;
        }
        catch(Exception e) {
            System.out.println("Do not connect to DB - Error: " +e);
        }
        return null;
    }
    
    // Update password a account
    public static void UpdatePW() throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()) {
            String query = "UPDATE user SET PassWord = ? WHERE UserName = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, Str);
            ps.setString(2, Str2);
            ps.executeUpdate();
            conn.close();
        }
        catch(Exception e) {
            System.out.println("Do not connect to DB - Error: " +e);
        }
    }
    
    //Update CardID of a account
    public static void UpdateCardID() throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()) {
            String query = "UPDATE user SET CardID = ? WHERE UserName = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, Str);
            ps.setString(2, Str2);
            ps.executeUpdate();
            conn.close();
        }
        catch(Exception e) {
            System.out.println("Do not connect to DB - Error: " +e);
        }
    }
    
    //Delete a account
    public static void DelUser() throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()) {
            String query = "DELETE FROM user WHERE UserName = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, Str);
            ps.executeUpdate();
            conn.close();
        }
        catch(Exception e) {
            System.out.println("Do not connect to DB - Error: " +e);
        }
    }
    
    public static void checkUsername(String st) throws SQLException {
        ArrayList<String> user = new QueryUser(st).SearchUserName();
    }
    
    public static void main(String[] args) throws SQLException {
       new QueryUser().checkUsername("khai");
    }
}
