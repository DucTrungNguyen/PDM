/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author Administrator
 */
public class DTO {
    //private String name;
    //private String pass;
    public static String name = new String();
    public static String pass = new String();
    public String getName(){
        return name;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    
    public String getPass(){
         return pass;
    }
    
    public void setPass(String pass){
        this.pass = pass;
    }

    
}
