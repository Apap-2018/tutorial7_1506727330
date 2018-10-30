package com.apap.tutorial7.service;

import com.apap.tutorial7.model.DealerModel;

import java.util.List;
import java.util.Optional;

public interface DealerService {
    Optional<DealerModel> getDealerDetailById(Long id);
    DealerModel addDealer(DealerModel dealer);
    void deleteDealer(DealerModel dealer);

    void updateDealer(long id, DealerModel dealer);

    List<DealerModel> viewAllDealer();
}
