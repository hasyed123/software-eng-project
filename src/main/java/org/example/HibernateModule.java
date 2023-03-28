package org.example;

import com.google.inject.AbstractModule;
import org.example.model.Club;
import org.example.model.User;
import org.example.model.Event;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateModule extends AbstractModule {

    @Override
    protected void configure() {
        Configuration configuration = new Configuration().configure();
        configuration.addAnnotatedClass(User.class);
        configuration.addAnnotatedClass(Club.class);
        configuration.addAnnotatedClass(Event.class);

        StandardServiceRegistryBuilder builder =
                new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());

        bind(SessionFactory.class).toInstance(sessionFactory);
    }
}