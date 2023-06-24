package com.logistic.transportlogistic.repositorie;

import com.logistic.transportlogistic.domain.Transport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransportRepository extends JpaRepository<Transport, Long> {

}
