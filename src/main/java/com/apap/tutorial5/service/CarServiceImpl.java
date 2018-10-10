package com.apap.tutorial5.service;

import com.apap.tutorial5.Repository.CarDb;
import com.apap.tutorial5.model.CarModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    public void addCar(CarModel car){
        carDb.save(car);
    }

    @Override
    public void deleteCar(Long id){ carDb.deleteById(id);}

}
