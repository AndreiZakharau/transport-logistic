package logistic.transportlogistic.mapper;

import static com.logistic.transportlogistic.mapper.CarMapper.CAR_MAPPER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.logistic.transportlogistic.api.model.CreateCar;
import com.logistic.transportlogistic.api.model.ReadCar;
import com.logistic.transportlogistic.domain.Car;
import java.time.LocalDate;
import logistic.transportlogistic.testobject.TestModel;
import org.junit.jupiter.api.Test;

class CarMapperTest {

  @Test
  void carFromReadCar() {

    ReadCar readCar = TestModel.getReadCar();
    Car car = CAR_MAPPER.carFromReadCar(readCar);

    assertNotNull(car);
    assertEquals(car.getModel(), readCar.getModel());
    assertEquals(car.getFabricator(), readCar.getFabricator());
    assertEquals(car.getCreateDate(), LocalDate.parse(readCar.getCreateDate()));

  }

  @Test
  void readCarFromCar() {
  }

  @Test
  void carFromCreateCar() {

    CreateCar createCar = TestModel.getCreateCar();
    Car car = CAR_MAPPER.carFromCreateCar(createCar);

    assertNotNull(car);
    assertEquals(car.getModel(), createCar.getModel());
    assertEquals(car.getFabricator(), createCar.getFabricator());
    assertEquals(car.getCreateDate(), LocalDate.parse(createCar.getCreateDate()));
  }

  @Test
  void createCarFromCar() {

  }
}