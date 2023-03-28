package org.example.data.dao;

import com.google.inject.Inject;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import org.example.model.Club;
import org.example.model.Event;
import org.example.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class EventDao {

    private final SessionFactory sessionFactory;

    @Inject
    public EventDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Event> getAllEvents() {
        Session session = sessionFactory.openSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Event> criteria = builder.createQuery(Event.class);
        criteria.from(Event.class);
        List<Event> events = session.createQuery(criteria).getResultList();
        session.close();
        return events;
    }

    public Event getEventById(Integer id) {
        Session session = sessionFactory.openSession();
        Event event = session.get(Event.class, id);
        session.close();
        return event;
    }

    public void addOrUpdateEvent(Event event) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.merge(event);
        try {
            transaction.commit();
        }
        catch (Exception e) {}
        session.close();
    }
}
