package com.apap.tutorial4.controller;

import com.apap.tutorial4.model.CarModel;
import com.apap.tutorial4.model.DealerModel;
import com.apap.tutorial4.service.CarService;
import com.apap.tutorial4.service.DealerService;
import com.apap.tutorial4.service.DealerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.websocket.server.PathParam;
import java.util.List;
import java.util.Optional;

@Controller
public class DealerController {
    @Autowired
    private DealerService dealerService;

    @Autowired
    private CarService carService;

    @RequestMapping("/")
    private String home(){
        return "home";
    }

    @RequestMapping(value = "/dealer/add", method = RequestMethod.GET)
    private String add(Model model){
        model.addAttribute("dealer", new DealerModel());
        return "addDealer";
    }

    @RequestMapping(value = "/dealer/add", method = RequestMethod.POST)
    private String addDealerSubmit(@ModelAttribute DealerModel dealer){
        dealerService.addDealer(dealer);
        return "add";
    }

    @RequestMapping(value = "/dealer/view", method = RequestMethod.GET)
    private String viewDealer(@RequestParam(value = "dealerId") Long id, Model model){
        DealerModel dealer = dealerService.getDealerDetailById(id).get();
        model.addAttribute("dealer", dealer);
        dealer.getListCar().sort((carModel1, carModel2) ->
        {return (int)(carModel1.getPrice()-carModel2.getPrice());});
        model.addAttribute("listCar", dealer.getListCar());
        return "view-dealer";
    }

    @RequestMapping("/dealer/delete/{id}")
    public String delete(@PathVariable(value = "id") Long id, Model model){
        DealerModel dealer = dealerService.getDealerDetailById(id).get();
        dealerService.deleteDealer(dealer);
        return "delete-dealer";
    }

    @RequestMapping(value = "/dealer/{id}/update", method = RequestMethod.GET)
    public String update(@PathVariable("id") Long id, Model model){
        DealerModel dealer = dealerService.getDealerDetailById(id).get();
        model.addAttribute("dealer",dealer);
        return "update-dealer";
    }

    @RequestMapping(value = "/dealer/{id}/update", method = RequestMethod.POST)
    public String updateDealerSubmit(@ModelAttribute DealerModel dealer){
        System.out.println(dealer.getId());
        System.out.println(dealer.getAlamat());
        System.out.println(dealer.getNoTelp());
        Optional<DealerModel> optional = dealerService.getDealerDetailById(dealer.getId());
        DealerModel dealerToUpdate = optional.get();
        dealerToUpdate.setAlamat(dealer.getAlamat());
        dealerToUpdate.setNoTelp(dealer.getNoTelp());
        dealerService.addDealer(dealerToUpdate);
        return "update";
    }

    @RequestMapping(value = "/dealers", method = RequestMethod.GET)
    public String update(Model model){
        List<DealerModel> dealer = dealerService.getAllDealer();
        model.addAttribute("listDealer",dealer);
        return "view-all";
    }
}
