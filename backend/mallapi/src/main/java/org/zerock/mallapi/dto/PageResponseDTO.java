package org.zerock.mallapi.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Data
public class PageResponseDTO<E> {
    private List<E> dtoList;                                                                            // 현재 페이지에 해당하는 데이터 리스트
    private List<Integer> pageNumList;                                                                  // 페이지 번호 리스트
    private PageRequestDTO pageRequestDTO;                                                              // 페이지 요청 정보를 담고 있는 객체
    private boolean prev, next;                                                                         // 이전, 다음 페이지 여부
    private int totalCount, prevPage, nextPage, totalPage, current;                                     // 전체 데이터 수, 이전 페이지 번호, 다음 페이지 번호, 총 페이지 수, 현재 페이지 번호

    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(List<E> dtoList, PageRequestDTO pageRequestDTO, long totalCount) {

        this.dtoList = dtoList;
        this.pageRequestDTO = pageRequestDTO;
        this.totalCount = (int) totalCount;

        // 현재 페이지 계산
        int currentPage =  pageRequestDTO.getPage();
        // 현재 페이지가 속한 범위의 끝 페이지 번호 계산
        // 예를 들어, 현재 페이지가 7이면 end 는 10, 현재 페이지가 15이면 end 는 20
        int end = (int)(Math.ceil(currentPage / 10.0)) * 10;
        // 시작 페이지 번호 계산
        // 예를 들어, end 가 10이면 start 는 1, end 가 20이면 start 는 11
        int start = end - 9;
        // 전체 페이지 수 계산
        int last = (int)(Math.ceil((totalCount/(double)pageRequestDTO.getSize())));
        // end 가 last 를 초과하지 않도록 조정
        end = end > last ? last : end;

        // 이전 페이지 링크 여부 결정
        this.prev = currentPage > 1;
        // 다음 페이지 링크 여부 결정
        this.next = currentPage < last;

        // 페이지 번호 리스트 생성 : 페이지네이션 UI에 표시
        this.pageNumList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList());

        // 이전 페이지 번호 설정
        if(prev) { this.prevPage = currentPage - 1; }
        // 현재 페이지 번호 설정
        if(next) { this.nextPage = currentPage + 1; }

        // 총 페이지 수 설정
        this.totalPage = last;
        // 현재 페이지 번호 설정
        this.current = currentPage;
    }

}