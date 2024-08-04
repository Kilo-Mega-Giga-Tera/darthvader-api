package kr.app.darthvader.domain.user.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TuserResponseDto {

    @JsonProperty("user_id")
    private String userId;

    private String name;

    private String role;

    public TuserResponseDto(String userId, String role) {
        this.userId = userId;
        this.role = role;
    }

}
