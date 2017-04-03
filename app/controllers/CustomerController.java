package controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import models.Customer;
import play.data.Form;
import play.data.FormFactory;
import play.data.validation.Constraints;
import play.data.validation.ValidationError;
import play.libs.Json;
import play.mvc.*;
import util.Messages;

public class CustomerController extends Controller {
    
    @Inject
    FormFactory formFactory;
    
    

    public Result register(){
        Form<RegisterForm> registerForm=formFactory.form(RegisterForm.class).bindFromRequest();
        if(registerForm.hasErrors()){
            return badRequest(registerForm.errorsAsJson());
        }
        ObjectNode result = Json.newObject();
        result.put("status", "success");
        return ok(result);
    }
    
    public Result login(){
        Form<LoginForm> loginForm=formFactory.form(LoginForm.class).bindFromRequest();
        
        if(loginForm.hasErrors()){
        return badRequest(loginForm.errorsAsJson());
        }
        ObjectNode result = Json.newObject();
        result.put("status", "success");

        return ok(result);
    }
    
    
    public Result update(){
        return ok("come here to update");
    }

    
 public static class CustomerForm {
 @Constraints.Required
 @Constraints.Email
 public String email;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
 
 
}

public static class LoginForm extends CustomerForm {
    
   public List<ValidationError> validate() {
    List<ValidationError> errors = new ArrayList<ValidationError>();
    if(Customer.findByEmail(email)==null){
        errors.add(new ValidationError("invalidmail","Email not found"));
        return errors;
    }
    if(Customer.findByEmailAndPassword(email, password)==null){
        errors.add(new ValidationError("loginerror","Invalid email and password"));
    }
    return errors.isEmpty() ? null : errors;
}
    
 @Constraints.Required
 @Constraints.MinLength(4)
 public String password;

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
 
 
}


public static class RegisterForm extends LoginForm {
      
 @Constraints.Required
 @Constraints.MinLength(2)
 @Constraints.MaxLength(255)
 public String firstName;
 
 @Constraints.Required
 @Constraints.MinLength(2)
 @Constraints.MaxLength(255)
 public String lastName;
 
 @Constraints.Required
 @Constraints.MinLength(11)
 @Constraints.MaxLength(11)
 public String phoneNumber;
 
 public List<ValidationError> validate() {
    List<ValidationError> errors = new ArrayList<ValidationError>();
    if (Customer.findByEmail(email) != null) {
        errors.add(new ValidationError("email", "This e-mail is already registered."));
    }
    Messages validationMessage=Customer.validatePhoneNumber(phoneNumber);
    if(!validationMessage.isStatus()){
        errors.add(new ValidationError("phoneNumber",validationMessage.getMessage()));
    }
    return errors.isEmpty() ? null : errors;
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

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }
 
 

}




    
}