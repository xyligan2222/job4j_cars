package ru.job4j.cars.integrity;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class OrdersStoreTest {
    private BasicDataSource pool = new BasicDataSource();

    @Before
    public void setUp() throws SQLException {
        pool.setDriverClassName("org.hsqldb.jdbcDriver");
        pool.setUrl("jdbc:hsqldb:mem:cars;sql.syntax_pgs=true");
        pool.setUsername("postgres");
        pool.setPassword("sa");
        pool.setMaxTotal(2);
        StringBuilder builder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(new FileInputStream("./database/update_001.sql")))
        ) {
            br.lines().forEach(line -> builder.append(line).append(System.lineSeparator()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        pool.getConnection().prepareStatement(builder.toString()).executeUpdate();
    }

    @Test
    public void whenSaveOrderAndFindAllOneRowWithDescription() {
        OrdersStore store = new OrdersStore(pool);

        store.save(Order.of("name1", "description1"));

        List<Order> all = (List<Order>) store.findAll();

        assertThat(all.size(), is(1));
        assertThat(all.get(0).getDescription(), is("description1"));
        assertThat(all.get(0).getId(), is(1));
    }

    @Test
    public void whenSaveOrderAndFindOrderName() {
        OrdersStore store = new OrdersStore(pool);

        store.save(Order.of("name2", "description2"));
        Order all = store.findByName("name2");
        assertThat(all.getName(), is("name2"));
        assertThat(all.getId(), is(1));
    }

    @Test
    public void whenSaveOrderAndFindOrderById() {
        OrdersStore store = new OrdersStore(pool);

        store.save(Order.of("name2", "description2"));
        store.save(Order.of("name3", "description3"));
        Order all = store.findById(2);
        assertThat(all.getName(), is("name3"));
        assertThat(all.getId(), is(2));
    }

    @Test
    public void whenSaveOrderThenUpdateAndFindOrderById() {
        OrdersStore store = new OrdersStore(pool);

        store.save(Order.of("name2", "description2"));
        store.update(1, "Vasya");

        Order all = store.findById(1);
        assertThat(all.getName(), is("Vasya"));
        assertThat(all.getId(), is(1));
    }
}