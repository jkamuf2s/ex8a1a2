package ooka.jkjh.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author JAYDESKTOP
 */
@Entity
public class ConferenceRating implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private List<Long> userIdsWhoSubmittedRating = new ArrayList<>();
    private Long conferenceId;
    private Long userId;
    private Integer ratingValue = 0;

    public ConferenceRating(Long conferenceId, Long userId) {
        this.conferenceId = conferenceId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    
    
    public boolean submitRatingForUser(Long userId, Long conferenceID, Integer rating) {

        if (!userIdsWhoSubmittedRating.contains(userId) && conferenceId.equals(conferenceID)) {
            ratingValue += rating;
            userIdsWhoSubmittedRating.add(userId);
            return true;
        }
        return false;
    }

    public boolean checkIfUserCanStillRateTheConference(Long userId, Long conferenceID) {

        if (!userIdsWhoSubmittedRating.contains(userId) && conferenceId.equals(conferenceID)) {
            return true;
        } else {
            return false;
        }

    }

    public Integer getRatingValue() {
        return ratingValue;
    }

    public void setConferenceId(Long ratingForConference) {
        this.conferenceId = ratingForConference;
    }

    public Long getConferenceId() {
        return conferenceId;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ConferenceRating)) {
            return false;
        }
        ConferenceRating other = (ConferenceRating) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ooka.jkjh.entities.ConferenceRating[ id=" + id + " ]";
    }

}
