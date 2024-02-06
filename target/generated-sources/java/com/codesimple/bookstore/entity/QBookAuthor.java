package com.codesimple.bookstore.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QBookAuthor is a Querydsl query type for BookAuthor
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QBookAuthor extends EntityPathBase<BookAuthor> {

    private static final long serialVersionUID = 1939712391L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QBookAuthor bookAuthor = new QBookAuthor("bookAuthor");

    public final QAuthor author;

    public final QBook book;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QBookAuthor(String variable) {
        this(BookAuthor.class, forVariable(variable), INITS);
    }

    public QBookAuthor(Path<? extends BookAuthor> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QBookAuthor(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QBookAuthor(PathMetadata metadata, PathInits inits) {
        this(BookAuthor.class, metadata, inits);
    }

    public QBookAuthor(Class<? extends BookAuthor> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.author = inits.isInitialized("author") ? new QAuthor(forProperty("author")) : null;
        this.book = inits.isInitialized("book") ? new QBook(forProperty("book")) : null;
    }

}

