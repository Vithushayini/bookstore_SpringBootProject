package com.codesimple.bookstore.controller;

import com.codesimple.bookstore.common.APIResponse;
import com.codesimple.bookstore.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //ithu podathala spring ku therim ithu oru controller nu
public class AuthorController {

    @Autowired //ithu kudukaadi AuthorService authorService=new AuthorService(); ithu kudukanum
   public AuthorService authorService;
    @GetMapping(value = "/authors")
    private APIResponse getAuthors(Pageable pageable){
        APIResponse apiResponse=authorService.getAuthors(pageable);

        return apiResponse;
    }
}
