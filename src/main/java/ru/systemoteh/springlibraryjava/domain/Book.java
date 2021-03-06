package ru.systemoteh.springlibraryjava.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;

/**
 * Сущность Книга
 */
// JPA
@Entity // все поля класса будут автоматически связаны со столбцами таблицы
@Table(catalog = "library", name = "book")
// Lombok
@EqualsAndHashCode(of = "id")
@Data // генерация гетеров-сетеров для всех полей класса
// аннотации Hibernate
@DynamicUpdate // обновляет только те поля, которые изменились
@DynamicInsert // вставляет только те поля, у которых есть значение
@SelectBeforeUpdate // проверить объект перед обновлением, нужно ли его обновлять
public class Book {

    public Book() {
    }

    // здесь нет заполнения поля content - чтобы не грузить страницу (контент получаем по требованию)
    public Book(Long id, String name, Integer pageCount, String isbn, Genre genre, Author author, Publisher publisher, Integer publishYear, byte[] image, String description, long viewCount, long totalRating, long totalVoteCount, long avgRating) {
        this.id = id;
        this.name = name;
        this.pageCount = pageCount;
        this.isbn = isbn;
        this.genre = genre;
        this.author = author;
        this.publisher = publisher;
        this.publishYear = publishYear;
        this.image = image;
        this.description = description;
        this.viewCount = viewCount;
        this.totalRating = totalRating;
        this.totalVoteCount = totalVoteCount;
        this.avgRating = avgRating;
    }

    public Book(Long id, byte[] image) {
        this.id = id;
        this.image = image;
    }

    @Id
    // autoincrement for Oracle DB, PostgreSQL
    @SequenceGenerator(name = "author_generator", sequenceName = "author_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_generator")
    // autoincrement for MS SQL, MySQL
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // for MS SQL, MySQL
    private Long id;
    private String name;
    private String isbn;
    private String description;
    @Column(name = "page_count")
    private Integer pageCount;
    @Column(name = "publish_year")
    private Integer publishYear;
    @Column(name = "view_count")
    private long viewCount;
    @Column(name = "total_rating")
    private long totalRating;
    @Column(name = "total_vote_count")
    private long totalVoteCount;
    @Column(name = "avg_rating")
    private long avgRating;

    // по-умолчанию Hibernate пытается связать по полю genre_id (как в нашей таблице), если имя столбца другое, нужно задавать атрибут name у @JoinColumn
    @JoinColumn // для получения готового объекта Genre по id
    @ManyToOne(fetch = FetchType.LAZY) // ссылка foreign key идет из таблицы Book в таблицу Genre
    private Genre genre;
    @ManyToOne(fetch = FetchType.LAZY)
    private Author author;
    @JoinColumn
    @ManyToOne(fetch = FetchType.LAZY)
    private Publisher publisher;

    @Column(updatable = false) // updatable = false: при апдейте это поле не будет добавляться
    // (content будем обновлять отдельным запросом)
    private byte[] content;
    private byte[] image;

    @Override
    public String toString() {
        return name;
    }
}
