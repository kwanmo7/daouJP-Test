package restfulapi.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import restfulapi.dto.RecordDTO;
import restfulapi.mapper.RestfulApiMapper;
import restfulapi.service.RecordService;

@Service
@RequiredArgsConstructor
public class RecordServiceImpl implements RecordService {

  private final RestfulApiMapper restfulApiMapper;

  @Override
  public RecordDTO insertRecord(RecordDTO recordDTO) {
//    recordDTO.setFormattedTime();
    return restfulApiMapper.insertRecord(recordDTO);
  }

  @Override
  public RecordDTO updateRecord(RecordDTO recordDTO) {
    return restfulApiMapper.updateRecord(recordDTO);
  }

  @Override
  public List<RecordDTO> selectAllRecord(LocalDateTime start, LocalDateTime end) {
    List<RecordDTO> list = restfulApiMapper.selectAllRecord(start, end);
    return list.stream().map(record -> {
      record.setFormattedTime();
      return record;
    }).collect(Collectors.toList());
  }

  @Override
  public List<RecordDTO> selectSubscribers(LocalDateTime start, LocalDateTime end) {
    List<RecordDTO> list = restfulApiMapper.selectSubscribers(start, end);
    return list.stream().map(record -> {
      record.setFormattedTime();
      return record;
    }).collect(Collectors.toList());
  }

  @Override
  public List<RecordDTO> selectDropouts(LocalDateTime start, LocalDateTime end) {
    List<RecordDTO> list = restfulApiMapper.selectDropouts(start, end);
    return list.stream().map(record -> {
      record.setFormattedTime();
      return record;
    }).collect(Collectors.toList());
  }

  @Override
  public List<RecordDTO> selectPaymentAmount(LocalDateTime start, LocalDateTime end) {
    List<RecordDTO> list = restfulApiMapper.selectPaymentAmount(start, end);
    return list.stream().map(record -> {
      record.setFormattedTime();
      return record;
    }).collect(Collectors.toList());
  }

  @Override
  public List<RecordDTO> selectAmountUsed(LocalDateTime start, LocalDateTime end) {
    List<RecordDTO> list = restfulApiMapper.selectAmountUsed(start, end);
    return list.stream().map(record -> {
      record.setFormattedTime();
      return record;
    }).collect(Collectors.toList());
  }

  @Override
  public List<RecordDTO> selectSalesAmount(LocalDateTime start, LocalDateTime end) {
    List<RecordDTO> list = restfulApiMapper.selectSalesAmount(start, end);
    return list.stream().map(record -> {
      record.setFormattedTime();
      return record;
    }).collect(Collectors.toList());
  }

  @Override
  public void deleteRecord(Long id) {
    restfulApiMapper.deleteRecord(id);
  }
}
