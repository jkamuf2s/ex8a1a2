package ooka.jkjh.controller;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import ooka.jkjh.dao.UserDaoLocal;
import ooka.jkjh.entities.User;

/**
 *
 * @author JAYDESKTOP
 */
@ManagedBean
@SessionScoped
public class UserController {

    private User user;

    private String lastName;
    private String firstName;
    private String emailAddress;
    private String password;
    private String userRole;

    @EJB
    private UserDaoLocal userDao;

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

        user = this.userDao.getUserByEmailAddress(emailAddress);
        lastName = user.getLastName();
        firstName = user.getFirstName();
        emailAddress = user.getEmailAddress();
        password = user.getPassword();
        userRole = user.getRole();

        return Pages.USER_ACTION_OVERVIEW;

    }

    public String signUp() {

        return Pages.SIGN_UP_PAGE;
    }

    public String createNewUser() {

        user = new User(lastName, firstName, emailAddress, password, userRole);

        this.userDao.addNewUser(user);

        return Pages.USER_ACTION_OVERVIEW;

    }

}
