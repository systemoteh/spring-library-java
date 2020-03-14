package ru.systemoteh.springlibraryjava.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import ru.systemoteh.springlibraryjava.domain.Book;

import java.util.List;

// описывает специфичное поведение для работы с книгами
public interface BookService extends AbstractService<Book> {

    // поиск топовых книг
    List<Book> findTopBooks(int limit);

    // получение контента по id
    byte[] getContent(long id);

    // постраничный вывод книг определенного жанра
    Page<Book> findByGenre(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection, long genreId);

    // обновление статистики просмотра книги
    void updateViewCount(long viewCount, long id);

    // обновить данные рейтинга
    void updateRating(long totalRating, long totalVoteCount, int avgRating, long id);
}