package kr.app.darthvader.domain.user.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Data;

@Data
public class TuserResponseDto {

    @JsonProperty("user_id")
    private String userId;
    private String name;
    private String role;

    @QueryProjection
    public TuserResponseDto(String userId, String name, String role) {
        this.userId = userId;
        this.name = name;
        this.role = role;
    }

}
