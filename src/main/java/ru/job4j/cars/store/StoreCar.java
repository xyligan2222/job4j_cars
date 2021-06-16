package ru.job4j.cars.store;

import ru.job4j.cars.models.Car;
import ru.job4j.cars.models.Post;

import java.util.List;

public interface StoreCar {
    public List<Post> findCarWithPhoto();
    public List<Post> findCarLastDay();
    public List<Post> findCarMark(String markAuto);
}
