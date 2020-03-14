package ru.systemoteh.springlibraryjava.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

/**
 * Сущность Жанр
 */
// JPA
@Entity // все поля класса будут автоматически связаны со столбцами таблицы
@Table(catalog = "library", name = "genre")
// Lombok
@EqualsAndHashCode(of = "id")
@Data // генерация гетеров-сетеров для всех полей класса
// аннотации Hibernate
@DynamicUpdate // обновляет только те поля, которые изменились
@DynamicInsert // вставляет только те поля, у которых есть значение
@SelectBeforeUpdate // проверить объект перед обновлением, нужно ли его обновлять
public class Genre {

    @Id
    // autoincrement for Oracle DB, PostgreSQL
    @SequenceGenerator(name = "genre_generator", sequenceName = "genre_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "genre_generator")
    // autoincrement for MS SQL, MySQL
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // for MS SQL, MySQL
    private Long id;
    private String name;
    // двухсторонняя связь с Book
    @Basic(fetch = FetchType.LAZY) // коллекция будет запрашиваться только по требованию (ленивая инициализация)
    @OneToMany(mappedBy = "genre") // genre должно совпадать с именем поля в классе Book
    private List<Book> books;

    @Override
    public String toString() {
        return name;
    }
}
