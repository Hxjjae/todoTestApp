package com.green.todotestapp;

import com.green.todotestapp.model.TodoInsDto;
import com.green.todotestapp.model.TodoVo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@Import({TodoSerivceImpl.class})
class TodoServiceTest {

    @MockBean
    private TodoMapper mapper;

    @Autowired
    private TodoService service;

    @Test
    void insTodo() {
//        when(mapper.insTodo(any())).thenReturn(3);
//
//        TodoInsDto p1 = new TodoInsDto();
//        p1.setCtnt("test");
//        p1.setPic("pic");
//
//        int r1 = service.insTodo(p1);
//        assertEquals(3, r1);
//
//        verify(mapper).insTodo(any());
    }

    @Test
    void insTodo2() {
//        final int VAL = 4;
//        when(mapper.insTodo(any())).thenReturn(VAL);
//        TodoInsDto p1 = new TodoInsDto();
//        int r1 = service.insTodo(p1);
//        assertEquals(VAL, r1);
//
//        verify(mapper).insTodo(any());

    }

    @Test
    @DisplayName("Todo 리스트 SELECT")
    void selTodo() {
        TodoVo vo = new TodoVo();
        vo.setItodo(1L);
        vo.setCtnt("내용내용내용");
        vo.setPic("사진사진");

        TodoVo vo2 = new TodoVo();
        vo2.setItodo(2L);
        vo2.setCtnt("내용내용내용2");
        vo2.setPic("사진사진2");

        List<TodoVo> list = new ArrayList<>();
        list.add(vo);
        list.add(vo2);

        //when
        when(mapper.selTodo()).thenReturn(list);

        List<TodoVo> serviceList = service.selTodo();
        TodoVo todoVo = list.get(0);
        TodoVo todoVo2 = list.get(1);
        assertEquals(todoVo.getItodo(), serviceList.get(0).getItodo());
        assertEquals(todoVo.getCtnt(), serviceList.get(0).getCtnt());
        assertEquals(todoVo.getPic(), serviceList.get(0).getPic());

        assertEquals(todoVo2.getItodo(), serviceList.get(1).getItodo());
        assertEquals(todoVo2.getCtnt(), serviceList.get(1).getCtnt());
        assertEquals(todoVo2.getPic(), serviceList.get(1).getPic());


    }
}