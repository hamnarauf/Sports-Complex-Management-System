/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

/**
 *
 * @author Sana Zehra
 */
public class User {
    private String uname;
    private String pass;

    public User(String uname, String pass){
        this.uname = uname;
        this.pass = pass;
    }

    public String getUname(){
        return this.uname;
    }

    public void setUname(String uname){
        this.uname = uname;
    }

    public String getPass(){
        return pass;
    }

    public void setPass(String pass){
        this.pass = pass;
    }
}
