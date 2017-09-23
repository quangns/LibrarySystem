/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 *
 * @author quangns
 */
public class QueryCard {
    private static int CID;
    private static Date ExpDate;
    private static boolean Status;
    private static int NumActive;
    private static int year;
    private static int month;
    private static int day;
    
    public QueryCard(int year, int month, int day, int NumActive) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.NumActive = NumActive;
    }
    
    public QueryCard(int CID) {
        this.CID = CID;
    }
    
    public QueryCard(boolean Status, int CID) {
        this.Status = Status;
        this.CID = CID;
    }
    
    //Add a card
    public static void InsertCard() throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()){
            Statement st = conn.createStatement();
            st.executeUpdate("INSERT INTO card(ExpiredDate, NumActive) VALUES ('"+year+"-"+month+"-"+day+"'," +NumActive + ")");
            conn.close();
        }
        catch(Exception e) {
            System.out.print("Do not connect to DB - Error: " +e);
        }
    }
    
    //Search card with CID
    public static ResultSet SearchCard() throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()) {
            Statement st = conn.createStatement();
            ResultSet rs;
            
            rs = st.executeQuery("SELECT * FROM card WHERE CID = '" + CID + "'");
//            while (rs.next()) {
//                String stt = rs.getString("Status");
//                System.out.println(stt);
//                String num = rs.getString("NumActive");
//                System.out.println(num);
//            }
            conn.close();
            return rs;
        }
        catch(Exception e) {
            System.out.println("Do not connect to DB - Error: " +e);
        }
        return null;
    }
    
    //Search card with Status
    public static ResultSet SearchCardST() throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()) {
            Statement st = conn.createStatement();
            ResultSet rs;
            
            rs = st.executeQuery("SELECT * FROM card WHERE Status IS NULL");
            while (rs.next()) {
                String stt = rs.getString("CID");
                String num = rs.getString("NumActive");
                System.out.println("CID: " + stt + ", Number to Active: " + num);
            }
            conn.close();
            return rs;
        }
        catch(Exception e) {
            System.out.println("Do not connect to DB - Error: " +e);
        }
        return null;
    }
    
    //Update status of card with Number for Active
    public static void UpdateCardNA() throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()) {
            String query = "UPDATE card SET Status = 1, NumActive = null WHERE NumActive = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, CID);
            ps.executeUpdate();
            conn.close();
        }
        catch(Exception e) {
            System.out.println("Do not connect to DB - Error: " +e);
        }
    }
    
    //Update status of card with CID
    public static void UpdateCardCID() throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()) {
            String query = "UPDATE card SET Status = ? WHERE CID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setBoolean(1, Status);
            ps.setInt(2, CID);
            ps.executeUpdate();
            conn.close();
        }
        catch(Exception e) {
            System.out.println("Do not connect to DB - Error: " +e);
        }
    }
    
   //Delete a card
    public static void DelCard() throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()) {
            String query = "DELETE FROM card WHERE CID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, CID);
            ps.executeUpdate();
            conn.close();
        }
        catch(Exception e) {
            System.out.println("Do not connect to DB - Error: " +e);
        }
    }
    
    public static void main(String[] args) throws SQLException {
        QueryCard card = new QueryCard(13);
        card.DelCard();
    }
}
