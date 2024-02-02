package com.codesimple.bookstore.repo;

import com.codesimple.bookstore.entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findAllByYearOfPublicationInAndBookType(Set<Integer> yop ,String bookType);

    @Query(nativeQuery = true,value = rawQuery)
    List<Book> findAllByYearOfPublicationIn(Set<Integer> yop);

   String rawQuery="select * from book where year_of_publication IN ?1";
}

