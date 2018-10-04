package com.apap.tutorial4.service;

import com.apap.tutorial4.model.CarModel;

import java.util.Optional;

public interface CarService {
    Optional<CarModel> getCarDetailById(Long id);
    void addCar(CarModel car);
    void deleteCar(Long id);
}
