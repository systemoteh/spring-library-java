package ru.systemoteh.springlibraryjava.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.systemoteh.springlibraryjava.domain.Book;
import ru.systemoteh.springlibraryjava.repository.BookRepository;
import ru.systemoteh.springlibraryjava.service.BookService;

import java.util.List;
import java.util.Optional;

// API сервисного уровня для работы с книгами
@Service
@Transactional(transactionManager = "transactionManager")
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> getAll(Sort sort) {
        return bookRepository.findAll(sort);
    }

    @Override
    public Page<Book> getAll(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection) {
        return bookRepository.findAllWithoutContent(PageRequest.of(pageNumber, pageSize, Sort.by(sortDirection, sortField)));
    }

    @Override
    public List<Book> search(String... searchString) {
        return null;
    }

    @Override
    public Page<Book> search(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection, String... searchString) {
        // чтобы название метода не было слишком длинным - можно использовать @Query c HQL (если больше 2-х переменных)
        return bookRepository.findByNameContainingIgnoreCaseOrAuthorNameContainingIgnoreCaseOrderByName(searchString[0], searchString[0], PageRequest.of(pageNumber, pageSize, Sort.by(sortDirection, sortField)));
    }

    @Override
    public Book save(Book book) {
        bookRepository.save(book);
        if (book.getContent() != null) {
            bookRepository.updateContent(book.getContent(), book.getId());
        }
        return book;
    }

    @Override
    public void delete(Book book) {
        bookRepository.delete(book);
    }

    @Override
    public Book get(long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public byte[] getContent(long id) {
        return bookRepository.getContent(id);
    }

    public List<Book> findTopBooks(int limit) {
        return bookRepository.findTopBooks(PageRequest.of(0, limit, Sort.by(Sort.Direction.DESC, "viewCount")));
    }

    @Override
    public Page<Book> findByGenre(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection, long genreId) {
        return bookRepository.findByGenre(genreId, PageRequest.of(pageNumber, pageSize, Sort.by(sortDirection, sortField)));
    }

    @Override
    public void updateViewCount(long viewCount, long id) {
        bookRepository.updateViewCount(viewCount, id);
    }

    @Override
    public void updateRating(long totalRating, long totalVoteCount, int avgRating, long id) {
        bookRepository.updateRating(totalRating, totalVoteCount, avgRating, id);
    }
}