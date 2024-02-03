package com.codesimple.bookstore.validator;

import com.codesimple.bookstore.common.Error;
import com.codesimple.bookstore.entity.Book;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component  //ithu podda than spring container la ithu register aakum so nama autowired panni itha use panalam
public class BookValidator {
    public List<Error> validateCreateBookRequest(Book book) {

        List<Error> errors=new ArrayList<>();

        //name
        if(book.getName()==null){
            Error error=new Error("name","book name is null");
            errors.add(error);
        }

        //yop
        if(book.getYearOfPublication()==null){
            Error error=new Error("yop","yop is null");
            errors.add(error);
        }

        //book type
        if(book.getBookType()==null){
            Error error=new Error("book type","book type is null");
            errors.add(error);
        }


        return errors;
    }
}
