package com.logistic.transportlogistic.api.pagination;

import java.util.List;

public record SortParamsContext(List<String> sortColumns, List<String> orderTypes) {

}
