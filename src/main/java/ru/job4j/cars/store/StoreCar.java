package ru.job4j.cars.store;

import ru.job4j.cars.models.*;

import java.util.List;

public interface StoreCar {
    public List<Post> findCarWithPhoto();
    public List<Post> findCarLastDay();
    public List<Post> findCarMark(int id);
    public List<Post> findAllPost();
    public Post savePost(Post post);
    public Post deletePost(Integer id);
    public Post findPostById(Integer id);
    public Post UpdatePost(Integer id, Post post);
    public User saveUser(User user);
    public User findUserByEmail (String email);
    public User findUserById (int id);
    public List<MarkAuto> findAllMarkAuto();
    public List<CarBody> findAllCarBody();
    public PhotoCar savePhotoCar(PhotoCar photo);
    public Car saveCar(Car car);
    public  MarkAuto findMarkAutoById(int id);
    public  MarkAuto findMarkAutoByName(String name);
    public  CarBody findCarBodyByName(String name);
    public  CarBody findCarBodyById(int id);
    public  PhotoCar findPhotoCarByName(String name);
    public  PhotoCar findPhotoCarById(int id);
}
