package ru.job4j.cars.store;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.cars.models.*;

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
                    " join fetch p.photoCar ph where ph.name != :Text ")
                    .setParameter("Text", "null.jpg")
                    .list();
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
    public List<Post> findCarMark(int id) {
        return this.tx(session -> {
            List<Post> result = session.createQuery("select distinct p from  Post p"
                    + " join fetch p.car c"
                    + " join fetch c.markAuto m"
                    + " where m.id =:ID")
                    .setParameter("ID", id)
                    .list();
            return result;
        });
    }

    @Override
    public List<Post> findAllPost() {
        return this.tx(
                session -> session.createQuery("select distinct p from Post p "
                                + "join fetch p.car c "
                                + "join fetch c.markAuto m "
                                + "join fetch c.user "
                                + "join fetch c.carBody "
                        + "join fetch  p.photoCar").list()
        );
    }

    @Override
    public Post savePost(Post post) {
        return this.tx(session -> {
            session.save(post);
            return post;
        });
    }

    @Override
    public Post deletePost(Integer id) {
        return this.tx(session -> {
            Post post = session.get(Post.class, id);
            session.delete(post);
            return post;
        });
    }

    @Override
    public Post findPostById(Integer id) {
        return this.tx(session -> {
            Post post = session.get(Post.class, id);
            return post;
        });
    }

    @Override
    public Post UpdatePost(Integer id, Post post) {
        return null;
    }

    @Override
    public User saveUser(User user) {
        return this.tx(session -> {
            session.save(user);
            return user;
        });
    }

    @Override
    public User findUserByEmail(String email) {
        return this.tx(session -> {
            try {
                Query query = session.createQuery("FROM User as u WHERE u.email = :Email");
                query.setParameter("Email", email);
                User user = (User) query.getSingleResult();
                return user;
            } catch ( Exception exception) {
                return null;
            }

        });
    }

    @Override
    public User findUserById(int id) {
        return this.tx(session -> {
            User user = session.get(User.class, id);
            return user;
        });
    }

    @Override
    public List<MarkAuto> findAllMarkAuto() {
        return this.tx(session -> session.createQuery("from MarkAuto ").list()
        );
    }

    @Override
    public List<CarBody> findAllCarBody() {
        return this.tx(session -> session.createQuery("from CarBody").list()
        );
    }

    @Override
    public PhotoCar savePhotoCar(PhotoCar photo) {
        return this.tx(session -> {
            session.save(photo);
            return photo;
        });
    }

    @Override
    public Car saveCar(Car car) {
        return this.tx(session -> {
            session.save(car);
            return car;
        });
    }

    @Override
    public MarkAuto findMarkAutoById(int id) {
        return this.tx(session -> {
            MarkAuto markAuto = session.get(MarkAuto.class, id);
            return markAuto;
        });
    }

    @Override
    public MarkAuto findMarkAutoByName(String name) {
        return this.tx(session -> {
            Query query = session.createQuery("FROM MarkAuto as m WHERE m.name = :Name");
            query.setParameter("Name", name);
            MarkAuto markAuto = (MarkAuto) query.getSingleResult();
            return markAuto;
        });
    }

    @Override
    public CarBody findCarBodyByName(String name) {
        return this.tx(session -> {
            Query query = session.createQuery("FROM CarBody as c WHERE c.name = :Name");
            query.setParameter("Name", name);
            CarBody carBody = (CarBody) query.getSingleResult();
            return carBody;
        });
    }

    @Override
    public CarBody findCarBodyById(int id) {
        return this.tx(session -> {
            CarBody carBody = session.get(CarBody.class, id);
            return carBody;
        });
    }

    @Override
    public PhotoCar findPhotoCarByName(String name) {
        return this.tx(session -> {
            Query query = session.createQuery("FROM PhotoCar as p WHERE p.name = :Name");
            query.setParameter("Name", name);
            PhotoCar photoCar = (PhotoCar) query.getSingleResult();
            return photoCar;
        });
    }

    @Override
    public PhotoCar findPhotoCarById(int id) {
        return this.tx(session -> {
            PhotoCar photoCar = session.get(PhotoCar.class, id);
            return photoCar;
        });
    }


}
