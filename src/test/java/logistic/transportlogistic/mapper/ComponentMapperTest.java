package logistic.transportlogistic.mapper;


import static com.logistic.transportlogistic.mapper.ComponentMapper.COMPONENT_MAPPER;

import com.logistic.transportlogistic.api.model.CreateComponent;
import com.logistic.transportlogistic.domain.Component;
import logistic.transportlogistic.testobject.TestModel;
import org.junit.jupiter.api.Test;

class ComponentMapperTest {

  @Test
  void componentFromCreateComponent() {

    CreateComponent createComponent = TestModel.getCreateComponent();
    Component component = COMPONENT_MAPPER.componentFromCreateComponent(createComponent);
  }

  @Test
  void createComponentFromComponent() {
  }

  @Test
  void readComponentFromComponent() {
  }

  @Test
  void componentFromReadComponent() {
  }
}