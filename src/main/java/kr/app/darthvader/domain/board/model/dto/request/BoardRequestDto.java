package kr.app.darthvader.domain.board.model.dto.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class BoardRequestDto {

    @NotEmpty(message = "title 값은 필수입니다")
    private String title;

    @NotEmpty(message = "content 값은 필수입니다")
    private String content;

}
