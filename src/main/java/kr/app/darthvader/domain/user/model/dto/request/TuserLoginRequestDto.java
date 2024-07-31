package kr.app.darthvader.domain.user.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TuserLoginRequestDto {

    @JsonProperty("user_id")
    private String userId;

    private String password;

}
