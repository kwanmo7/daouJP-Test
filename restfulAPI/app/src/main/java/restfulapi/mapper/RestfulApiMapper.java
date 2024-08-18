package restfulapi.mapper;

import java.time.LocalDateTime;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import restfulapi.dto.RecordDTO;

@Mapper
public interface RestfulApiMapper {
  
  // 등록
  int insertRecord(@Param("record") RecordDTO recordDTO);
  
  // 수정
  int updateRecord(@Param("record")RecordDTO recordDTO);

  // 시간대별 전체항목 조회
  List<RecordDTO> selectAllRecord(@Param("start")LocalDateTime start, @Param("end")LocalDateTime end);

  // 시간대별 가입자수 조회
  List<RecordDTO> selectSubscribers(@Param("start")LocalDateTime start, @Param("end")LocalDateTime end);

  // 시간대별 탈퇴자수 조회
  List<RecordDTO> selectDropouts(@Param("start")LocalDateTime start, @Param("end")LocalDateTime end);

  // 시간대별 결제금액 조회
  List<RecordDTO> selectPaymentAmount(@Param("start")LocalDateTime start, @Param("end")LocalDateTime end);

  // 시간대별 사용금액 조회
  List<RecordDTO> selectAmountUsed(@Param("start")LocalDateTime start, @Param("end")LocalDateTime end);

  // 시간대별 매출금액 조회
  List<RecordDTO> selectSalesAmount(@Param("start")LocalDateTime start, @Param("end")LocalDateTime end);
  
  // 삭제
  void deleteRecord(@Param("id") Long id);
}
