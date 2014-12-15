package ooka.jkjh.controller;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import ooka.jkjh.dao.ConferenceDaoLocal;
import ooka.jkjh.entities.Conference;

/**
 *
 * @author JAYDESKTOP
 */
@ManagedBean
@SessionScoped
public class ConferenceController {
    
    private String conferenceName;
    private Conference conferenceToCreate;
    private List<Conference> openConferences;
    
    
    @EJB
    private ConferenceDaoLocal conferenceDao;
    
    

    public String getConferenceName() {
        return conferenceName;
    }

    public void setConferenceName(String conferenceName) {
        this.conferenceName = conferenceName;
    }

    public List<Conference> getOpenConferences() {
        return openConferences;
    }


    
    
    
    
    public String doIndex(){
        return Pages.INDEX_PAGE;
    }
    
        public String doOverview(){
        return Pages.USER_ACTION_OVERVIEW;
    }
    
    public String doCreateNewConference(){
        
        return Pages.CREATE_CONFERENCE;
    }
    
        public String doRegisterAtConference(){
        
        return Pages.REGISTER_AT_CONFERENCE;
    }
    
    public String createNewConference(){
        
        conferenceToCreate = new Conference();
        conferenceToCreate.setName(conferenceName);
        conferenceDao.addConference(conferenceToCreate, UserController.user);
        openConferences = conferenceDao.getAllOpenConferences();
        
        return Pages.USER_ACTION_OVERVIEW;
    }
    
    public String registerUserAtConference(Conference conferenceToRegister){
        
        conferenceDao.addParticipantToConference(UserController.user, conferenceToRegister.getId().intValue());
        return Pages.USER_ACTION_OVERVIEW;
    }
    
    public List<Conference> showRegisteredConferencesOfUser(){
        
        return conferenceDao.getConferencesAttendedByUser(UserController.user);
        
        
    }
    
        public List<Conference> showCreatedConferencesOfUser(){
        
        return conferenceDao.getConferencesCreatedByUser(UserController.user);
        
        
    }
    
}
