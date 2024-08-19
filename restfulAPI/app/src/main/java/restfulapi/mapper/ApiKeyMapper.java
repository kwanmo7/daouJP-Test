package restfulapi.mapper;

import org.apache.ibatis.annotations.Mapper;
import restfulapi.dto.ApiKey;

@Mapper
public interface ApiKeyMapper {
  boolean checkApiKey(String apiKey);

  int insertApiKey(ApiKey apiKey);
}
