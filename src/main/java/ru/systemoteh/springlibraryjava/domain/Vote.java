package ru.systemoteh.springlibraryjava.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.util.Date;

/**
 * Сущность Голосование
 */
// JPA
@Entity // все поля класса будут автоматически связаны со столбцами таблицы
@Table(catalog = "library", name = "vote")
// Lombok
@EqualsAndHashCode(of = "id")
@Data // генерация гетеров-сетеров для всех полей класса
// аннотации Hibernate
@DynamicUpdate // обновляет только те поля, которые изменились
@DynamicInsert // вставляет только те поля, у которых есть значение
@SelectBeforeUpdate // проверить объект перед обновлением, нужно ли его обновлять
public class Vote {

    @Id
    // autoincrement for Oracle DB, PostgreSQL
    @SequenceGenerator(name = "vote_generator", sequenceName = "vote_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vote_generator")
    // autoincrement for MS SQL, MySQL
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // for MS SQL, MySQL
    private Long id;
    private String value;
    @Column(name = "book_id")
    private Long bookId;
    private String username;
}
