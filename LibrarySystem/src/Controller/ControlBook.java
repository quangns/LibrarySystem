/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;
import entity.QueryBook;
import entity.QueryBookcp;
import entity.StoreBook;
import entity.StoreBookcp;
import form.BorrowedForm;
import form.EditBookForm;
import form.NewStart;
import form.SearchBookForm;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author quangns
 */
public class ControlBook{
    private static StoreBook books = new StoreBook();
    private static ArrayList<String> book = new ArrayList<String>();
    private ArrayList<StoreBook> stbook = new ArrayList<StoreBook>();
    private static StoreBookcp copy = new StoreBookcp();
    private BookInterface bookinterface;

    public ControlBook(SearchBookForm bookinterface) {
        this.bookinterface = bookinterface;
    }
    
    public ControlBook(NewStart bookinterface) {
        this.bookinterface = bookinterface;
    }
    
    public ControlBook(BorrowedForm bookinterface) {
        this.bookinterface = bookinterface;
    }
    
    public ControlBook() {
    }

    public ControlBook(EditBookForm bookinterface) {
        this.bookinterface = (BookInterface) bookinterface;
    }
    
    /**
     * kiem tra ten sach da co trong database chua
     * @param title ten sach can tra
     * @return true la da co | false la chua co trong database
     * @throws SQLException 
     */
    private boolean CheckStoreBook(String title) throws SQLException {
        stbook = QueryBook.SearchTitle(title);
        return (!stbook.isEmpty());
    }
    
    
    /**
     * lay thong cac sach co cung ten duoc luu trong database
     * @param title ten sach can lay thong tin
     */
    private void GetInfoBook(String title) throws SQLException {
//        ArrayList<String> book = QueryBook.SearchTitle(title);
        books.setTitle(book.get(0));
//        books.setBid(book.get(1));
        books.setAuthor(book.get(2));
        books.setPublisher(book.get(3));
//        books.setPrice(book.get(4));
    }
    
    public void SearchBook(String title) {
        try {
            int count = stbook.size();
            if(CheckStoreBook(title)){
                for(int i =0; i< stbook.size(); i++){
                    StoreBook showbook = stbook.get(i);
                    String name = stbook.get(i).getTitle();
                    String bid = Integer.toString(stbook.get(i).getBid());
                    String author = stbook.get(i).getAuthor();
                    String publisher = stbook.get(i).getPublisher();
                    String price = Integer.toString(stbook.get(i).getPrice());
                    bookinterface.ShowBooks(bid, title, author, publisher, price);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControlBook.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void ShowBook(String cid) throws SQLException {
        GetInfoBookcpCID(cid);
    }
    
    public void PrintBook(String id) throws SQLException {
        book = new QueryBook().SearchBID(id);
        String name = book.get(0);
        String bid = book.get(1);
        String author = book.get(2);
        String publisher = book.get(3);
        String price =book.get(4);
        bookinterface.ShowBooks(bid, name, author, publisher, price);
        
    }
    
    public void GetInfoBookcp(String bid) throws SQLException {
        ArrayList<String> infocp = new QueryBookcp().SearchBookcp(bid);
        copy.setBID(infocp.get(2));
        copy.setCID(infocp.get(1));
        copy.setCPID(infocp.get(0));
        copy.setStatus(infocp.get(3));
    }
    
    public void GetInfoBookcpCID(String cid) throws SQLException {
        ArrayList<String> infocp = new QueryBookcp().SearchBookBorrowed(cid);
        int num = infocp.size();
        for (int i = 0; i < num; i=i+3) { 
            PrintBook(infocp.get(i));
        }
    }
    
    public void GetBookforBorrow(String bid) throws SQLException {
        int checkcount = new QueryBookcp().CountBookFree(bid);
        if(checkcount != 0) {
            GetInfoBookcp(bid);
            String cid = new ControlAccount().GetCard();
            if(cid != null)
                {
                String status = new ControlCard().GetStatus(cid);
                if(status.equals("1")) {
                    String cpid = copy.getCPID();

                    int noOfDays = 14;
                    Calendar cal = Calendar.getInstance();
                    cal.add(Calendar.DAY_OF_YEAR, noOfDays);
                    String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(cal.getTime());

                    new QueryBookcp().UpdateBorrow(cid, timeStamp, cpid);
                }
            }
        }
        else
            bookinterface.ShowErr();
    }
    
    public void GetInfoEditBook(String stbid, String title, String publisher, String author, String stprice) {
        StoreBook editbook = new StoreBook();
        int bid = Integer.parseInt(stbid);
        int price = Integer.parseInt(stprice);
        editbook.setBid(bid);
        editbook.setAuthor(author);
        editbook.setPrice(price);
        editbook.setTitle(title);
        editbook.setPublisher(publisher);
        bookinterface.ShowBooks(stbid, title, publisher, author, stprice);
    }
    
    public void UpdateBook(StoreBook editbook) throws SQLException {
        new QueryBook().UpdateBook(editbook);
    }
    
    public void InsertBook(String title, String publisher, String author, int price) throws SQLException {
        new QueryBook().InsertBook(title, publisher, author, price);
    }
    
    public void InsertBookcp(int bid, int number) throws SQLException {
        new QueryBookcp().InsertCopy(bid, number);
    }
}
