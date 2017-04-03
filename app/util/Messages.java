/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Adeyemi
 */
public class Messages {
    
    MessageType type;
    boolean status;
    String message;
    
    public Messages(){
    
    }

    public Messages(MessageType type,boolean status,String message){
        this.type=type;
        this.status=status;
        this.message=message;
    };
    
    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
    
}
