package org.zerock.mallapi.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.mallapi.dto.PageRequestDTO;
import org.zerock.mallapi.dto.PageResponseDTO;
import org.zerock.mallapi.dto.TodoDTO;

import java.time.LocalDate;

@SpringBootTest
@Log4j2
public class TodoServiceTests {

    @Autowired
    private TodoService todoService;

    @Test
    public void testRegister() {
        TodoDTO todoDTO = TodoDTO.builder()
                .title("서비스 등록 테스트")
                .writer("tester")
                .dueDate(LocalDate.of(2023, 10,10))
                .build();

        Long tno = todoService.register(todoDTO);
        log.info("TNO: {}", tno);
    }

    @Test
    public void testGet() {
        Long tno = 177L;

        TodoDTO todoDTO = todoService.get(tno);

        log.info(todoDTO);
    }

    @Test
    public void testModify() {
        TodoDTO todoDTO = TodoDTO.builder()
                .tno(178L)
                .title("서비스 갱신 테스트")
                .writer("tester")
                .dueDate(LocalDate.of(2023, 10,10))
                .build();

        todoService.modify(todoDTO);
    }

    @Test
    public void testRemove() {
        long tno = 202L;

        todoService.remove(tno);
    }

    @Test
    public void testList() {
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();
        PageResponseDTO<TodoDTO> pageResponseDTO = todoService.list(pageRequestDTO);

        log.info(pageResponseDTO);
    }
}
