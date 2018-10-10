package com.apap.tutorial5.service;

import com.apap.tutorial5.model.CarModel;

import java.util.Optional;

public interface CarService {
    Optional<CarModel> getCarDetailById(Long id);
    void addCar(CarModel car);
    void deleteCar(Long id);
}
