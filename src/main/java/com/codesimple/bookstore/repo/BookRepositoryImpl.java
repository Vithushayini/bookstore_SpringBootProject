package com.codesimple.bookstore.repo;

import com.codesimple.bookstore.dto.BookQueryDslDTO;
import com.codesimple.bookstore.entity.Book;
import com.codesimple.bookstore.entity.QBook;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.QBean;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.ArrayList;
import java.util.List;

public class BookRepositoryImpl implements BookRepositoryCustom{
    @PersistenceContext
    EntityManager em;

    public static QBook qBook=QBook.book;
    @Override
    public List<Book> getAllBooksByQueryDsl(Integer year) {

        //query dsl
        JPAQuery<Book> jpaQuery=new JPAQuery<>(em);

        //select * from book where year_of_publication = year;


        //return
//        return jpaQuery
//                .from(qBook)
//                .where(qBook.yearOfPublication.eq(year))
//                .fetch();
//    }

        //select id,bookType from book where year_of_publication = year;

        //method1 using tuple
        /*List<Tuple> tuples=jpaQuery
                .select(qBook.id,qBook.bookType)
                .from(qBook)
                .where(qBook.yearOfPublication.eq(year))
                .fetch();

        List<Book> books=new ArrayList<>();

        for (Tuple eachTuple:tuples){
//            System.out.println(eachTuple.get(qBook.id));
//            System.out.println(eachTuple.get(qBook.bookType));
            Book book=new Book();
            book.setId(eachTuple.get(qBook.id));
            book.setBookType(eachTuple.get(qBook.bookType));

            books.add(book);
        }*/


        //method2 using projection
        QBean<Book> bookQBean= Projections.bean(Book.class,
                qBook.id,
                qBook.bookType
        );

        List<Book> books=jpaQuery
                .select(bookQBean)
                .from(qBook)
                .where(qBook.yearOfPublication.eq(year))
                .fetch();

        //return
        return books;
    }

    @Override
    public List<BookQueryDslDTO> getAllBooksByQueryDslDto(Integer year) {
        //query dsl
        JPAQuery<BookQueryDslDTO> jpaQuery=new JPAQuery<>(em);

        QBean<BookQueryDslDTO> dslDTOQBean=Projections.bean(BookQueryDslDTO.class,
                qBook.id,
                qBook.bookType.as("type")
        );

        List<BookQueryDslDTO> books=jpaQuery
                .select(dslDTOQBean)
                .from(qBook)
                .where(qBook.yearOfPublication.eq(year))
                .fetch();

        return books;
    }

}
