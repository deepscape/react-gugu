package org.zerock.mallapi.service;

import org.zerock.mallapi.dto.PageRequestDTO;
import org.zerock.mallapi.dto.PageResponseDTO;
import org.zerock.mallapi.dto.TodoDTO;

public interface TodoService {

    // Todo 등록
    Long register(TodoDTO todoDTO);

    // Todo 조회
    TodoDTO get(Long tno);

    // Todo 수정
    void modify(TodoDTO todoDTO);

    // Todo 삭제
    void remove(Long tno);

    // 페이징 처리
    PageResponseDTO<TodoDTO> list(PageRequestDTO pageRequestDTO);
}
