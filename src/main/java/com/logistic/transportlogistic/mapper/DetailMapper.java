package com.logistic.transportlogistic.mapper;

import com.logistic.transportlogistic.api.model.CreateDetail;
import com.logistic.transportlogistic.api.model.ReadDetail;
import com.logistic.transportlogistic.domain.Detail;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface DetailMapper {

  DetailMapper DETAIL_MAPPER = Mappers.getMapper(DetailMapper.class);

  Detail detailFromCreateDetail(CreateDetail createDetail);

  CreateDetail createDetailFromDetail(Detail detail);

  Detail detailFromReadDetail(ReadDetail readDetail);

  ReadDetail readDetailFromDetail(Detail detail);
}
