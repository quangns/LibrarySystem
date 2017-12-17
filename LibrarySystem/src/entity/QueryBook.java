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
public class QueryBook {
        
    
    /**
     * them 1 ten sach vao database
     * @param title ten cua sach
     * @param publisher nha xuat ban quyen sach do
     * @param author tac gia
     * @param price gia cua sach
     * @throws SQLException 
     */
    public static void InsertBook(String title, String publisher, String author, int price) throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()){
//            Statement st = conn.createStatement();
            String query = "INSERT INTO book(Title, Publisher, Author, Price) VALUES (?, ?, ?, ?)";
//            st.executeUpdate(query);
//            String query = "UPDATE book SET Title = ?, Publisher = ?, Author = ?, Price = ? WHERE bid = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, title);
            ps.setString(2, publisher);
            ps.setString(3, author);
            ps.setInt(4, price);
//            ps.setString(5, bid);
            ps.executeUpdate();
            conn.close();
        }
        catch(Exception e) {
            System.out.println("Do not connect to DB - Error: " +e);
        }
    }
    
    //Search book with Title
    public static ArrayList<String> SearchTitle(String title) throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()) {
            Statement st = conn.createStatement();
            ResultSet rs;
            ArrayList<String> book = new ArrayList<String>();
            rs = st.executeQuery("SELECT * FROM book WHERE Title LIKE '%" + title + "%'");
            while (rs.next()) {
                String name = rs.getString("Title");
                String bid = rs.getString("BID");
                String author = rs.getString("Author");
                String publisher = rs.getString("Publisher");
                String price = rs.getString("Price");
                book.add(name);
                book.add(bid);
                book.add(author);
                book.add(publisher);
                book.add(price);
            }
            conn.close();
            return book;
        }
        catch(Exception e) {
            System.out.println("Do not connect to DB - Error: " +e);
        }
        return null;
    }
    
    //Search book with Author
    public static ArrayList<String> SearchBID(String bid) throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()) {
            Statement st = conn.createStatement();
            ResultSet rs;
            ArrayList<String> book = new ArrayList<String>();
            rs = st.executeQuery("SELECT * FROM book WHERE BID = '" + bid + "'");
            while (rs.next()) {
                String name = rs.getString("Title");
                String author = rs.getString("Author");
                String publisher = rs.getString("Publisher");
                String price = rs.getString("Price");
                book.add(name);
                book.add(bid);
                book.add(author);
                book.add(publisher);
                book.add(price);
            }
            conn.close();
            return book;
        }
        catch(Exception e) {
            System.out.println("Do not connect to DB - Error: " +e);
        }
        return null;
    }
    
    
    /**
     * Chinh sua gia cua mot quyen sach
     * @param price gia sach muon cap nhat
     * @param bid ma cua quyen sach do
     * @throws SQLException 
     */
    public static void UpdatePrice(String price, String bid) throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()) {
            String query = "UPDATE book SET Price = ? WHERE BID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, price);
            ps.setString(2, bid);
            ps.executeUpdate();
            conn.close();
        }
        catch(Exception e) {
            System.out.println("Do not connect to DB - Error: " +e);
        }
    }
    
    
    /**
     * xoa sach theo ma sach cua sach do
     * @param bid ma cua quyen sach do
     * @throws SQLException 
     */
    public static void DelBook(String bid) throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()) {
            String query = "DELETE FROM book WHERE BID = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, bid);
            ps.executeUpdate();
            conn.close();
        }
        catch(Exception e) {
            System.out.println("Do not connect to DB - Error: " +e);
        }
    }
    
    public static void UpdateBook(StoreBook book) throws SQLException {
        try(Connection conn = ConnectSQL.connectsql()) {
            int bid = Integer.parseInt(book.getBid());
            String title = book.getTitle();
            String author = book.getAuthor();
            String publisher = book.getPublisher();
            int price = Integer.parseInt(book.getPrice());
            String query = "UPDATE book SET Title = ?, Publisher = ?, Author = ?, Price = ? WHERE bid = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, title);
            System.out.println(title.length());
            ps.setString(2, publisher);
            ps.setString(3, author);
            ps.setInt(4, price);
            ps.setInt(5, bid);
            ps.executeUpdate();
            conn.close();
        }
        catch(SQLException e) {
            System.out.println("Do not connect to DB - Error: " + e);
        }
    }
    
    public static void main(String[] args) throws SQLException {
        new QueryBook().InsertBook("đừng để tách cà phê nguội", "không có", "không", 30);
    }
}
