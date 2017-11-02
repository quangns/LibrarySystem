/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

/**
 *
 * @author quangns
 */
public class ControlUser {
    public String username;
    public String password;
    public String role;
    public int cardID;
    
    public ControlUser(String username, String password, String role, int cardID) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.cardID = cardID;
    }
    
    public ControlUser() {}
    
    public void SetUsername(String username) {
        this.username = username;
    }
    
    public String GetUsername() {
        return this.username;
    }
    
    public void SetPassword(String password) {
        this.password = password;
    }
    
    public String GetPassword() {
        return this.password;
    }
    
    public void SetRole(String Role) {
        this.role = role;
    }
    
    public String GetRole() {
        return this.role;
    }
    
    public void SetCardID(int cardID) {
        this.cardID = cardID;
    }
    
    public int GetCardID() {
        return this.cardID;
    }
}
