package ooka.jkjh.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author JAYDESKTOP
 */
@ManagedBean
@SessionScoped
public class UserController {

    private String lastName;
    private String firstName;
    private String emailAddress;
    private String password;
    private String userRole;

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String signIn() {

        return Pages.USER_ACTION_OVERVIEW;

    }

    public String signUp() {

        return Pages.SIGN_UP_PAGE;
    }

    public String createNewUser() {

        return Pages.USER_ACTION_OVERVIEW;

    }

}
