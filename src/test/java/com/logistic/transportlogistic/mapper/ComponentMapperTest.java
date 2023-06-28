package com.logistic.transportlogistic.mapper;


import static com.logistic.transportlogistic.mapper.ComponentMapper.COMPONENT_MAPPER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.logistic.transportlogistic.domain.Component;
import com.logistic.transportlogistic.model.CreateComponent;
import com.logistic.transportlogistic.model.ReadComponent;
import com.logistic.transportlogistic.testobject.TestModel;
import org.junit.jupiter.api.Test;

class ComponentMapperTest {

  @Test
  void componentFromCreateComponent() {

    CreateComponent createComponent = TestModel.getCreateComponent();

    Component component = COMPONENT_MAPPER.componentFromCreateComponent(createComponent);

    assertNotNull(component);
    assertEquals(component.getType(), createComponent.getType());
    assertEquals(component.getDetails().size(), 0);
  }

  @Test
  void createComponentFromComponent() {

    Component component = TestModel.getComponent();

    CreateComponent createComponent = COMPONENT_MAPPER.createComponentFromComponent(component);

    assertNotNull(createComponent);
    assertEquals(createComponent.getType(), component.getType());
  }

  @Test
  void readComponentFromComponent() {

    Component component = TestModel.getComponent();

    ReadComponent readComponent = COMPONENT_MAPPER.readComponentFromComponent(component);

    assertNotNull(readComponent);
    assertEquals(readComponent.getType(), component.getType());
    assertEquals(readComponent.getDetails(), component.getDetails());
    assertEquals(readComponent.getId(), component.getId());

  }

  @Test
  void componentFromReadComponent() {

    ReadComponent readComponent = TestModel.getReadComponent();

    Component component = COMPONENT_MAPPER.componentFromReadComponent(readComponent);

    assertNotNull(component);
    assertEquals(component.getType(), readComponent.getType());
    assertEquals(component.getDetails(), readComponent.getDetails());
    assertEquals(component.getId(), readComponent.getId());
  }
}