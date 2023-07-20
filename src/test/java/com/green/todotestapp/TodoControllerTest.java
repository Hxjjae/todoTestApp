package com.green.todotestapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.todotestapp.model.TodoInsDto;
import com.green.todotestapp.model.TodoRes;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockPart;
import org.springframework.test.web.servlet.MockMvc;

import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TodoController.class)
class TodoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TodoService service;

    @Test
    @DisplayName("TODO - 등록")
    void insTodo() throws Exception {
        TodoInsDto dto = new TodoInsDto();
        dto.setItodo(3L);
        dto.setCtnt("테스트1");
        dto.setPic("main.jpg");
        TodoRes res = new TodoRes(dto);
        given(service.insTodo(any())).willReturn(res);



        String originalFileNm = "9e83131e-7fa4-4f80-9e0b-b0c1741db58e";
        String contentType = "png";
        String filePath = "D:/home/download/todo/14/" + originalFileNm + "." + contentType;
        FileInputStream fileInputStream = new FileInputStream(filePath);
        MockMultipartFile pic = new MockMultipartFile("pic", originalFileNm, "png", fileInputStream);

        ObjectMapper om = new ObjectMapper();
        String resJson = om.writeValueAsString(res);


        mvc.perform(multipart("/api/todo")
                        .file(pic)
                        .part(new MockPart("ctnt", "테스트3".getBytes(StandardCharsets.UTF_8)))
                )
                .andExpect(status().isOk())
                .andExpect(content().string(resJson))
                .andDo(print());

        verify(service).insTodo(any());
    }
}