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
public class User {
        private Long userId;
        private String name;
	private String lastname;
	private String email;
	private String password;
        private String about;
        private String coverPhoto;
        private String profilePhoto;
	private String creditCardType;
        private String creditCardNumber;
        private String creditCardExpirationDate;
	
	public User() {
		name = "";
		lastname = "";
                //userId = "";
		email = "";
		password = "";
                about = "";
                coverPhoto = "";
                profilePhoto = "";
                creditCardType = "";
                creditCardNumber = "";
                creditCardExpirationDate = "";
	}
	
	
        public Long getId() {
            return userId;
        }

        public void setId(Long userId) {
            this.userId = userId;
        }
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPassword() {
		return password;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
        public void setCardType(String  creditCartType) {
            
        this.creditCardType = creditCartType;
    }

        public String getCardType() {
            return creditCardType;
        }

        public void setCardNumber(String creditCardNumber) {
            this.creditCardNumber = creditCardNumber;
        }

        public String getCardNumber() {
            return creditCardNumber;
        }

        public void setExpDate(String creditCardExpirationDate) {
            this.creditCardExpirationDate = creditCardExpirationDate;
        }

        public String getExpDate() {
            return creditCardExpirationDate;
        }
        
        public void setAbout(String about){
            this.about = about;
        }
        public String getAbout(){
            return about;
        }
        
        public void setCoverPhoto(String coverPhoto){
            this.coverPhoto = coverPhoto;
        }
        public String getCoverPhoto(){
            return coverPhoto;
        }
        public void setProfilePhoto(String profilePhoto){
            this.profilePhoto = profilePhoto;
        }
        public String getProfilePhoto(){
            return profilePhoto;
        }
}
