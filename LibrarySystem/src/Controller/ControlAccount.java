/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entity.QueryUser;
import java.sql.SQLException;
import java.util.ArrayList;
import Controller.ControlUser;

/**
 *
 * @author quangns
 */
public class ControlAccount {
    private static ControlUser account = new ControlUser();
    
    
    /**
     * lay du lieu nguoi dung theo username trong lan kiem tra dau tien, 
     * luu lai de khong bi truy van nhieu lan
     * @param st username cua nguoi dung nhap vao
     * @throws SQLException 
     */
    private void GetInfo(String st) throws SQLException {
        ArrayList<String> user = new QueryUser(st).SearchUserName();
        account.SetUsername(user.get(0));
        account.SetPassword(user.get(1));
        account.SetRole(user.get(2));
    }
    
    private boolean CheckStoreUsername(String st) throws SQLException {
        ArrayList<String> user = new QueryUser(st).SearchUserName();
        return user.isEmpty();
}

    private boolean CheckUsername(String st) throws SQLException {
        return st.equals(account.GetUsername());
    }
    
    private boolean CheckPassword(String st) throws SQLException {
        return st.equals(account.GetPassword());
    }
    
    private boolean CheckRole(String st) {
        return st.equals(account.GetRole());
    }
    
    protected boolean CheckHaveSpace(String st) {
        if(st == null || st.equals(""))
            return false;
        String [] array = st.split("");
        for(int i =0; i<array.length; i++) {
            if(array[i].equals(" "))
                return false;
        }
        return true;
    }
    
    
    /**
     * lay cac du lieu lien quan de nguoi su dung, 
     * kiem tra co phu hop trong database khong
     * @param username username nguoi dung da nhap
     * @param password password nguoi dung da nhap
     * @return sau khi da xac thuc dang nhap vao he thong
     * @return 1 de tra ve man hinh user
     * @return 2 de tra ve man hinh librarian
     * @return 0 bao sai thong tin
     * @throws SQLException
     */
    public boolean CheckSignIn(String username, String password) throws SQLException {
        if(CheckHaveSpace(username) && CheckHaveSpace(password)) {
            GetInfo(username);
            boolean checkpw = new ControlAccount().CheckPassword(password);
            if(checkpw)
                return true;
            }
        return false;
    }
    
    
    /**
     * kiem tra username da co trong database chua,
     * neu chua co thi moi duoc dang ki them vao database
     * @param firstname ten cua nguoi dung dang ki
     * @param lastname ho cua nguoi dung dang ki
     * @param username username nguoi dung muon dang ki
     * @param password password nguoi dung dang ki
     * @return 
     */
    public boolean Register(String firstname, String lastname, String username, String password) throws SQLException {
        if(CheckHaveSpace(username)) {
            CheckStoreUsername(username);
            boolean checkusername = new ControlAccount().CheckUsername(username);
            if(!checkusername) {
                new QueryUser(firstname, lastname, username, password).InsertUser();
                return true;
            }
            return false;
        }
        return false;        
    }
    
    public static void main(String[] args) throws SQLException {
        String st = "minh";
        ControlAccount string = new ControlAccount();
//        System.out.println(string.CheckSignIn("minh", "43"));
        System.out.println(string.Register("nguyen", "quang", "hoang", "123"));
    }
}
