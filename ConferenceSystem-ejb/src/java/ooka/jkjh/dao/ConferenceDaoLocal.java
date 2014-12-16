package ooka.jkjh.dao;

import java.util.HashMap;
import java.util.List;
import javax.ejb.Local;
import ooka.jkjh.entities.Conference;
import ooka.jkjh.entities.User;

/**
 *
 * @author JAYDESKTOP
 */
@Local
public interface ConferenceDaoLocal {

    List<Conference> getAllOpenConferences();

    int addConference(Conference conference, User hostUser);

    int addParticipantToConference(User user, int conferenceID);

    List<Conference> getConferencesAttendedByUser(User user);
    
    List<Conference> getConferencesCreatedByUser(User user);
    
    List<Conference> getConferencesWhereParticipantNotRegisteredAndOpen(User user);
    
    List<User> getParticipantsOfConference(Long conferenceID);
    
    public List<Conference> getAllConferencesWhichUserCanRate(User user);

    public boolean closeCOnference(Long conferenceID, Long userWhoClosedTheConference);

}
