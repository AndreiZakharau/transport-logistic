package com.logistic.transportlogistic.mapper;

import com.logistic.transportlogistic.domain.Detail;
import com.logistic.transportlogistic.model.CreateDetail;
import com.logistic.transportlogistic.model.ReadDetail;
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
