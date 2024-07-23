package kr.app.darthvader.domain.board.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryProjection;
import kr.app.darthvader.domain.board.model.entity.Tboard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class BoardResponseDto {

    @JsonProperty("board_seq")
    private Long boardSeq;

    private String title;
    private String content;

    @JsonProperty("user_seq")
    private Long userSeq;

    @QueryProjection
    public BoardResponseDto(@NonNull Tboard board) {
        this.title = board.getTitle();
        this.content = board.getContent();
        this.boardSeq = board.getId();
        this.userSeq = board.getTuser().getSeq();
    }

}
