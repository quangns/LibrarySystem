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
/**
 *
 * @author quangns
 */
public class QueryBook {
    private static String Title;
    private static String Publisher;
    private static String Author;
    private static int Price;
    private static String Str;
    private static String Str2;

    public QueryBook(String Title, String Publisher, String Author, int Price) {
        this.Title = Title;
        this.Publisher = Publisher;
        this.Author = Author;
        this.Price = Price;
    }
    
    public QueryBook(String Str) {
        this.Str = Str;
    }
    
    public QueryBook(String Str, String Str2) {
        this.Str = Str;
        this.Str2 = Str2;
    }
    
    //Add a book
    public static void InsertBook() throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()){
            Statement st = conn.createStatement();
            st.executeUpdate("INSERT INTO book(Title, Publisher, Author, Price) VALUES ('" + Title+ "','" + Publisher+"','"+Author+"',"+Price+")");
            conn.close();
        }
        catch(Exception e) {
            System.out.print("Do not connect to DB - Error: " +e);
        }
    }
    
    //Search book with Title
    public static ResultSet SearchTitle() throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()) {
            Statement st = conn.createStatement();
            ResultSet rs;
            
            rs = st.executeQuery("SELECT * FROM book WHERE Title = '" + Str + "'");
//            while (rs.next()) {
//                String lastName = rs.getString("Author");
//                System.out.println(lastName);
//            }
            conn.close();
            return rs;
        }
        catch(Exception e) {
            System.out.println("Do not connect to DB - Error: " +e);
        }
        return null;
    }
    
    //Search book with Author
    public static ResultSet SearchAuthor() throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()) {
            Statement st = conn.createStatement();
            ResultSet rs;
            rs = st.executeQuery("SELECT * FROM book WHERE Author = '" + Str + "'");
//            while (rs.next()) {
//                String lastName = rs.getString("Title");
//                System.out.println(lastName);
//            }
            return rs;
        }
        catch(Exception e) {
            System.out.println("Do not connect to DB - Error: " +e);
        }
        return null;
    }
    
    //Update price of book
    public static void UpdatePrice() throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()) {
            String query = "UPDATE book SET Price = ? WHERE BID = ?";
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
    
    //delete a book
    public static void DelBook() throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()) {
            String query = "DELETE FROM book WHERE BID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, Str);
            ps.executeUpdate();
            conn.close();
        }
        catch(Exception e) {
            System.out.println("Do not connect to DB - Error: " +e);
        }
    }
    
    public static void main(String[] args) throws SQLException {
        QueryBook book = new QueryBook("ca phe cung tony", "Nha Nam", "Tony", 70);
        book.InsertBook();
    }
}
