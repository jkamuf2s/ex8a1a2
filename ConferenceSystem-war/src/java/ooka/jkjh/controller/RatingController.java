package ooka.jkjh.controller;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import ooka.jkjh.dao.ConferenceDaoLocal;
import ooka.jkjh.dao.RatingDaoLocal;
import ooka.jkjh.entities.Conference;
import ooka.jkjh.entities.User;

/**
 *
 * @author JAYDESKTOP
 */
@ManagedBean
@SessionScoped
public class RatingController {

    private boolean conferencesToRateAvaliabe = true;
    private User loggedInUser;

    public boolean isConferencesToRateAvaliabe() {
        return conferencesToRateAvaliabe;
    }

    @EJB
    private RatingDaoLocal ratingDao;
    @EJB
    private ConferenceDaoLocal confernceDao;

    public void doSetCurrentUser(User user) {
        this.loggedInUser = user;
    }

    public String doGetRateConference() {
        return Pages.RATE_A_CONFERENCE;
    }

    public String doRatingOfAconference() {
        return Pages.USER_ACTION_OVERVIEW;
    }

    public List<Conference> renderAllClosedAndNotRatedByUserConferences() {

        conferencesToRateAvaliabe = true;
        return confernceDao.getAllConferencesWhichUserCanRate(loggedInUser);

    }

}
