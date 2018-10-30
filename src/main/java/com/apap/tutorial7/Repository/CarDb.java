package com.apap.tutorial7.Repository;

import com.apap.tutorial7.model.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarDb extends JpaRepository<CarModel,Long> {
    CarModel findByType(String type);
}
