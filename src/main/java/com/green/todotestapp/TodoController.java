package com.green.todotestapp;


import com.green.todotestapp.model.TodoInsDto;
import com.green.todotestapp.model.TodoInsParam;
import com.green.todotestapp.model.TodoRes;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;

@RestController
@RequestMapping("/api/todo")
@RequiredArgsConstructor
public class TodoController {

    @Autowired
    private final TodoService SERVICE;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public TodoRes insTodo(@RequestPart String ctnt,@RequestPart MultipartFile pic) {
        TodoInsParam p = new TodoInsParam();
        p.setCtnt(ctnt);
        p.setPic(pic);
        return SERVICE.insTodo(p);
    }
}
