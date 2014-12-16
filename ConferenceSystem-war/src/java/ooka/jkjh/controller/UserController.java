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
    private int id;
    boolean passwordWrongWarningUserDoesNotExist = false;
    boolean emailAdressAlreadyTaken = false;

    @EJB
    private UserDaoLocal userDao;

    public boolean isPasswordWrongWarningUserDoesNotExist() {
        return passwordWrongWarningUserDoesNotExist;
    }

    public boolean isEmailAdressAlreadyTaken() {
        return emailAdressAlreadyTaken;
    }

    public User getUser() {
        return user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

        if (user == null) {
            return wrongPassword();
        }

        if (!password.equals(user.getPassword())) {
            return wrongPassword();
        } else {

            lastName = user.getLastName();
            firstName = user.getFirstName();
            userRole = user.getRole();
            passwordWrongWarningUserDoesNotExist = false;
            return Pages.USER_ACTION_OVERVIEW;
        }

    }

    public String signUp() {

        emailAdressAlreadyTaken = false;
        return Pages.SIGN_UP_PAGE;
    }

    public String createNewUser() {

        user = new User(lastName, firstName, emailAddress, password, userRole);

        this.id = this.userDao.addNewUser(user);
        if (this.id == -1) {
            return emailAdressAlreadyTaken();
        } else {
            emailAdressAlreadyTaken = false;
            return Pages.USER_ACTION_OVERVIEW;
        }

    }

    public String signOut() {
        user = null;
        return Pages.INDEX_PAGE;
    }

    public String wrongPassword() {
        user = null;
        passwordWrongWarningUserDoesNotExist = true;
        return Pages.INDEX_PAGE;
    }

    public String emailAdressAlreadyTaken() {
        user = null;
        emailAdressAlreadyTaken = true;
        return Pages.SIGN_UP_PAGE;

    }

}
