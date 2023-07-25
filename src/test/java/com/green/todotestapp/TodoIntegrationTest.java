package com.green.todotestapp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.green.todotestapp.model.TodoRes;
import com.green.todotestapp.utils.MyFileUtils;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.mock.web.MockPart;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Slf4j
public class TodoIntegrationTest extends IntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;
    @Value("${file.dir}")
    private String fileDir;

    @Test
    @Rollback(false)
    public void postTodo() throws Exception {
        String originalFileNm = "9e83131e-7fa4-4f80-9e0b-b0c1741db58e";
        String contentType = "png";
        String filePath = "D:/home/download/todo/14/" + originalFileNm + "." + contentType;
        FileInputStream fileInputStream = new FileInputStream(filePath);
        MockMultipartFile pic = new MockMultipartFile("pic", originalFileNm, "png", fileInputStream);


        MvcResult mr = mvc.perform(multipart("/api/todo")
                        .file(pic)
                        .part(new MockPart("ctnt", "테스트3".getBytes(StandardCharsets.UTF_8)))
                )
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        String content = mr.getResponse().getContentAsString();
        TodoRes todoRes = om.readValue(content, TodoRes.class);
        log.info("toodRes : {}", todoRes);

        String dicPath = MyFileUtils.getAbsolutePath(fileDir + "/todo/" + todoRes.getItodo());
        File dicFile = new File(dicPath);
        assertEquals(true, dicFile.exists(), "1번 폴더가 없음");

        File picFile = new File(dicPath, todoRes.getPic());
        assertEquals(true, picFile.exists(), "1번 이미지가 없음");
        assertEquals("테스트3", todoRes.getCtnt());
    }
}
