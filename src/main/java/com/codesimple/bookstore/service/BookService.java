package com.codesimple.bookstore.service;

import com.codesimple.bookstore.common.APIResponse;
import com.codesimple.bookstore.data.BookData;
import com.codesimple.bookstore.dto.AuthorDTO;
import com.codesimple.bookstore.dto.BookDTO;
import com.codesimple.bookstore.entity.Author;
import com.codesimple.bookstore.entity.Book;
import com.codesimple.bookstore.entity.BookAuthor;
import com.codesimple.bookstore.repo.BookAuthorRepository;
import com.codesimple.bookstore.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BookService {
   @Autowired
   private BookRepository bookRepository;

   @Autowired
   private BookAuthorRepository bookAuthorRepository;

//    public static List<Book> getBookByRawQuery(Set<Integer> yop) {
//        List<Book> bookList=BookRepository.findAllByYearOfPublicationIn(yop);
//        return null;
//    }

    public List<Book> getBooks(Set<Integer> yop,String bookType){
        List<Book> bookList=new ArrayList<>();
        if(yop==null){
            bookRepository.findAll().forEach(book -> bookList.add(book));

        }
        else {
             return bookRepository.findAllByYearOfPublicationInAndBookType(yop,bookType);
        }
        return bookList;
    }

    public Book createBook(Book book){
        return bookRepository.save(book);
    }

    public BookDTO getBookById(Long bookid, boolean authorData) {
        //Book book;
        List<BookAuthor> bookAuthors=null;

        Optional<Book> optionalBook = bookRepository.findById(bookid);


        if(authorData){
             bookAuthors= bookAuthorRepository.findAllByBookId(bookid);
        }

        BookDTO bookDTO=new BookDTO();

        //set book details
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            // Now you can use the found book
            bookDTO.setId(book.getId());
            bookDTO.setName(book.getName());
            bookDTO.setDesc(book.getDesc());
            bookDTO.setYearOfPublication(book.getYearOfPublication());
            bookDTO.setBookType(book.getBookType());
        }


        //get author details
        List<AuthorDTO> authorDTOList=new ArrayList<>();

        if(bookAuthors !=null) {
            for (BookAuthor bookAuthor : bookAuthors) {
                Author author = bookAuthor.getAuthor();

                AuthorDTO authorDTO = new AuthorDTO();

                authorDTO.setId(author.getId());
                authorDTO.setName(author.getName());
                authorDTO.setGender(author.getGender());

                authorDTOList.add(authorDTO);
            }

            //set Author details
            bookDTO.setAuthors(authorDTOList);
        }

        return bookDTO;





    }

    public Book updateBook(Book incomingBook) {
        return bookRepository.save(incomingBook);
    }

    public String deleteByBookId(Long bookid) {
        bookRepository.deleteById(bookid);
        return "book deleted successfully";
    }

    //raw query -get books
    public APIResponse getBookByRawQuery(Set<Integer> yop){
        APIResponse apiResponse=new APIResponse();

        //db call
        List<Book> bookList=bookRepository.findAllByYearOfPublicationIn(yop);

        //setData
        BookData bookData=new BookData();
        bookData.setBooks(bookList);

        //set api response
        apiResponse.setData(bookData);
        return apiResponse;
    }


}
