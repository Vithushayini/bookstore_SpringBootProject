package com.codesimple.bookstore.controller;

import com.codesimple.bookstore.common.APIResponse;
import com.codesimple.bookstore.dto.RequestMeta;
import com.codesimple.bookstore.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //ithu podathala spring ku therim ithu oru controller nu
public class AuthorController {

    @Autowired //ithu kudukaadi AuthorService authorService=new AuthorService(); ithu kudukanum
   public AuthorService authorService;

    @Autowired
    private RequestMeta requestMeta;
    @GetMapping(value = "/authors")
    private APIResponse getAuthors(@SortDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
        System.out.println(requestMeta.getEmailId());

        APIResponse apiResponse=authorService.getAuthors(pageable);

        return apiResponse;
    }

    @GetMapping(value = "/authorsWithNamed")
    private APIResponse getAuthorsWithNamedQuery(Pageable pageable){
        APIResponse apiResponse=authorService.getAuthorsWithNamedQuery(pageable);

        return apiResponse;
    }


}
