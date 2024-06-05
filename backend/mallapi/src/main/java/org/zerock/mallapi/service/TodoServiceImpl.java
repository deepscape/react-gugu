package org.zerock.mallapi.service;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.zerock.mallapi.dto.TodoDTO;

@Service
@Transactional
@Log4j2
public class TodoServiceImpl implements TodoService {
    @Override
    public Long register(TodoDTO todoDTO) {
        log.info("register todo");

        return null;
    }
}
