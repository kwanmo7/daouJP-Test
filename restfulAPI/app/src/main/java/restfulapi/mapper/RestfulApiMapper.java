package restfulapi.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import restfulapi.model.Record;

@Mapper
public interface RestfulApiMapper {
  void insertRecord(Record record);

  void updateRecord(Record record);

  List<Record> selectAllRecord();

  List<Record> selectSubscribers();

  List<Record> selectSalesAmount();

  List<Record> selectPaymentAmount();

  void deleteRecord(Long id);
}
