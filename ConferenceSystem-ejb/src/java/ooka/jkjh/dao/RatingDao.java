package ooka.jkjh.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import ooka.jkjh.entities.Conference;
import ooka.jkjh.entities.ConferenceRating;
import ooka.jkjh.entities.User;

/**
 *
 * @author JAYDESKTOP
 */
@Stateless
public class RatingDao implements RatingDaoLocal {

    private static HashMap ratings = new HashMap<Integer, ConferenceRating>();
    private static int id = 0;

    @Override
    public int addClosedConferenceForRating(Long conferenceID, Long userID) {
        Iterator it = ratings.entrySet().iterator();
        while (it.hasNext()) {

            HashMap.Entry pairs = (HashMap.Entry) it.next();
            ConferenceRating checkConferenceRating = (ConferenceRating) pairs.getValue();
            if (checkConferenceRating.getConferenceId().equals(conferenceID)) {
                return -1;
            }

        }

        ConferenceRating conferenceRating = new ConferenceRating(conferenceID, userID);

        conferenceRating.setId(Long.valueOf(id));
        conferenceRating.setUserId(userID);
        ratings.put(id, conferenceRating);

        id++;

        return id - 1;
    }

    @Override
    public boolean rateClosedConference(Long userId, Long conferenceId, Integer rating) {
        Iterator it = ratings.entrySet().iterator();

        while (it.hasNext()) {

            HashMap.Entry pairs = (HashMap.Entry) it.next();
            ConferenceRating tmp = (ConferenceRating) pairs.getValue();

            if (tmp.getConferenceId().equals(conferenceId)) {

                return tmp.submitRatingForUser(userId, conferenceId, rating);
            }
        }

        return false;

    }

    @Override
    public Integer getRatingsForHost(User host) {
        Iterator it = ratings.entrySet().iterator();
        Integer ratingSum = 0;

        while (it.hasNext()) {

            HashMap.Entry pairs = (HashMap.Entry) it.next();
            ConferenceRating tmp = (ConferenceRating) pairs.getValue();

            if (tmp.getUserId().equals(host.getId())) {

                ratingSum += tmp.getRatingValue();
            }
        }

        return ratingSum;
    }

    @Override
    public boolean checkIfUserDidNotRateThisConference(Long userID, Long conferenceID) {
        ConferenceRating conferenceRating = (ConferenceRating) ratings.get(conferenceID.intValue());
        if (conferenceRating != null) {
            return conferenceRating.checkIfUserCanStillRateTheConference(userID, conferenceID);
        }
        return false;
    }

    @Override
    public boolean checkIfHostUserIsRestricted(User host) {
        Iterator it = ratings.entrySet().iterator();
        int ratingSum = 0;
        int ratedConferncesSum = 0;

        while (it.hasNext()) {

            HashMap.Entry pairs = (HashMap.Entry) it.next();
            ConferenceRating tmp = (ConferenceRating) pairs.getValue();

            if (tmp.getUserId().equals(host.getId())) {

                ratingSum += tmp.getRatingValue().intValue();
                ratedConferncesSum++;
            }
        }

        if (ratedConferncesSum > 0) {
            if (((float)ratingSum / (float)ratedConferncesSum) < 5) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }

    }

}
