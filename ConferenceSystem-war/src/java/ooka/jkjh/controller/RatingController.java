package ooka.jkjh.controller;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import ooka.jkjh.dao.ConferenceDaoLocal;
import ooka.jkjh.dao.RatingDaoLocal;
import ooka.jkjh.entities.Conference;

/**
 *
 * @author JAYDESKTOP
 */
@ManagedBean
@SessionScoped
public class RatingController {
    
    private boolean conferencesToRateAvaliabe = true;

    public boolean isConferencesToRateAvaliabe() {
        return conferencesToRateAvaliabe;
    }
    
    
    
    
    @EJB
    private RatingDaoLocal ratingDao;
    @EJB
    private ConferenceDaoLocal confernceDao;
    
    
    public String doGetRateConference(){
        return Pages.RATE_A_CONFERENCE;
    }
    
    public String doRatingOfAconference(){
        return Pages.USER_ACTION_OVERVIEW;
    }
    
    public List<Conference> renderAllClosedAndNotRatedByUserConferences(){
        
        conferencesToRateAvaliabe = true;
        return confernceDao.getAllConferencesWhichUserCanRate(UserController.user);
        
        
    }
    
}
