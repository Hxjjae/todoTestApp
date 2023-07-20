package com.green.todotestapp;

import com.green.todotestapp.model.TodoInsDto;
import com.green.todotestapp.model.TodoUpdDto;
import com.green.todotestapp.model.TodoVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.boot.test.autoconfigure.MybatisTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@MybatisTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class TodoMapperTest {

    @Autowired
    private TodoMapper mapper;

    @Test
    void insTodo() {
//        TodoInsDto p1 = new TodoInsDto();
//        p1.setCtnt("test");
//        p1.setPic("pic");
//        int r1 = mapper.insTodo(p1);
//
//        assertEquals(1, r1);
//        assertEquals(8, p1.getItodo());
//
//        TodoInsDto p2 = new TodoInsDto();
//        p2.setCtnt("test2");
//
//        int r2 = mapper.insTodo(p2);
//
//        assertEquals(1, r2);
//        assertEquals(9, p2.getItodo());
//
//        List<TodoVo> list = mapper.selTodo();
//        TodoVo todoVo = list.get(7);
//        assertEquals(todoVo.getCtnt(),p1.getCtnt());
//        assertEquals(todoVo.getPic(),p1.getPic());
//
//        TodoVo todoVo2 = list.get(8);
//        assertEquals(todoVo2.getCtnt(),p2.getCtnt());
//        assertEquals(todoVo2.getPic(),p2.getPic());

    }

    @Test
    public void selTodo() {
        //given
        List<TodoVo> list = mapper.selTodo();

        String ctnt = list.get(0).getCtnt();
        Long itodo = list.get(0).getItodo();
        String pic = list.get(0).getPic();
        LocalDateTime createdAt = list.get(0).getCreatedAt();
        assertEquals("아무내용", ctnt);
        assertEquals(1, itodo);
        assertEquals(null,pic);
        assertEquals("2023-06-13T16:58:12", createdAt.toString());

        String ctnt2 = list.get(1).getCtnt();
        Long itodo2 = list.get(1).getItodo();
        String pic2 = list.get(1).getPic();

        assertEquals("아무내용", ctnt2);
        assertEquals(2, itodo2);
        assertEquals(null,pic2);
    }

    @Test
    public void updTodo() {
        //given
        TodoUpdDto dto = new TodoUpdDto();
        dto.setItodo(1L);
        dto.setCtnt("수정했음내용");
        dto.setPic("사진사진수정했음");

        int result = mapper.updTodo(dto);
        assertEquals(result,1);

        List<TodoVo> list = mapper.selTodo();
        TodoVo todoVo = list.get(0);

        assertEquals(todoVo.getItodo(),dto.getItodo());
        assertEquals(todoVo.getCtnt(),dto.getCtnt());
        assertEquals(todoVo.getPic(),dto.getPic());
    }
}