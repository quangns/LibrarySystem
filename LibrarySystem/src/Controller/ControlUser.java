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
    protected String username;
    protected String password;
    protected String role;
    protected int cardID;
    
    ControlUser(String username, String password, String role, int cardID) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.cardID = cardID;
    }
    
    ControlUser() {}
    
    void SetUsername(String username) {
        this.username = username;
    }
    
    String GetUsername() {
        return this.username;
    }
    
    void SetPassword(String password) {
        this.password = password;
    }
    
    String GetPassword() {
        return this.password;
    }
    
    void SetRole(String Role) {
        this.role = role;
    }
    
    String GetRole() {
        return this.role;
    }
    
    void SetCardID(int cardID) {
        this.cardID = cardID;
    }
    
    int GetCardID() {
        return this.cardID;
    }
}
