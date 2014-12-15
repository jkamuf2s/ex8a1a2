package ooka.jkjh.dao;

import java.util.HashMap;
import java.util.Iterator;
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

        throw new IllegalAccessError("User not in Database");
    }

}
