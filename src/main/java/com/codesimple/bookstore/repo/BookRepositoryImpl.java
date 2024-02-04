package com.codesimple.bookstore.repo;

import com.codesimple.bookstore.entity.Book;
import com.codesimple.bookstore.entity.QBook;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

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
        return jpaQuery
                .from(qBook)
                .where(qBook.yearOfPublication.eq(year))
                .fetch();
    }
}
