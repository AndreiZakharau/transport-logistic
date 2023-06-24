package com.logistic.transportlogistic.mapper;

import com.logistic.transportlogistic.api.model.CreateComponent;
import com.logistic.transportlogistic.api.model.ReadComponent;
import com.logistic.transportlogistic.domain.Component;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ComponentMapper {

  ComponentMapper COMPONENT_MAPPER = Mappers.getMapper(ComponentMapper.class);

  Component componentFromCreateComponent(CreateComponent createComponent);

  CreateComponent createComponentFromComponent(Component component);

  ReadComponent readComponentFromComponent(Component component);

  Component componentFromReadComponent(ReadComponent readComponent);

}
