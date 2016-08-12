package ru.andrunov;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Admin on 17.07.2016.
 */
public class UserHolder {
    private final SessionFactory factory = new Configuration().configure().buildSessionFactory();

    public int countRecords(Filters filters){
        int result = 0;
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("SELECT count(*) FROM User" + createQueryText(filters));
            query = setQueryParameters(query, filters);
            result = Integer.parseInt(query.list().get(0).toString());
            session.getTransaction().commit();
        }
        return result;
    }

    public List<User> values(int firstRecord, int lastRecord, Filters filters){
        List<User> result = new ArrayList<>();
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("FROM User" + createQueryText(filters));
            query = setQueryParameters(query, filters);
            query.setFirstResult(firstRecord);
            query.setMaxResults(lastRecord);
            result.addAll(query.list());
            session.getTransaction().commit();
        }
        return result;
    }

    public User create(final User user){
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        }
        return user;
    }

    public void update (final User user) {
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            session.update(user);
            session.getTransaction().commit();
        }
    }

    public void delete (int id){
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            User user = (User)session.get(User.class,id);
            if(user==null){
                throw  new IllegalStateException(String.format("User %s does not exist",id));
            }
            session.delete(user);
            session.getTransaction().commit();
        }
    }

    public User findById(int id){
        try(Session session = factory.openSession()) {
            session.beginTransaction();
            User user = session.get(User.class,id);
            if (user == null){
                throw  new IllegalStateException(String.format("User %s does not exist",id));
            }
            session.getTransaction().commit();
            return user;
        }
    }

    private String createQueryText(Filters filters){
        String result = "";
        if (filters.getNameFilter() != null){
            result = result + " User WHERE substring(User.name,1,:lenght) = :substring";
        }
        return result;
    }

    private Query setQueryParameters(Query query, Filters filters){
        if (filters.getNameFilter() != null){
            query.setParameter("substring",filters.getNameFilter());
            query.setParameter("lenght",filters.getNameFilter().length());
        }
        return query;
    }

}
