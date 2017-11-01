/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import entity.QueryUser;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author quangns
 */
public class ControlAccount {
    public boolean CheckUsername(String st) throws SQLException {
        ArrayList<String> user = new QueryUser(st).SearchUserName();
        return st.equals(user.get(0));
    }
    
    public boolean CheckPassword(String st) throws SQLException {
        ArrayList<String> user = QueryUser.SearchUserName();
        return st.equals(user.get(1));
    }
    
    public boolean CheckSignIn(String st1, String st2) throws SQLException {
        boolean checkusername = new ControlAccount().CheckUsername(st1);
        boolean checkpw = new ControlAccount().CheckPassword(st2);
        if(checkusername)
            if(checkpw)
                return true;
        return false;
        
    }
}
