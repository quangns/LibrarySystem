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
import java.text.ParseException;
/**
 *
 * @author quangns
 */
public class QueryBookcp {
    private static int CPID;
    private static int CID;
    private static int BID;
    private static boolean Status;
    private static int num;
    private static String date;
    
    public QueryBookcp(int num, int BID) {
        this.num = num;
        this.BID = BID;
    }
    
    public QueryBookcp(int CID, String date, int CPID) {
        this.CID = CID;
        this.date = date;
        this.CPID = CPID;
    }
    
    public QueryBookcp(int num) {
        this.num = num;
    }
    
    //Add the book copies
    public static void InsertCopy() throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()){
            Statement st = conn.createStatement();
            for(int i=0; i<num;i++){
                System.out.println(i);
                st.executeUpdate("INSERT INTO bookcp(BID) VALUES ("+BID+ ")");
            }
            conn.close();
        }
        catch(Exception e) {
            System.out.print("Do not connect to DB - Error: " +e);
        }
    }
    
    //Update borrow book
    public static void UpdateBorrow() throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()) {
            String query = "UPDATE bookcp SET CID = ?, ExpDate = ?, Status = false WHERE CPID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, CID);
            ps.setString(2, date);
            ps.setInt(3, CPID);
            ps.executeUpdate();
            conn.close();
        }
        catch(Exception e) {
            System.out.println("Do not connect to DB - Error: " +e);
        }
    }
    
    //Update return book
    public static void UpdateReturn() throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()) {
            String query = "UPDATE bookcp SET CID = NULL, ExpDate = NULL, Status = true WHERE CPID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, num);
            ps.executeUpdate();
            conn.close();
        }
        catch(Exception e) {
            System.out.println("Do not connect to DB - Error: " +e);
        }
    }
    
    //Count total book
    public static ResultSet CountTotalBook() throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()){
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(CPID) AS number FROM bookcp WHERE BID =" + num);
//            while (rs.next()) {
//                String lastName = rs.getString("number");
//                System.out.println(lastName + "\n");
//            }
            conn.close();
            return rs;
        }
        catch(Exception e) {
            System.out.print("Do not connect to DB - Error: " +e);
        }
        return null;
    }
    
    //Count book can borrow
    public static ResultSet CountBookFree() throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()){
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(CPID) AS number FROM bookcp WHERE BID =" + num + " AND Status = true");
//            while (rs.next()) {
//                String lastName = rs.getString("number");
//                System.out.println(lastName + "\n");
//            }
            conn.close();
            return rs;
        }
        catch(Exception e) {
            System.out.print("Do not connect to DB - Error: " +e);
        }
        return null;
    }
    
    //Count the book borrowed by a user
    public static ResultSet CountBookBorrowed() throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()){
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT COUNT(CPID) AS number FROM bookcp WHERE CID =" + num);
//            while (rs.next()) {
//                String lastName = rs.getString("number");
//                System.out.println(lastName + "\n");
//            }
            conn.close();
            return rs;
        }
        catch(Exception e) {
            System.out.print("Do not connect to DB - Error: " +e);
        }
        return null;
    }
    
    public static void main(String[] args) throws SQLException, ParseException {
        QueryBookcp copy = new QueryBookcp(2);
        copy.CountBookBorrowed();
    }
}
