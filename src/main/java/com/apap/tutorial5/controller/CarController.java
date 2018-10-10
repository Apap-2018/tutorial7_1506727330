package com.apap.tutorial5.controller;

import com.apap.tutorial5.model.CarModel;
import com.apap.tutorial5.model.DealerModel;
import com.apap.tutorial5.service.CarService;
import com.apap.tutorial5.service.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class CarController {
    @Autowired
    private CarService carService;

    @Autowired
    private DealerService dealerService;

    @RequestMapping(value = "/car/add/{dealerId}", method = RequestMethod.POST, params = "removeRow")
    private String removeRow(@ModelAttribute DealerModel dealer, @RequestParam(value = "removeRow") String rowId, Model model){
        dealer.getListCar().remove(Integer.parseInt(rowId));
        model.addAttribute("pageTitle", "Add Car");
        model.addAttribute("dealer",dealer);
        return "addCar";
    }

    @RequestMapping(value = "/car/add/{dealerId}",method = RequestMethod.POST, params = "addRow")
    private String addRow(@PathVariable(value = "dealerId") Long dealerId, @ModelAttribute DealerModel dealer, Model model){
        CarModel car = new CarModel();
        dealer.getListCar().add(car);
        model.addAttribute("pageTitle", "Add Car");
        model.addAttribute("dealer",dealer);
        return "addCar";
    }

    @RequestMapping(value = "/car/add/{dealerId}",method = RequestMethod.GET)
    private String add(@PathVariable(value = "dealerId") Long dealerId, Model model){
        DealerModel dealer = dealerService.getDealerDetailById(dealerId).get();
        dealer.setListCar(new ArrayList<>());
        dealer.getListCar().add(new CarModel());
        model.addAttribute("pageTitle", "Add Car");
        model.addAttribute("dealer",dealer);
        return "addCar";
    }

    @RequestMapping(value = "/car/add/{dealerId}", method = RequestMethod.POST, params = "save")
    private String addCarSubmit(@ModelAttribute DealerModel dealer, Model model){
        DealerModel dbDealer = dealerService.getDealerDetailById(dealer.getId()).get();
        for (CarModel car : dealer.getListCar()) {
            car.setDealer(dealer);
            carService.addCar(car);
        }
        model.addAttribute("pageTitle", "Add Car");
        return "add";
    }

    @RequestMapping(value = "/car/delete", method = RequestMethod.POST)
    public String delete(@ModelAttribute DealerModel dealer, Model model){

        for (CarModel car: dealer.getListCar()){
            carService.deleteCar(car.getId());
        }
        model.addAttribute("pageTitle", "Delete Car");
        return "delete-car";
    }

    @RequestMapping(value = "/car/{id}/update", method = RequestMethod.GET)
    public String update(@PathVariable("id") Long id, Model model){
        CarModel car = carService.getCarDetailById(id).get();
        model.addAttribute("car",car);
        model.addAttribute("pageTitle", "Update Car");
        return "update-car";
    }

    @RequestMapping(value = "/car/{id}/update", method = RequestMethod.POST)
    public String updateDealerSubmit(@PathVariable("id") Long id,@ModelAttribute CarModel car, Model model){
        CarModel carToUpdate = carService.getCarDetailById(id).get();
        carToUpdate.setAmount(car.getAmount());
        carToUpdate.setBrand(car.getBrand());
        carToUpdate.setPrice(car.getPrice());
        carToUpdate.setType(car.getType());
        System.out.println(carToUpdate.getDealer());
        carService.addCar(carToUpdate);
        model.addAttribute("pageTitle", "Update Car");
        return "update";
    }
}
