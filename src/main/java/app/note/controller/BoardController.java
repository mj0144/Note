package app.note.controller;


import app.note.dto.BoardSearchCondition;
import app.note.entity.Board;
import app.note.service.BoardService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    // 게시물 목록 조회
    @GetMapping("/board")
    public ResponseEntity<List<BoardResponseDto>> getBoards(@PageableDefault(size = 5) Pageable pageable,
                                                             BoardSearchCondition boardSearchCondition) {
        log.info("boardList");
        List<Board> list = boardService.getBoards(boardSearchCondition, pageable);
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
    @PostMapping("/board")
    public ResponseEntity creatBoard(@Valid @RequestBody BoardRequestDto boardRequestDto) {
        Board save = boardService.createBoard(boardRequestDto);
        return new ResponseEntity("저장 완료, "+save.getId(), HttpStatus.OK); // TODO : 리턴시, 상태코드랑 이런것도 넘겨야지.
    }

    // 게시물 수정
    @PutMapping("/board/{id}")
    public ResponseEntity<String> updateBoard(@PathVariable("id") long id,
                                              @RequestBody BoardUpdateRequestDto boardUpdateRequestDto) {
        try {
            boardService.updateBoard(id, boardUpdateRequestDto);
            return ResponseEntity.ok("정상처리 되었습니다");
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    //  삭제 ( delete : /api/board/{id} )
    @DeleteMapping("/board/{id}")
    public ResponseEntity<String> deleteBoard(@PathVariable("id") long id) {
        try {
            boardService.deletBoard(id);
            return ResponseEntity.ok("정상처리 되었습니다");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.internalServerError().build();
        }

    }

}
