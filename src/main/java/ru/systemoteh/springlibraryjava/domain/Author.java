package ru.systemoteh.springlibraryjava.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Сущность Автор
 */
// JPA
@Entity // все поля класса будут автоматически связаны со столбцами таблицы
@Table(catalog = "library", name = "author")
// Lombok
@EqualsAndHashCode(of = "id")
@Data // генерация гетеров-сетеров для всех полей класса
// аннотации Hibernate
@DynamicUpdate // обновляет только те поля, которые изменились
@DynamicInsert // вставляет только те поля, у которых есть значение
@SelectBeforeUpdate  // проверить объект перед обновлением, нужно ли его обновлять
public class Author {

    @Id
    // autoincrement for Oracle DB, PostgreSQL
    @SequenceGenerator(name = "author_generator", sequenceName = "author_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "author_generator")
    // autoincrement for MS SQL, MySQL
//    @GeneratedValue(strategy = GenerationType.IDENTITY) // for MS SQL, MySQL
    private Long id;
    private String name;
    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date")
    private Date birthDate;

    // двухсторонняя связь с Book
    @Basic(fetch = FetchType.LAZY) // коллекция будет запрашиваться только по требованию (ленивая инициализация)
    @OneToMany(mappedBy = "author") // author должно совпадать с именем поля в классе Book
    private List<Book> books;

    @Override
    public String toString() {
        return name;
    }
}
