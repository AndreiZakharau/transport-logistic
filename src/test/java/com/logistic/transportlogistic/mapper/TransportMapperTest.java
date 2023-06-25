package com.logistic.transportlogistic.mapper;


import static com.logistic.transportlogistic.mapper.TransportMapper.TRANSPORT_MAPPER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.logistic.transportlogistic.model.CreateTransport;
import com.logistic.transportlogistic.model.ReadTransport;
import com.logistic.transportlogistic.domain.Transport;
import com.logistic.transportlogistic.testobject.TestModel;
import org.junit.jupiter.api.Test;

class TransportMapperTest {

  @Test
  void transportFromCreateTransport() {

    CreateTransport createTransport = TestModel.getCreateTransport();

    Transport transport = TRANSPORT_MAPPER.transportFromCreateTransport(createTransport);

    assertNotNull(transport);
    assertEquals(transport.getVin(), createTransport.getVin());
    assertEquals(transport.getRegistryNumber(), createTransport.getRegistryNumber());
    assertNull(transport.getCar());
  }

  @Test
  void createTransportFromTransport() {

    Transport transport = TestModel.getTransport();

    CreateTransport createTransport = TRANSPORT_MAPPER.createTransportFromTransport(transport);

    assertNotNull(createTransport);
    assertEquals(transport.getVin(), createTransport.getVin());
    assertEquals(transport.getRegistryNumber(), createTransport.getRegistryNumber());

  }

  @Test
  void transportFromReadTransport() {

    ReadTransport readTransport = TestModel.getReadTransport();

    Transport transport = TRANSPORT_MAPPER.transportFromReadTransport(readTransport);

    assertNotNull(transport);
    assertEquals(transport.getVin(), readTransport.getVin());
    assertEquals(transport.getRegistryNumber(), readTransport.getRegistryNumber());
    assertEquals(transport.getId(), readTransport.getId());
    assertEquals(transport.getCar(), readTransport.getCar());
    assertEquals(transport.getDetails(), readTransport.getDetails());

  }

  @Test
  void readTransportFromTransport() {

    Transport transport = TestModel.getTransport();

    ReadTransport readTransport = TRANSPORT_MAPPER.readTransportFromTransport(transport);

    assertNotNull(readTransport);
    assertEquals(transport.getVin(), readTransport.getVin());
    assertEquals(transport.getRegistryNumber(), readTransport.getRegistryNumber());
    assertEquals(transport.getId(), readTransport.getId());
    assertEquals(transport.getCar(), readTransport.getCar());
    assertEquals(transport.getDetails(), readTransport.getDetails());
  }
}