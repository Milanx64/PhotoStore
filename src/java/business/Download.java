/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.Date;
import java.io.Serializable;
/**
 *
 * @author comp-one
 */
public class Download implements Serializable{
    private Long downloadId;
    private Long userID;
    private Date downloadDate;
    private String productCode;
    

    public Download() {
        
        downloadDate = new Date();
        productCode = "";
    }

    public Long getDownloadId() {
        return downloadId;
    }

    public void setDownloadId(Long downloadId) {
        this.downloadId = downloadId;
    }

    public void setUserID(Long id){
        this.userID = id;
    }
    public Long getUserID(){
        return userID;
    }
    public void setDownloadDate(Date date) {
        downloadDate = date;
    }

    public Date getDownloadDate() {
        return downloadDate;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductCode() {
        return productCode;
    }
    
     
}
