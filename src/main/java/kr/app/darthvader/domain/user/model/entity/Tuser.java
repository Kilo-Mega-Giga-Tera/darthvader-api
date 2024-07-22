package kr.app.darthvader.domain.user.model.entity;

import jakarta.persistence.*;
import kr.app.darthvader.domain.board.model.entity.Tboard;
import kr.app.darthvader.domain.common.model.entity.BaseEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TUSER")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Tuser extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_seq")
    private Long seq;

    @Column(name = "user_id", length = 50, nullable = false, unique = true)
    private String userId;

    @Column(name = "password", length = 65, nullable = false)
    private String password;

    @Column(name = "user_nm", length = 50, nullable = false)
    private String userNm;

    @Column(name = "role", length = 10, nullable = false)
    private String role = "ROLE_USER";

    @OneToMany(mappedBy = "tuser")
    List<Tboard> tboardList = new ArrayList<>();

    public Tuser(String userNm, String userId, String password) {
        this.userNm = userNm;
        this.userId = userId;
        this.password = password;

        super.addCreateBy(getUserId());
        super.addUpdateBy(getUserId());
    }

}
