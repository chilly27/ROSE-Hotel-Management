/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.sql.Date;

/**
 *
 * @author Admin
 */
public class Customer {
    private int customerID;
    private String userName;
    private String email;
    private String avatar;
    private String password; 
    private Date dob; 
    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    private char gender; // Gender with constraint
    private String cusPhone; // Customer Phone with constraint
    private String role;
    private String googleID;

    public String getGoogleID() {
        return googleID;
    }

    public void setGoogleID(String googleID) {
        this.googleID = googleID;
    }

    public Customer() {
    }

    public Customer(String email, String userName, String avatar, String password, String cusPhone,Date dob , String role, char gender) {
        
        this.email = email;
        this.userName = userName;
        this.avatar = avatar;
        this.password = password;
        this.dob = dob;
        this.gender = gender;
        this.cusPhone = cusPhone;
        this.role = role;
    }
    
    
    public Customer(int customerID,String email, String userName, String password, String cusPhone,Date dob , String role, char gender) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.dob = dob;
    
        this.gender = gender;
        this.cusPhone = cusPhone;
        this.role = role;
    }
//    customerID INT IDENTITY(1,1) PRIMARY KEY, -- Tự động tăng và là khóa chính
//    userName NVARCHAR(50) NOT NULL, -- Tên người dùng, không được để trống
//    email NVARCHAR(100) NULL UNIQUE, -- Địa chỉ email, không được để trống và phải là duy nhất
//    avatar NVARCHAR(255), -- URL hoặc đường dẫn đến ảnh đại diện
//    password NVARCHAR(255) NULL, -- Mật khẩu, không được để trống
//    dob DATE, -- Ngày sinh
//    address NVARCHAR(255), -- Địa chỉ
//    gender CHAR(1) CHECK (gender IN ('M', 'F', 'O')), -- Giới tính, với ràng buộc CHECK
//    cusPhone NVARCHAR(15) CHECK (cusPhone LIKE '[0-9]%'), -- Số điện thoại với ràng buộc CHECK
//    role NVARCHAR(15) NOT NULL DEFAULT 'customer',
//    googleID NVARCHAR(100) -- ID Google (nếu có)
//);
    public Customer(int customerID, String userName, String email, String avatar, String password, Date dob, String address, char gender, String cusPhone, String role, String googleID) {
        this.customerID = customerID;
        this.userName = userName;
        this.email = email;
        this.avatar = avatar;
        this.password = password;
        this.dob = dob;
        this.address = address;
        this.gender = gender;
        this.cusPhone = cusPhone;
        this.role = role;
        this.googleID = googleID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
    
    

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public String getCusPhone() {
        return cusPhone;
    }

    public void setCusPhone(String cusPhone) {
        this.cusPhone = cusPhone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
    
    

    // Method to check valid email format
    public boolean isEmailValid() {
        return email != null && email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    // Method to check valid phone number format
    public boolean isPhoneValid() {
        return cusPhone != null && cusPhone.matches("\\d{10}");
    }

    // Method to check valid gender
    public boolean isGenderValid() {
        return gender == 'M' || gender == 'W' || gender == 'O';
    }
}
                             

