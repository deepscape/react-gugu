package org.zerock.mallapi.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.zerock.mallapi.domain.Todo;

import java.time.LocalDate;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
public class TodoRepositoryTests {

    @Autowired
    private TodoRepository todoRepository;

    @Test
    public void test1() {
        log.info("-------------------------------");
        log.info(todoRepository);
    }

    @Test
    public void testInsert() {
        IntStream.rangeClosed(0, 99)
                .mapToObj(i -> Todo.builder()
                        .title("Title..." + i)
                        .dueDate(LocalDate.of(2023, 12, 31))
                        .writer("user00")
                        .build())
                .forEach(todoRepository::save);
    }
}
