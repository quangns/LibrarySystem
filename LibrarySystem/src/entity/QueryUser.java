/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
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
public class QueryUser {
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
    
    
    /**
     * Them tai khoan nguoi dung vao he thong
     * @throws SQLException 
     */
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
    
    
    /**
     * tim kiem thong tin nguoi dung theo username
     * @return luu thong tin nguoi dung vao ArrayList
     * @throws SQLException 
     */
    public static ArrayList<String> SearchUserName() throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()) {
            Statement st = conn.createStatement();
            ResultSet rs;
            ArrayList<String> user = new ArrayList<String>();
            rs = st.executeQuery("SELECT * FROM user WHERE UserName = '" + Str + "'");
            while (rs.next()) {
                String username = rs.getString("UserName");
                String password = rs.getString("PassWord");
                String role = rs.getString("Role");
                user.add(username);
                user.add(password);
                user.add(role);
            }
            conn.close();
            return user;
        }
        catch(Exception e) {
            System.out.println("Do not connect to DB - Error: " +e);
        }
        return null;
    }
    
    
    /**
     * Tim kiem thong tin nguoi dung theo ten cua nguoi do trong he thong
     * @return cac username cua cac nguoi dung co cung ten tim kiem trong he thong
     * @throws SQLException 
     */
    public static ResultSet SearchName() throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()) {
            Statement st = conn.createStatement();
            ResultSet rs;
            
            rs = st.executeQuery("SELECT * FROM user WHERE FirstName = '" + Str + "'");
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
    
    
    /**
     * Thay doi mat khau 
     * @throws SQLException 
     */
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
    
    
    /**
     * Them ma the muon cua nguoi dung vao he thong,
     * khi nguoi dung moi duoc cap the hay lam lai the,
     * sau khi nhap ma vao the, he thong se them ID cua the vao database
     * @throws SQLException 
     */
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
    
    
    /**
     * Xoa mot nguoi su dung theo username
     * @throws SQLException 
     */
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
    
    
    //Check username to register
//    public static boolean CheckRsUsername(String st) throws SQLException {
//        ArrayList<String> user = new QueryUser(st).SearchUserName();
////        return !st.equals(user.get(0));
//        try {
//            return !st.equals(user.get(0));
//        } catch (Exception e) {
//            System.out.println("error: " + e);
//        }
//        return false;
//    }
    
    
//    public static void main(String[] args) throws SQLException {
//       boolean check = QueryUser.CheckRsUsername("minh");
//       if(check)
//            System.out.println("chua duoc su dung");
//       else
//            System.out.println("da duoc su dung");
//    }
}
