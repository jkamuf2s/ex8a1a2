package ooka.jkjh.dao;

import java.util.HashMap;
import ooka.jkjh.entities.User;


/**
 *
 * @author JAYDESKTOP
 */
public class UserDao {

    private static HashMap users = new HashMap<Integer,User>();
    private static UserDao instance = null;
    private static int id = 0;

    private UserDao() {
    }

    public static UserDao getInstance() {
        if (instance == null) {
            instance = new UserDao();
        }
        return instance;
    }
    
    public int addNewUser(User user){
        
        users.put(id,user);
        id++;
        
        return id-1;
        
    }
    
}
