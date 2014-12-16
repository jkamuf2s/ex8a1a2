package ooka.jkjh.dao;

import javax.ejb.Local;
import ooka.jkjh.entities.User;

/**
 *
 * @author JAYDESKTOP
 */
@Local
public interface RatingDaoLocal {

    boolean rateClosedConference(Long userId, Long conferenceId, Integer rating);

    int addClosedConferenceForRating(Long conferenceID, Long userID);

    Integer getRatingsForHost(User host);

    boolean checkIfUserDidNotRateThisConference(Long userID, Long conferenceID);
    
    public boolean checkIfHostUserIsRestricted(User host);

    
    
}
