package kr.app.darthvader.domain.board.model.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BoardRequestDto {

    @NotEmpty(message = "title 값은 필수입니다")
    private String title;

    @NotEmpty(message = "content 값은 필수입니다")
    private String content;

    @NotNull(message = "user_seq 값은 필수입니다")
    @JsonProperty("user_seq")
    private Long userSeq;

}
