package org.example;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.example.business.CemsService;
import org.example.model.User;

public class Main {
    public static void main(String[] args) {
        Injector injector = Guice.createInjector(new HibernateModule());
        CemsService service = new CemsService(injector);
        User user = service.getUser("user20");
        for(int c=0; c<user.getEvents().size(); c++) {
            System.out.println(user.getEvents().get(c));
        }
    }


}