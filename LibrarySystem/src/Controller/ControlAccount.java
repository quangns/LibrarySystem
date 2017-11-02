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
    public static ControlUser account = new ControlUser();
        
    public void GetInfo(String st) throws SQLException {
        ArrayList<String> user = new QueryUser(st).SearchUserName();
//        ControlUser account  = new ControlUser();
        account.SetUsername(user.get(0));
        account.SetPassword(user.get(1));
        account.SetRole(user.get(2));
    }
    
    public boolean CheckUsername(String st) throws SQLException {
        return st.equals(account.GetUsername());
    }
    
    public boolean CheckPassword(String st) throws SQLException {
        return st.equals(account.GetPassword());
    }
    
    public boolean CheckRole(String st) {
        return st.equals(account.GetRole());
    }
    
    public boolean CheckSignIn(String st1, String st2) throws SQLException {
        GetInfo(st1);
        boolean checkusername = new ControlAccount().CheckUsername(st1);
        boolean checkpw = new ControlAccount().CheckPassword(st2);
        if(checkusername)
            if(checkpw)
                return true;
        return false;
    }
}
