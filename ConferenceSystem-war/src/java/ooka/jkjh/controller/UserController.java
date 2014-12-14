package ooka.jkjh.controller;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

/**
 *
 * @author JAYDESKTOP
 */
@Named
@ApplicationScoped
public class UserController {
    
    private String emailAddress;

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    
    

    public String signIn() {
        
        return Pages.USER_ACTION_OVERVIEW;

    }

}
