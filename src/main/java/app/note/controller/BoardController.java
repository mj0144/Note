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

    // 게시물 목록 조회
    @GetMapping("/board")
    public ResponseEntity<List<BoardResponseDto>> getBoards(@PageableDefault(size = 5) Pageable pageable,
                                                            BoardSearchCondition boardSearchCondition) {
        log.info("boardList");
        List<Board> list = boardService.getBoards(boardSearchCondition, (int) pageable.getOffset(), pageable.getPageSize());
        List<BoardResponseDto> boardResponseDtos = list.stream()
                .map(o -> new BoardResponseDto(o))
                .collect(Collectors.toList());

        return new ResponseEntity<>(boardResponseDtos, HttpStatus.OK);
    }

    // 게시물 단건 조회
    @GetMapping("/board/{id}")
    public ResponseEntity<BoardResponseDto> getBoard(@PathVariable("id") long id) {
        Board board = boardService.getBoard(id);
        if (board != null) {
            return ResponseEntity.ok(new BoardResponseDto(board));
        }
        return ResponseEntity.noContent().build(); //204(no_content) 반환
    }

    //  게시물 저장
    // TODO : requestdto를 service단에 보내는건 안좋지 않을가? 사실상 여기서 엔티티에 매핑해서 넘기는게 맞지 않을까?
    @PostMapping("/board")
    public ResponseEntity creatBoard(BoardRequestDto boardRequestDto) {
        Board save = boardService.createBoard(boardRequestDto);
        return new ResponseEntity("저장 완료, "+save.getId(), HttpStatus.OK);
    }

    // 게시물 수정
    @PutMapping("/board/{id}")
    public ResponseEntity<String> updateBoard(@PathVariable("id") long id, BoardUpdateRequestDto boardUpdateRequestDto) {
        boardService.updateBoard(boardUpdateRequestDto);
        try {
            return ResponseEntity.ok("정상처리 되었습니다");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    //  삭제 ( delete : /api/board/{id} )
    @DeleteMapping("/board/{id}")
    public ResponseEntity<String> deleteBoard(@PathVariable("id") long id) {

    }






}
