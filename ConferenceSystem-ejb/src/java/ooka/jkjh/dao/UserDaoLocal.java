package ooka.jkjh.dao;

import ooka.jkjh.entities.User;

/**
 *
 * @author JAYDESKTOP
 */
public interface UserDaoLocal {
    public int addNewUser(User user);

    User getUserByEmailAddress(String emailAddress);
}
