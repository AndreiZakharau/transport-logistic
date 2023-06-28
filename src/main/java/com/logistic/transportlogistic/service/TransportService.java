package com.logistic.transportlogistic.service;

import com.logistic.transportlogistic.model.CreateTransport;
import com.logistic.transportlogistic.model.ReadTransport;

public interface TransportService extends DomainService<CreateTransport, ReadTransport> {

  ReadTransport setDriverToTransport(long tId, long dId);

}

