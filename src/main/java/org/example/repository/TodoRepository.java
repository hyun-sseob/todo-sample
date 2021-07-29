package org.example.repository;

import org.example.model.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/*
* JpaRepository
* crud와 같은 공통되는 코드들을 모든 repository에 작성해야되는 번거로움을 없앨수 있다.
* */
@Repository
public interface TodoRepository extends JpaRepository<TodoEntity, Long> {
}
