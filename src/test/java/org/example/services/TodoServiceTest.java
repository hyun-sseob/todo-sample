package org.example.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.aspectj.lang.annotation.Before;
import org.example.model.TodoEntity;
import org.example.model.TodoRequest;
import org.example.repository.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

/*
* @RunWith(MockitoJUnitRunner.class) JUnit4 전용
*
* Mock 객체를 테스트를 실행하는동안 사용할수 있게 해준다.
*
* */

//Mock객체를 사용하기위한 Annotation(JUnit5 전용)
@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {

    /*
    * Mock Object
    *
    * 검사하고자 하는 코드와 맞물려 동작하는 객체들을 대신하여 동작하기 위해 만들어진 객체
    *
    * */

    @InjectMocks //@mock객체를 주입해준다 ex) TodoService todoService = new TodoService(new todoRepository);
    private TodoService todoService;

    @Mock //mock 객체 생성
    private TodoRepository todoRepository;

    @Test
    public void add() {
        when(this.todoRepository.save(any(TodoEntity.class)))
                .then(AdditionalAnswers.returnsFirstArg());

        TodoRequest request = new TodoRequest();
        request.setTitle("test title");
        TodoEntity actual = this.todoService.add(request);
        System.out.println(">> : " + actual.getId());
        assertEquals(1L,  actual.getId());
        assertEquals("test title",  actual.getTitle());
    }

    @Test
    public void searchById() {
        TodoEntity todo = new TodoEntity();
        todo.setTitle("test");
        todo.setId(123L);
        todo.setOrder(0L);
        todo.setCompleted(false);
        Optional<TodoEntity> expected = Optional.of(todo);

        given(this.todoRepository.findById(anyLong()))
                .willReturn(expected);

        TodoEntity actual = this.todoService.searchById(123L);

        assertEquals(actual.getId(), 123L);
        assertEquals(actual.getOrder(), 0L);
        assertFalse(actual.getCompleted());
        assertEquals(actual.getTitle(), "test");
    }

    @Test
    public void searchByIdFailed() {
        given(this.todoRepository.findById(anyLong())).willReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> {
            this.todoService.searchById(123L);
        });
    }

    @Test
    void testAdd() {
    }

    @Test
    void testSearchById() {
    }
}
