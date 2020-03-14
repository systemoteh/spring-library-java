package ru.systemoteh.springlibraryjava.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.util.List;

/**
 * Сущность Издательство
 */
// JPA
@Entity // все поля класса будут автоматически связаны со столбцами таблицы
@Table(catalog = "library", name = "publisher")
// Lombok
@EqualsAndHashCode(of = "id")
@Data // генерация гетеров-сетеров для всех полей класса
// Hibernate
@DynamicUpdate // обновляет только те поля, которые изменились
@DynamicInsert // вставляет только те поля, у которых есть значение
@SelectBeforeUpdate // проверить объект перед обновлением, нужно ли его обновлять
public class Publisher {

    @Id
    // autoincrement for Oracle DB, PostgreSQL
    @SequenceGenerator(name = "publisher_generator", sequenceName = "publisher_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "publisher_generator")
    // autoincrement for MS SQL, MySQL
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // for MS SQL, MySQL
    private Long id;
    private String name;

    // двухсторонняя связь с Book
    @Basic(fetch = FetchType.LAZY) // коллекция будет запрашиваться только по требованию (ленивая инициализация)
    @OneToMany(mappedBy = "publisher") // publisher должно совпадать с именем поля в классе Book
    private List<Book> books;

    @Override
    public String toString() {
        return name;
    }
}
