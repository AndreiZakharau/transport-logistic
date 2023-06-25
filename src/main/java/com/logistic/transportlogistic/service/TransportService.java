package com.logistic.transportlogistic.service;

import com.logistic.transportlogistic.model.CreateTransport;
import com.logistic.transportlogistic.model.ReadTransport;
import java.util.List;
import org.springframework.data.domain.Page;

public interface TransportService extends DomainService<CreateTransport, ReadTransport> {

  ReadTransport setDriverToTransport(long tId, long dId);

}

