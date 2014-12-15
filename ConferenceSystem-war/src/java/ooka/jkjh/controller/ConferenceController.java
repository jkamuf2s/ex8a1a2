package ooka.jkjh.controller;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author JAYDESKTOP
 */
@ManagedBean
@SessionScoped
public class ConferenceController {
    
    
    public String doIndex(){
        return Pages.INDEX_PAGE;
    }
    
    public String createNewConference(){
        
        return Pages.CREATE_CONFERENCE;
    }
    
}
