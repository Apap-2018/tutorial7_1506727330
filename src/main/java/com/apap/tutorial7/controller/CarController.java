package com.apap.tutorial7.controller;

import com.apap.tutorial7.model.CarModel;
import com.apap.tutorial7.model.DealerModel;
import com.apap.tutorial7.service.CarService;
import com.apap.tutorial7.service.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/car")
public class CarController {

    @Autowired
    private DealerService dealerService;

    @Autowired
    private CarService carService;

    @Autowired
    RestTemplate restTemplate;

    @Bean
    public RestTemplate rest(){
        return new RestTemplate();
    }

    @PostMapping(value = "/add")
    private CarModel addCarSubmit(@RequestBody CarModel car){
        return carService.addCar(car);
    }

    @GetMapping(value = "/{carId}")
    private CarModel viewCar(@PathVariable ("carId") long carId,Model model){
        return carService.getCarDetailById(carId).get();
    }

    @DeleteMapping(value = "/{carId}")
    private String deleteCar(@PathVariable("carId") long carId, Model model){
        CarModel car = carService.getCarDetailById(carId).get();
        carService.deleteCar(car);
        return "Success";
    }

    @PutMapping(value = "/{id}")
    private String updateCarubmit(
            @PathVariable (value = "id") long carId,
            @RequestParam(value = "brand",required = false) String brand,
            @RequestParam(value = "type",required = false) String type,
            @RequestParam(value = "price",required = false) String price,
            @RequestParam(value = "amount",required = false) String amount,
            @RequestParam(value = "dealerId",required = false) String dealerId){
        CarModel car= (CarModel) carService.getCarDetailById(carId).get();
        if (car.equals(null)){
            return "Coulnd't find your car";
        }
        if (brand != null){
            car.setBrand(brand);
        }
        if (type != null){
            car.setType(type);
        }
        if (price != null){
            car.setPrice(Long.parseLong(price));
        }
        if (amount != null){
            car.setAmount(Integer.parseInt(amount));
        }
        if (dealerId !=null){
            car.setDealer(dealerService.getDealerDetailById(Long.parseLong(dealerId)).get());
        }
        carService.addCar(car);
        return "update success";
    }

    @GetMapping()
    private List<CarModel> viewAllCar(Model model){
        return carService.viewAllCar();
    }
}
