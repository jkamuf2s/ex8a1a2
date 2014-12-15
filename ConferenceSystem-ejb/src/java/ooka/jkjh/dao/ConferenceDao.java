package ooka.jkjh.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.Stateless;
import ooka.jkjh.entities.Conference;
import ooka.jkjh.entities.User;

/**
 *
 * @author JAYDESKTOP
 */
@Stateless
public class ConferenceDao implements ConferenceDaoLocal {

    private static HashMap conferences = new HashMap<Integer, Conference>();
    private static int id = 0;

    @Override
    public int addConference(Conference conference, User hostUser) {
        conference.setId(Long.valueOf(id));
        conference.setHostId(hostUser.getId());
        conferences.put(id, conference);

        id++;

        return id - 1;
    }

    @Override
    public List<Conference> getAllOpenConferences() {
        Iterator it = conferences.entrySet().iterator();
        List<Conference> openConfernces = new LinkedList();
        while (it.hasNext()) {

            HashMap.Entry pairs = (HashMap.Entry) it.next();
            Conference tmp = (Conference) pairs.getValue();

            if (tmp.isCompleted() == false) {
                openConfernces.add(tmp);
            }
        }

        return openConfernces;
    }

    @Override
    public int addParticipantToConference(User user, int conferenceID) {
        Iterator it = conferences.entrySet().iterator();

        while (it.hasNext()) {

            HashMap.Entry pairs = (HashMap.Entry) it.next();
            Conference conference = (Conference) pairs.getValue();
            Integer key = (Integer) pairs.getKey();

            if (key.equals(conferenceID)) {
                conference.addParticipant(user.getId());
                return id;
            }
        }

        return -1;
    }

    @Override
    public List<Conference> getConferencesAttendedByUser(User user) {

        Iterator it = conferences.entrySet().iterator();
        List<Conference> openConfernces = new LinkedList();
        while (it.hasNext()) {

            HashMap.Entry pairs = (HashMap.Entry) it.next();
            Conference tmp = (Conference) pairs.getValue();

            if (tmp.getParticipantIds().contains(user.getId())) {
                openConfernces.add(tmp);
            }
        }

        return openConfernces;

    }

    @Override
    public List<Conference> getConferencesCreatedByUser(User user) {

        Iterator it = conferences.entrySet().iterator();
        List<Conference> createdConferences = new LinkedList();
        while (it.hasNext()) {

            HashMap.Entry pairs = (HashMap.Entry) it.next();
            Conference tmp = (Conference) pairs.getValue();

            if (tmp.getHostId().equals(user.getId())) {
                createdConferences.add(tmp);
            }
        }

        return createdConferences;
    }

    @Override
    public List<Conference> getConferencesWhereParticipantNotRegistered(User user) {

        Iterator it = conferences.entrySet().iterator();
        List<Conference> notParticipatedConferences = new LinkedList();
        while (it.hasNext()) {

            HashMap.Entry pairs = (HashMap.Entry) it.next();
            Conference tmp = (Conference) pairs.getValue();

            if (!tmp.getParticipantIds().contains(user.getId())) {
                notParticipatedConferences.add(tmp);
            }
        }

        return notParticipatedConferences;

    }

}
