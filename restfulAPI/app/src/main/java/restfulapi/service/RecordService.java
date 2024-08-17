package restfulapi.service;

import java.time.LocalDateTime;
import java.util.List;
import restfulapi.dto.RecordDTO;

public interface RecordService {
  RecordDTO insertRecord(RecordDTO recordDTO);

  RecordDTO updateRecord(RecordDTO recordDTO);

  List<RecordDTO> selectAllRecord(LocalDateTime start, LocalDateTime end);

  List<RecordDTO> selectSubscribers(LocalDateTime start, LocalDateTime end);

  List<RecordDTO> selectDropouts(LocalDateTime start, LocalDateTime end);

  List<RecordDTO> selectPaymentAmount(LocalDateTime start, LocalDateTime end);

  List<RecordDTO> selectAmountUsed(LocalDateTime start, LocalDateTime end);

  List<RecordDTO> selectSalesAmount(LocalDateTime start, LocalDateTime end);


  void deleteRecord(Long id);
}
