package com.logistic.transportlogistic.model;

import java.util.List;

public record TransportModel(long id, String vin, String registryNumber, List<ReadDetail> details) {

}
