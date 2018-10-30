package com.apap.tutorial7.service;


import com.apap.tutorial7.Repository.DealerDb;
import com.apap.tutorial7.model.DealerModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DealerServiceImpl implements DealerService{
    @Autowired
    private DealerDb dealerDb;

    @Override
    public Optional<DealerModel> getDealerDetailById(Long id){
        return dealerDb.findById(id);
    }

    @Override
    public DealerModel addDealer(DealerModel dealer){
        return dealerDb.save(dealer);
    }

    @Override
    public void deleteDealer(DealerModel dealer){ dealerDb.delete(dealer);}

    @Override
    public List<DealerModel> viewAllDealer(){
        return dealerDb.findAll();
    }

    @Override
    public void updateDealer(long id, DealerModel updatedDealer){
        DealerModel dealer = dealerDb.getOne(id);
        dealer.setNoTelp(updatedDealer.getNoTelp());
        dealer.setAlamat(updatedDealer.getAlamat());
        dealerDb.save(dealer);
    }
}
