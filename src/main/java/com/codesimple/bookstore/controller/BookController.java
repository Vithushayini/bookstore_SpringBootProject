package com.codesimple.bookstore.controller;

import com.codesimple.bookstore.dto.BookDTO;
import com.codesimple.bookstore.entity.Book;
import com.codesimple.bookstore.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

@RestController
public class BookController {
@Autowired
    private BookService bookService;
    @RequestMapping(value = "/books")
    public List<Book> getBooks(@RequestParam(value="yearOfPublications",required = false) Set<Integer> yop,
                                 @RequestParam(value = "bookType",required = false)String bookType)
    {
        return bookService.getBooks(yop,bookType);
    }
    @RequestMapping(value="/books",method= RequestMethod.POST)
    public Book createBook(@RequestBody Book book){
        return bookService.createBook(book);
    }

    @RequestMapping(value="/books/{id}")
    public BookDTO getBookById(
            @PathVariable("id") Long bookId,
            @RequestParam(value = "authorData",required = false) boolean authorData){
    return bookService.getBookById(bookId,authorData);
    }
@RequestMapping(value = "/books",method = RequestMethod.PUT)
    public Book updateBook(@RequestBody Book incomingBook){
        return bookService.updateBook(incomingBook);
}
@RequestMapping(value = "/books/{bookid}",method = RequestMethod.DELETE)
public String deleteBookById(@PathVariable Long bookid){
   return bookService.deleteByBookId(bookid);
}

@GetMapping("/raw/books")
public List<Book> getBookByRawQuery(@RequestParam(value = "yearOfPublications") Set <Integer> yop){
        return  bookService.getBookByRawQuery(yop);
}

}
