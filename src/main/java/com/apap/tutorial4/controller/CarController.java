package com.apap.tutorial4.controller;

import com.apap.tutorial4.model.CarModel;
import com.apap.tutorial4.model.DealerModel;
import com.apap.tutorial4.service.CarService;
import com.apap.tutorial4.service.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Optional;

@Controller
public class CarController {
    @Autowired
    private CarService carService;

    @Autowired
    private DealerService dealerService;

    @RequestMapping(value = "/car/add/{dealerId}",method = RequestMethod.GET)
    private String add(@PathVariable(value = "dealerId") Long dealerId, Model model){
        CarModel car = new CarModel();
        DealerModel dealer = dealerService.getDealerDetailById(dealerId).get();
        car.setDealer(dealer);

        model.addAttribute("car",car);
        return "addCar";
    }

    @RequestMapping(value = "/car/add", method = RequestMethod.POST)
    private String addCarSubmit(@ModelAttribute CarModel car){
        carService.addCar(car);
        return "add";
    }

    @RequestMapping("/car/delete/{id}")
    public String delete(@PathVariable(value = "id") Long id, Model model){
        carService.deleteCar(id);
        return "delete-car";
    }

    @RequestMapping(value = "/car/{id}/update", method = RequestMethod.GET)
    public String update(@PathVariable("id") Long id, Model model){
        CarModel car = carService.getCarDetailById(id).get();
        model.addAttribute("car",car);
        return "update-car";
    }

    @RequestMapping(value = "/car/{id}/update", method = RequestMethod.POST)
    public String updateDealerSubmit(@PathVariable("id") Long id,@ModelAttribute CarModel car){
        CarModel carToUpdate = carService.getCarDetailById(id).get();
        carToUpdate.setAmount(car.getAmount());
        carToUpdate.setBrand(car.getBrand());
        carToUpdate.setPrice(car.getPrice());
        carToUpdate.setType(car.getType());
        System.out.println(carToUpdate.getDealer());
        carService.addCar(carToUpdate);
        return "update";
    }
}
