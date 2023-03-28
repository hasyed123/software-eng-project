package org.example.data.dao;

import com.google.inject.Inject;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.example.model.Club;
import org.example.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class ClubDao {

    private final SessionFactory sessionFactory;

    @Inject
    public ClubDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Club> getAllClubs() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Club> criteria = builder.createQuery(Club.class);
        criteria.from(Club.class);
        List<Club> clubs = session.createQuery(criteria).getResultList();
        session.close();
        return clubs;
    }

    public Club getClubById(String clubName) {
        Session session = sessionFactory.openSession();
        Club club = session.get(Club.class, clubName);
        session.close();
        return club;
    }

    public void addOrUpdateClub(Club club) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(club);
        try {
            transaction.commit();
        }
        catch (Exception e) {}
        session.close();
    }
}
