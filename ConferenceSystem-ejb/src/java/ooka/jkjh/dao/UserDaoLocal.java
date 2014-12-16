package ooka.jkjh.dao;

import java.util.List;
import ooka.jkjh.entities.User;

/**
 *
 * @author JAYDESKTOP
 */
public interface UserDaoLocal {

    public int addNewUser(User user);

    User getUserByEmailAddress(String emailAddress);

    public List<User> getUserListById(List<Long> userIDs);
}
