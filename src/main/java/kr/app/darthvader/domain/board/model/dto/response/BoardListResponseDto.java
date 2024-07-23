package kr.app.darthvader.domain.board.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryProjection;
import kr.app.darthvader.domain.board.model.entity.Tboard;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class BoardListResponseDto {

    @JsonProperty("board_seq")
    private Long boardSeq;

    private String title;

    @JsonProperty("create_date")
    private LocalDateTime createdDate;

    @JsonProperty("update_date")
    private LocalDateTime updatedDate;

    @JsonProperty("create_at")
    private String createAt;

    @JsonProperty("update_at")
    private String updateAt;

    @QueryProjection
    public BoardListResponseDto(@NonNull Tboard board) {
        this.title = board.getTitle();
        this.boardSeq = board.getId();
        this.createAt = board.getCreatedBy();
        this.updateAt = board.getUpdateBy();
        this.createdDate = board.getCreatedDate();
        this.updatedDate = board.getUpdateDate();
    }

}
