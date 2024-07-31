package kr.app.darthvader.domain.user.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryProjection;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.Map;

@Data
public class TuserRequestDto {

    @JsonProperty("user_id")
    @NotEmpty(message = "user_id 값은 필수입니다")
    private String userId;

    @NotEmpty(message = "name 값은 필수입니다")
    private String name;

    @NotEmpty(message = "password 값은 필수입니다")
    private String password;

    private Map<String, Object> user;

    @QueryProjection
    public TuserRequestDto(String userId, String name, String password) {
        this.userId = userId;
        this.name = name;
        this.password = password;
    }

}
