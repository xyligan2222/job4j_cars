package ru.job4j.cars.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.cars.models.Car;
import ru.job4j.cars.models.Post;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.function.Function;

public class AdRepostiroty implements  AutoCloseable, StoreCar{
    private static final Logger LOG = LoggerFactory.getLogger(AdRepostiroty.class.getName());
    @Override
    public void close() throws Exception {
    }

    private static final class Lazy {
        private static final AdRepostiroty INST = new AdRepostiroty();
    }

    public static AdRepostiroty instOf() {
        return Lazy.INST;
    }

    private final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure().build();
    private final SessionFactory sf = new MetadataSources(registry)
            .buildMetadata().buildSessionFactory();

    /*
   wrapper design pattern
   */
    private <T> T tx(final Function<Session, T> command) {
        final Session session = sf.openSession();
        final Transaction tx = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            tx.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Post> findCarWithPhoto() {
        return this.tx(session -> {
            List<Post> result = session.createQuery("select p from Post p" +
                    " join fetch p.photoCar ph" +
                    " join fetch p.car c where p.photoCar != null ").list();
            return result;
        });
    }

    @Override
    public List<Post> findCarLastDay() {
        return this.tx(session -> {
            LocalDateTime localDate = LocalDateTime.now();
            String dateNow = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            String dateBefore = localDate.minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));


            List<Post> result = session.createQuery("select distinct p from Post p"
                    + " join fetch p.photoCar ph join fetch p.car c"
                    + " join fetch c.user where p.created <= :dateNow and p.created >= :dateBefore")
                    .setParameter("dateNow", Timestamp.valueOf(dateNow))
                    .setParameter("dateBefore", Timestamp.valueOf(dateBefore))
                    .list();
            return result;
        });
    }

    @Override
    public List<Post> findCarMark(String markAuto) {
        return this.tx(session -> {
            List<Post> result = session.createQuery("select distinct p from  Post p"
                    + " join fetch p.car c"
                    + " join fetch c.markAuto m"
                    + " where m.name =:mark")
                    .setParameter("mark", markAuto)
                    .list();
            return result;
        });
    }



}
