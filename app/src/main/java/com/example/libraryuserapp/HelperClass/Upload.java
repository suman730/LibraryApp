package com.example.libraryuserapp.HelperClass;

public class Upload {

    private String username;
    private String password;
    private String reenter_password;
    private String email;
    private String mobileNo;
    private String vcode;
    private String imageUrl;

    public Upload() {

    }

    public Upload(String vcode, String username, String password, String reenter_password, String email, String mobileNo, String imageUrl) {

        this.vcode=vcode;
        this.username = username;
        this.password= password;
        this.reenter_password = reenter_password;
        this.email = email;
        this.mobileNo= mobileNo;
        this.imageUrl=imageUrl;

    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getReenter_password() {
        return reenter_password;
    }
    public String getEmail() {
        return email;
    }
    public String getMobileNo() {
        return mobileNo;
    }
    public String getVcode() {
        return vcode;
    }

    public void setName(String vcode,String username, String password, String reenter_password, String email,String mobileNo,String imageUrl) {
        this.vcode=vcode;
        this.username = username;
        this.password = password;
        this.reenter_password= reenter_password;
        this.email= email;
        this.mobileNo=mobileNo;
        this.imageUrl=imageUrl;

    }
}
