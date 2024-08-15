package scheduler.mapper;

import org.apache.ibatis.annotations.Mapper;
import scheduler.model.Record;

@Mapper
public interface SchedulerMapper {
  void insertRecord(Record record);
}
