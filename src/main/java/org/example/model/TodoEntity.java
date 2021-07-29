package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity //TodoEntity 클래스가 Entity임을 명시

/*
 * ex) TodoEntity todo = new TodoEntity();
 * */
@NoArgsConstructor //파라미터가 없는 기본 생서자를 생성

/*
* ex) TodoEntity todo = new TodoEntity(1, "테스트 title", 1, true);
* */
@AllArgsConstructor //모든 필드 값을 파라미터로 받는 생서자 생성
public class TodoEntity {

    /*
        @NoArgsContructor
        public TodoEntity() {
        }
    */

    /*
        @AllArgsConstructor
        public TodoEntity(Long id, String title, Long order, Boolean completed) {
            this.id = id;
            this.title = title;
            this.order = order;
            this.completed = completed;
        }
    */

    @Id //id컬럼을 기본키로 매핑
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false) //해당 필드와 컬럼 매핑
    private String title;

    @Column(name = "todoOrder", nullable = false)
    private Long order;

    @Column(nullable = false)
    private Boolean completed;//업데이트 완료 여부 컬럼

}
