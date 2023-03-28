package org.example.business;

import com.google.inject.Injector;
import org.example.data.dao.ClubDao;
import org.example.data.dao.EventDao;
import org.example.data.dao.UserDao;
import org.example.model.Club;
import org.example.model.Event;
import org.example.model.User;

public class CemsService {

    private final Injector injector;
    private final UserDao userDao;
    private final ClubDao clubDao;
    private final EventDao eventDao;

    public CemsService(Injector injector) {
        this.injector = injector;
        userDao = injector.getInstance(UserDao.class);
        clubDao = injector.getInstance(ClubDao.class);
        eventDao = injector.getInstance(EventDao.class);
    }

    //Returns the User object if credentials are correct, else returns null
    public User tryCredentials(String username, String password) {
        User user = userDao.getUserById(username);
        if(user == null) return null;
        else if(password.equals(user.getPassword())) return user;
        else return null;
    }

    public Boolean addMemberToClub(String clubName, String username) {
        User user = userDao.getUserById(username);
        Club club = clubDao.getClubById(clubName);
        if(user==null || club==null) return false;
        if(club.getMembers().contains(user)) return false;
        club.addMember(user);
        clubDao.addOrUpdateClub(club);
        return true;
    }

    public Boolean addMemberToEvent(Integer eventId, String username) {
        User user = userDao.getUserById(username);
        Event event = eventDao.getEventById(eventId);
        if(user==null || event==null) return false;
        if(event.getAttendees().contains(user)) return false;
        event.addAttendee(user);
        eventDao.addOrUpdateEvent(event);
        return true;
    }

    public User getUser(String username) {
        return userDao.getUserById(username);
    }

    public Club getClub(String clubName) {
        return clubDao.getClubById(clubName);
    }

    public Event getEvent(Integer id) {
        return eventDao.getEventById(id);
    }
}
