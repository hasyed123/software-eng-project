package org.example.business;

import com.google.inject.Injector;
import org.example.data.dao.ClubDao;
import org.example.data.dao.EventDao;
import org.example.data.dao.UserDao;
import org.example.model.Club;
import org.example.model.Event;
import org.example.model.User;

import java.util.List;

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

    // Registers user for event if user is in the club organizing the event
    public Boolean addMemberToEvent(Integer eventId, String username) {
        User user = userDao.getUserById(username);
        Event event = eventDao.getEventById(eventId);
        if(user==null || event==null) return false;
        Club club = event.getClub();
        if(event.getAttendees().contains(user) || !club.getMembers().contains(user)) return false;
        event.addAttendee(user);
        eventDao.addOrUpdateEvent(event);
        return true;
    }

    // method that checks if a user is a president of a club, and if they are returns the club, otherwise returns null
    public Club getClubIfPresident(String username) {
        User user = userDao.getUserById(username);
        if(user==null) return null;
        List<Club> clubs = clubDao.getAllClubs();
        for (Club club : clubs) {
            if (club.getPresident().equals(user)) {
                return club;
            }
        }
        return null;
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
