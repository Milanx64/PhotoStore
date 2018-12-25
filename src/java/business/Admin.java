/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

/**
 *
 * @author comp-one
 */
public class Admin {
    private int id;
    private String name;
    private String lastname;
    private String password;
    private String email;
    private String address;
    
    public Admin(){
        id = 0;
        name = "";
        lastname = "";
        password = "";
        email = "";
        address = "";
    }
    
    //Get and Set methods
    
    public void setId(int id){
        this.id = id;
    }
    public int getId(){
        return id;
    }
    
    public void setName(String name){
        this.name = name;
    }
     public String getName(){
       return name;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getLastname(){
        return lastname;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword(){
        return password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getEmail(){
        return email;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getAddress(){
        return address;
    }
    

}
