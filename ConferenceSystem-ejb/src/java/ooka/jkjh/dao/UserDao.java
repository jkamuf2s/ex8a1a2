package ooka.jkjh.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateless;
import ooka.jkjh.entities.User;

/**
 *
 * @author JAYDESKTOP
 */
@Stateless
public class UserDao implements UserDaoLocal {

    private static HashMap users = new HashMap<Integer, User>();
    private static int id = 0;

    /*
     * Dummy entries that I do not have to create a new user every time I
     * redeploy
     *
     *
     */
    private static User dummyUserJochen = new User("Jochen", "Kamuf", "jochen@kamuf.de", "121212", "Host");
    private static User dummyUserJon = new User("Jon", "Herrmann", "jon@herrmann.de", "121212", "Participant");

    public UserDao() {
        dummyUserJochen.setId(Long.valueOf(id));
        users.put(id, dummyUserJochen);
        id++;
        dummyUserJon.setId(Long.valueOf(id));
        users.put(id, dummyUserJon);
        id++;
    }

    @Override
    public int addNewUser(User user) {

        Iterator it = users.entrySet().iterator();
        while (it.hasNext()) {

            HashMap.Entry pairs = (HashMap.Entry) it.next();
            User checkUser = (User) pairs.getValue();
            if (checkUser.getEmailAddress().equals(user.getEmailAddress())) {
                return -1;
            }

        }

        user.setId(Long.valueOf(id));
        users.put(id, user);

        id++;

        return id - 1;

    }

    @Override
    public User getUserByEmailAddress(String emailAddress) {

        Iterator it = users.entrySet().iterator();
        while (it.hasNext()) {

            HashMap.Entry pairs = (HashMap.Entry) it.next();
            User tmp = (User) pairs.getValue();
            System.out.println(tmp.getFirstName() + " " + tmp.getLastName() + "with id: " + pairs.getKey().toString());
            if (tmp.getEmailAddress().equals(emailAddress)) {
                return tmp;
            }
        }

        return null;
    }

    @Override
    public List<User> getUserListById(List<Long> userIDs) {

        List<User> resultUsers = new ArrayList<>();
        Iterator it = users.entrySet().iterator();
        while (it.hasNext()) {

            HashMap.Entry pairs = (HashMap.Entry) it.next();
            User tmp = (User) pairs.getValue();

            if (userIDs.contains(tmp.getId())) {
                resultUsers.add(tmp);
            }
        }

        return resultUsers;

    }

}
