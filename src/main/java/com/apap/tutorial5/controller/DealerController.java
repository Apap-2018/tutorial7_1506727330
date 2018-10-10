package com.apap.tutorial5.controller;

import com.apap.tutorial5.model.CarModel;
import com.apap.tutorial5.model.DealerModel;
import com.apap.tutorial5.service.CarService;
import com.apap.tutorial5.service.DealerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class DealerController {
    @Autowired
    private DealerService dealerService;

    @Autowired
    private CarService carService;

    @RequestMapping("/")
    private String home(Model model){
        model.addAttribute("pageTitle","Home"); return "home";
    }

    @RequestMapping(value = "/dealer/add", method = RequestMethod.GET)
    private String add(Model model){
        model.addAttribute("dealer", new DealerModel());
        model.addAttribute("pageTitle","Add Dealer");
        return "addDealer";
    }

    @RequestMapping(value = "/dealer/add", method = RequestMethod.POST)
    private String addDealerSubmit(@ModelAttribute DealerModel dealer, Model model){
        dealerService.addDealer(dealer);

        model.addAttribute("pageTitle","Add Dealer");

        return "add";
    }

    @RequestMapping(value = "/dealer/view", method = RequestMethod.GET)
    private String view(@RequestParam(value = "dealerId") Long dealerId, Model model){
        DealerModel dealer = dealerService.getDealerDetailById(dealerId).get();
        dealer.getListCar().sort((carModel1, carModel2) -> {return (int)(carModel1.getPrice()-carModel2.getPrice());});
        model.addAttribute("dealer", dealer);
        model.addAttribute("listCar",dealer.getListCar());

        model.addAttribute("pageTitle","View Dealer");

        return "view-dealer";
    }

    @RequestMapping("/dealer/delete/{id}")
    public String delete(@PathVariable(value = "id") Long id, Model model){
        DealerModel dealer = dealerService.getDealerDetailById(id).get();
        dealerService.deleteDealer(dealer);

        model.addAttribute("pageTitle","Delete Dealer");

        return "delete-dealer";
    }

    @RequestMapping(value = "/dealer/{id}/update", method = RequestMethod.GET)
    public String update(@PathVariable("id") Long id, Model model){
        DealerModel dealer = dealerService.getDealerDetailById(id).get();
        model.addAttribute("dealer",dealer);

        model.addAttribute("pageTitle","Update Dealer");

        return "update-dealer";
    }

    @RequestMapping(value = "/dealer/{id}/update", method = RequestMethod.POST)
    public String updateDealerSubmit(@ModelAttribute DealerModel dealer, Model model){
        System.out.println(dealer.getId());
        System.out.println(dealer.getAlamat());
        System.out.println(dealer.getNoTelp());
        Optional<DealerModel> optional = dealerService.getDealerDetailById(dealer.getId());
        DealerModel dealerToUpdate = optional.get();
        dealerToUpdate.setAlamat(dealer.getAlamat());
        dealerToUpdate.setNoTelp(dealer.getNoTelp());
        dealerService.addDealer(dealerToUpdate);

        model.addAttribute("pageTitle","Update");

        return "update";
    }

    @RequestMapping(value = "/dealers", method = RequestMethod.GET)
    public String update(Model model){
        List<DealerModel> dealer = dealerService.getAllDealer();
        model.addAttribute("listDealer",dealer);
        model.addAttribute("pageTitle","View All Dealer");
        return "view-all";
    }
}
