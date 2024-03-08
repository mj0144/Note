package app.note;

import app.note.controller.BoardController;
import app.note.controller.BoardRequestDto;
import app.note.dto.BoardSearchCondition;
import app.note.service.BoardService;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


import java.net.http.HttpClient;

@ExtendWith(MockitoExtension.class)
public class MockTest {

    @InjectMocks
    private BoardController boardController;

    @Mock
    private BoardService boardService;

    private MockMvc mockMvc; // 일반적으로 HTTP 호출이 불가능하므로 MockMVC 사용.

    @BeforeEach
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(boardController).build();
    }


    @DisplayName("게시물 저장 테스트")
    @Test
    void saveTest() throws Exception {
        // given
        BoardRequestDto request = dataRequest();
        BoardRequestDto response = dataResponse();

        Mockito.doReturn(response)
                .when(boardService.createBoard(request));



        //when
        ResultActions json = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/board")
                        .contentType("JSON")
                        .content(new ObjectMapper().writeValueAsString(request))
        );

        //then
        json.andExpect(MockMvcResultMatchers.jsonPath("title").value("제목테스트2"));





    }

    private BoardRequestDto dataRequest() {
        return BoardRequestDto.builder()
                .title("제목테스트1")
                .content("내용테스트1")
                .build();
    }

    private BoardRequestDto dataResponse() {
        return BoardRequestDto.builder()
                .title("제목테스트1")
                .content("내용테스트1")
                .build();
    }
}
