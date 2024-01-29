package app.note.controller;


import app.note.dto.BoardSearchCondition;
import app.note.entity.Board;
import app.note.service.BoardService;
import jakarta.websocket.server.PathParam;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Slf4j
public class BoardController {

    private final BoardService boardService;

    // list 목록 출력 ( get : /api/board )
//    @GetMapping("/board")
//    public ResponseEntity<BoardResponseDto> boardList() {
//        List<Board> list = boardService.findAll();
//
//        List<BoardResponseDto> boardResponseDtos = list.stream()
//                .map(o -> new BoardResponseDto(o))
//                .collect(Collectors.toList());
//
//        return new ResponseEntity<>(boardResponseDtos, HttpStatus.OK);
//    }

    // 리스트 조회 - JPQL 사용한 페이징.
    @GetMapping("/board")
//    public Page<BoardResponseDto> boardList(@PageableDefault(size = 5) Pageable pageable) {
    public ResponseEntity<List<BoardResponseDto>> boardList(@PageableDefault(size = 5) Pageable pageable,
                                                            BoardSearchCondition boardSearchCondition) {
        log.info("boardList");
        List<Board> list = boardService.findAll(boardSearchCondition, (int) pageable.getOffset(), pageable.getPageSize());
        List<BoardResponseDto> boardResponseDtos = list.stream()
                .map(o -> new BoardResponseDto(o))
                .collect(Collectors.toList());

        return new ResponseEntity<>(boardResponseDtos, HttpStatus.OK);

    }

    //  저장 ( post : /api/board )
    // requestdto를 service단에 보내는건 안좋지 않을가? 사실상 여기서 엔티티에 매핑해서 넘기는게 맞지 않을까?
    @PostMapping("/board")
    public ResponseEntity creatBoard(BoardRequestDto boardRequestDto) {
        Board save = boardService.save(boardRequestDto);
        return new ResponseEntity("저장 완료, "+save.getId(), HttpStatus.OK);
    }

    //  선택(단건조회) ( get: /api/board/{id} )
    @GetMapping("/board/{id}")
    public ResponseEntity<BoardResponseDto> findById(@PathParam("id") long id) {
        Board board = boardService.findById(id);
        if(board != null) {

        }
            return ResponseEntity.ok(new BoardResponseDto(board));
    }

    //  수정 ( fetch : /api/board/{id} )


    //  삭제 ( delete : /api/board/{id} )






}
