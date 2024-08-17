package restfulapi.controller;

import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import restfulapi.dto.RecordDTO;
import restfulapi.service.RecordService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RecordController {
  private final RecordService recordService;

  // 시간대별 전체항목 조회
  @GetMapping("list")
  public List<RecordDTO> getAllRecord(@RequestParam("start")
                                      @DateTimeFormat(iso = ISO.DATE_TIME)
                                      LocalDateTime start,
                                      @RequestParam("end")
                                      @DateTimeFormat(iso = ISO.DATE_TIME)
                                      LocalDateTime end){
    return recordService.selectAllRecord(start, end);
  }

  // 시간대별 가입자수 조회
  @GetMapping("subscriber")
  public List<RecordDTO> getSubscriber(@RequestParam("start")
                                       @DateTimeFormat(iso = ISO.DATE_TIME)
                                       LocalDateTime start,
                                       @RequestParam("end")
                                       @DateTimeFormat(iso = ISO.DATE_TIME)
                                       LocalDateTime end){
    return recordService.selectSubscribers(start,end);
  }

  // 시간대별 탈퇴자수 조회
  @GetMapping("dropouts")
  public List<RecordDTO> getDropouts(@RequestParam("start")
                                     @DateTimeFormat(iso = ISO.DATE_TIME)
                                     LocalDateTime start,
                                     @RequestParam("end")
                                     @DateTimeFormat(iso = ISO.DATE_TIME)
                                     LocalDateTime end){
    return recordService.selectDropouts(start, end);
  }
  // 시간대별 결제금액 조회
  @GetMapping("paymentAmount")
  public List<RecordDTO> getPaymentAmount(@RequestParam("start")
                                          @DateTimeFormat(iso = ISO.DATE_TIME)
                                          LocalDateTime start,
                                          @RequestParam("end")
                                          @DateTimeFormat(iso = ISO.DATE_TIME)
                                          LocalDateTime end){
    return recordService.selectPaymentAmount(start, end);
  }
  // 시간대별 사용금액 조회
  @GetMapping("amountUsed")
  public List<RecordDTO> getAmountUsed(@RequestParam("start")
                                       @DateTimeFormat(iso = ISO.DATE_TIME)
                                       LocalDateTime start,
                                       @RequestParam("end")
                                       @DateTimeFormat(iso = ISO.DATE_TIME)
                                       LocalDateTime end){
    return recordService.selectAmountUsed(start, end);
  }
  // 시간대별 매출금액 조회
  @GetMapping("salesAmount")
  public List<RecordDTO> getSalesAmount(@RequestParam("start")
                                        @DateTimeFormat(iso = ISO.DATE_TIME)
                                        LocalDateTime start,
                                        @RequestParam("end")
                                        @DateTimeFormat(iso = ISO.DATE_TIME)
                                        LocalDateTime end){
    return recordService.selectSalesAmount(start, end);
  }

  // 수정
  @PutMapping
  public ResponseEntity<RecordDTO> updateRecord(@RequestBody RecordDTO recordDTO){
    try{
      RecordDTO updateRecord = recordService.updateRecord(recordDTO);
      return ResponseEntity.ok(updateRecord);
    } catch (Exception e){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
  }

  // 등록
  @PostMapping
  public ResponseEntity<RecordDTO> insertRecord(@RequestBody RecordDTO recordDTO){
    RecordDTO insertRecord = recordService.insertRecord(recordDTO);
    return ResponseEntity.status(HttpStatus.CREATED).body(insertRecord);
  }

  // 삭제
  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteRecord(@PathVariable Long id){
    try{
      recordService.deleteRecord(id);
      return ResponseEntity.noContent().build();
    } catch (Exception e){
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }
}
