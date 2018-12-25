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
import java.text.NumberFormat;
import java.io.Serializable;

public class Product implements Serializable{
     private Long productId;
    private String artistEmail;
    private String code;
    private String imageURL;
    private String type;
    private String description;
    private double price;
    private String name;
    private String title;
    
    public Product(){
        code="";
        description = "";
        price = 0;
        imageURL = "";
        type = "";
        description = "";
        name = "";
        title = "";
        artistEmail="";
    }
     public Long getId() {
        return productId;
    }

    public void setId(Long productId) {
        this.productId = productId;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getCode(){
        return code;
    }
    
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return description;
    }
    public String getArtistName(){
        String artistName= description.substring(0, description.indexOf(" - "));
        return artistName;
    }
    public void setPrice(double price){
        this.price = price;
    }
    public double getPrice(){
        return price;
    }
    public String getpriceCurrencyFormat(){
        NumberFormat currency = NumberFormat.getCurrencyInstance();
        return currency.format(price);
    }
    public void setImageURL(String url){
            this.imageURL = url;
    
    }
    public String getImageURL(){
        return imageURL;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return type;
    }
     public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return name;
    }
     public void setTitle(String title){
        this.title = title;
    }
    public String getTitle(){
        return title;
    }
     public void setArtistEmail(String artistEmail){
        this.artistEmail = artistEmail;
    }
    public String getArtistEmail(){
        return artistEmail;
    }
    
    
}
