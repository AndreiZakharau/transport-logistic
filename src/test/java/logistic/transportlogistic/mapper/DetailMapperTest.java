package logistic.transportlogistic.mapper;

import static com.logistic.transportlogistic.mapper.DetailMapper.DETAIL_MAPPER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.logistic.transportlogistic.api.model.CreateDetail;
import com.logistic.transportlogistic.api.model.ReadDetail;
import com.logistic.transportlogistic.domain.Detail;
import logistic.transportlogistic.testobject.TestModel;
import org.junit.jupiter.api.Test;

class DetailMapperTest {

  @Test
  void detailFromCreateDetail() {

    CreateDetail createDetail = TestModel.getCreateDetail();

    Detail detail = DETAIL_MAPPER.detailFromCreateDetail(createDetail);

    assertNotNull(detail);
    assertEquals(detail.getNumber(), createDetail.getNumber());
    assertEquals(detail.getTypeDetail(), createDetail.getTypeDetail());
    assertNull(detail.getId());
    assertNull(detail.getTransportId());
  }

  @Test
  void createDetailFromDetail() {

    Detail detail = TestModel.getDetail();

    CreateDetail createDetail =DETAIL_MAPPER.createDetailFromDetail(detail);

    assertNotNull(createDetail);
    assertEquals(createDetail.getNumber(), detail.getNumber());
    assertEquals(createDetail.getTypeDetail(), detail.getTypeDetail());
  }

  @Test
  void detailFromReadDetail() {

    ReadDetail readDetail = TestModel.getReadDetail();

    Detail detail = DETAIL_MAPPER.detailFromReadDetail(readDetail);

    assertNotNull(detail);
    assertEquals(detail.getNumber(), readDetail.getNumber());
    assertEquals(detail.getTypeDetail(), readDetail.getTypeDetail());
    assertEquals(detail.getId(), readDetail.getId());
    assertEquals(detail.getTransportId(), readDetail.getTransportId());
  }

  @Test
  void readDetailFromDetail() {

    Detail detail = TestModel.getDetail();

    ReadDetail readDetail = DETAIL_MAPPER.readDetailFromDetail(detail);

    assertNotNull(readDetail);
    assertEquals(readDetail.getNumber(), detail.getNumber());
    assertEquals(readDetail.getTypeDetail(), detail.getTypeDetail());
    assertEquals(readDetail.getId(), detail.getId());
    assertEquals(readDetail.getTransportId(), detail.getTransportId());
  }
}