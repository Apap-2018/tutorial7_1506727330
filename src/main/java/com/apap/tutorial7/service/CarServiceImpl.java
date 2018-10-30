package com.apap.tutorial7.service;

import com.apap.tutorial7.Repository.CarDb;
import com.apap.tutorial7.model.CarModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CarServiceImpl implements CarService{
    @Autowired
    private CarDb carDb;

    @Override
    public Optional<CarModel> getCarDetailById(Long id){
        return carDb.findById(id);
    }

    @Override
    public CarModel addCar(CarModel car){
        return carDb.save(car);
    }

    @Override
    public void deleteCar(CarModel car){ carDb.delete(car);}

    @Override
    public List<CarModel> viewAllCar(){
        return  carDb.findAll();
    }
}
