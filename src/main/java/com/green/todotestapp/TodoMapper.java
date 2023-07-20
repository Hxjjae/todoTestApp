package com.green.todotestapp;

import com.green.todotestapp.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TodoMapper {
    int insTodo(TodoInsDto dto);
    List<TodoVo> selTodo();
    TodoVo selTodoByItodo(int itodo);
    int updTodo(TodoUpdDto dto);
}
