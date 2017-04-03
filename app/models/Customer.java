/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;


import com.avaje.ebean.Model;
import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.persistence.*;
import util.MessageType;
import util.Messages;

/**
 *
 * @author Adeyemi
 */
@Entity
@Table(name="Customer")
public class Customer extends Model {
    
    @Id
    @GeneratedValue
    Long id;
   
    @Column(length=255,name="firstname",nullable=false)
    String firstName;
    
    @Column(length = 255,name="lastname",nullable = false)
    String lastName;
    
    @Column(length = 255,name="email",unique = true, nullable = false)
    String email;
    
    @Column(name="password",length = 64,nullable = false)
    byte[] password;
    
    @Column(length = 15,name="phonenumber",nullable = false)
    String phoneNumber;
    
    public static Finder<Long, Customer> find = new Finder<Long,Customer>(Customer.class);


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = getSha512(password);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    
    
 public static byte[] getSha512(String value) {
   try {
     return MessageDigest.getInstance("SHA-512").digest(value.getBytes("UTF-8"));
   }
   catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
     throw new RuntimeException(e);
   }
 }
    
 public static Customer findByEmailAndPassword(String email, String password) {
    return find
       .where()
       .eq("email", email.toLowerCase())
       .eq("password", getSha512(password))
       .findUnique();
 }

 public static Customer findByEmail(String email) {
    return find
       .where()
       .eq("email", email.toLowerCase())
       .findUnique();
 }

 
 public static Messages validatePhoneNumber(String number){
     PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
     try {
        PhoneNumber numberInstance = phoneUtil.parse(number, "NG");
        boolean isValid = phoneUtil.isValidNumberForRegion(numberInstance,"NG"); 
        
        if(isValid){
            return new Messages(MessageType.VALIDATION_REPORT,true,null);
        }
        else{
            return new Messages(MessageType.VALIDATION_REPORT,false,"Please enter a valid phone number for Nigeria");
        }
        
        } catch (NumberParseException e) {
         return new Messages(MessageType.VALIDATION_REPORT,false,"This is not a valid phone number");
    }
     }
    
    
    
}
