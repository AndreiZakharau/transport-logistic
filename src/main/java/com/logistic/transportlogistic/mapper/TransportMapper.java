package com.logistic.transportlogistic.mapper;

import com.logistic.transportlogistic.model.CreateTransport;
import com.logistic.transportlogistic.model.ReadTransport;
import com.logistic.transportlogistic.domain.Transport;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface TransportMapper {

  TransportMapper TRANSPORT_MAPPER = Mappers.getMapper(TransportMapper.class);

  Transport transportFromCreateTransport(CreateTransport createTransport);

  CreateTransport createTransportFromTransport(Transport transport);

  Transport transportFromReadTransport(ReadTransport readTransport);

  ReadTransport readTransportFromTransport(Transport transport);
}
