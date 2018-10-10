package com.apap.tutorial5.Repository;

import com.apap.tutorial5.model.DealerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DealerDb extends JpaRepository<DealerModel, Long> {

}
