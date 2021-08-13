package com.example.board.domain;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass   //이 entity를 상속할경우 필드(createDate, modifiedDate)등도 칼럼으로 인식하도록 함
@EntityListeners(AuditingEntityListener.class)      //Auditing 기능 추가
public class BaseTimeEntity {
    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
/**
 * BaseTimeEntity 클래스는 모든 Entity의 상위 클래스가 되어 Entity들의 생성/수정 시간을 자동으로 관리하는 역할을 한다.
 * @MappedSuperclass
 * JPA Entity 클래스들이 BaseTimeEntity를 상속할 경우 필드들도 칼럼으로 인식하게 함
 * @EntityListeners(AuditingEuntityListener.class)
 * BaseTimeEntity 클래스에 Auditing 기능을 포함시킴
 * Auditing이란, 감사 기능을 뜻한다. 의심가는 DB 작업을 모니터링 하면서 기록 정보를 수집하는 기능
 * 클래스 내부 어노테이션은 직관적으로 이해 가능
 * Posts 클래스가 BaseTimeEntity 클래스를 상속받도록 변경
 **/