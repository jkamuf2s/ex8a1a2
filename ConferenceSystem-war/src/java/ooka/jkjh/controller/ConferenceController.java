package ooka.jkjh.controller;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import ooka.jkjh.dao.ConferenceDaoLocal;
import ooka.jkjh.entities.Conference;
import ooka.jkjh.entities.User;

/**
 *
 * @author JAYDESKTOP
 */
@ManagedBean
@SessionScoped
public class ConferenceController {

    private String conferenceName;
    private Conference conferenceToCreate;
    private Long conferenceId;
    private boolean conferenceIsOpenAndDoesExist = false;
    private User loggedInUser;

    @EJB
    private ConferenceDaoLocal conferenceDao;

    public void doSetCurrentUser(User user) {
        this.loggedInUser = user;
    }

    public boolean isConferenceIsOpenAndDoesExist() {
        return conferenceIsOpenAndDoesExist;
    }

    public void setConferenceId(Long conferenceId) {
        this.conferenceId = conferenceId;
    }

    public String getConferenceName() {
        return conferenceName;
    }

    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }

    public List<Conference> getOpenConferences() {
        return conferenceDao.getAllOpenConferences();
    }

    public String doIndex() {
        return Pages.INDEX_PAGE;
    }

    public String doOverview() {
        return Pages.USER_ACTION_OVERVIEW;
    }

    public String doCreateNewConference() {

        return Pages.CREATE_CONFERENCE;
    }

    public String doRegisterAtConference() {

        return Pages.REGISTER_AT_CONFERENCE;
    }

    public String createNewConference() {

        conferenceToCreate = new Conference();
        conferenceToCreate.setName(conferenceName);
        if (-1 == conferenceDao.addConference(conferenceToCreate, loggedInUser)) {
            return conferenceWithThatNameAlreadyOpen();
        } else {
            conferenceIsOpenAndDoesExist = false;
            return Pages.USER_ACTION_OVERVIEW;
        }

    }

    public String registerUserAtConference(Conference conferenceToRegister) {

        conferenceDao.addParticipantToConference(loggedInUser, conferenceToRegister.getId().intValue());
        return Pages.USER_ACTION_OVERVIEW;
    }

    public List<Conference> showRegisteredConferencesOfUser() {

        return conferenceDao.getConferencesAttendedByUser(loggedInUser);

    }

    public List<Conference> showCreatedConferencesOfUser() {

        return conferenceDao.getConferencesCreatedByUser(loggedInUser);

    }

    public List<Conference> showConferencesWhereParticipantNotRegisteredAndOpen() {

        return conferenceDao.getConferencesWhereParticipantNotRegisteredAndOpen(loggedInUser);

    }

    public List<User> showParticipantsOfConference(Long conferenceId) {

        return conferenceDao.getParticipantsOfConference(conferenceId);

    }

    public String conferenceWithThatNameAlreadyOpen() {
        conferenceIsOpenAndDoesExist = true;
        return Pages.CREATE_CONFERENCE;
    }

    public String closeOpenConference(Long conferenceID) {
        conferenceDao.closeCOnference(conferenceID, loggedInUser.getId());
        return Pages.USER_ACTION_OVERVIEW;
    }

}
