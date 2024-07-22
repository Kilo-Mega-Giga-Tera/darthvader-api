package kr.app.darthvader.domain.common.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @CreatedBy
    @Column(name = "create_by", updatable = false)
    private String createdBy;

    @CreatedDate
    @Column(name = "create_date", updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedBy
    @Column(name = "update_by")
    private String updateBy;

    @LastModifiedDate
    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Column(name = "del_yn", nullable = false, length = 1)
    private String delYn = "N";

    public void addCreateBy(String createBy) {
        this.createdBy = createBy;
    }

    public void addUpdateBy(String createBy) {
        this.updateBy = createBy;
    }

}

