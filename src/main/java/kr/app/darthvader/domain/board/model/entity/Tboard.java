package kr.app.darthvader.domain.board.model.entity;

import jakarta.persistence.*;
import kr.app.darthvader.domain.common.model.entity.BaseEntity;
import kr.app.darthvader.domain.user.model.entity.Tuser;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tboard")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Tboard extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "board_seq")
    private Long id;

    @Column(name = "title", length = 300, nullable = false)
    private String title;

    @Column(name = "content", columnDefinition = "text")
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_seq", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Tuser tuser;

    public Tboard(String title, String content, Tuser tuser) {
        this.title = title;
        this.content = content;
        this.tuser = tuser;

        addCreateBy(tuser.getUserId());
        addUpdateBy(tuser.getUserId());
    }

}
