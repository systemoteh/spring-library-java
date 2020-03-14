package ru.systemoteh.springlibraryjava.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.systemoteh.springlibraryjava.domain.Genre;
import ru.systemoteh.springlibraryjava.repository.GenreRepository;
import ru.systemoteh.springlibraryjava.service.GenreService;

import java.util.List;
import java.util.Optional;

// API сервисного уровня для работы с жанрами
@Service
@Transactional
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreRepository genreRepository;

    @Override
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }

    public List<Genre> getAll(Sort sort) {
        return genreRepository.findAll(sort);
    }

    @Override
    public Page<Genre> getAll(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection) {
        return genreRepository.findAll(PageRequest.of(pageNumber, pageSize, Sort.by(sortDirection, sortField)));
    }

    @Override
    public List<Genre> search(String... searchString) {
        return genreRepository.findByNameContainingIgnoreCaseOrderByName(searchString[0]);
    }

    @Override
    public Page<Genre> search(int pageNumber, int pageSize, String sortField, Sort.Direction sortDirection, String... searchString) {
        return genreRepository.findByNameContainingIgnoreCaseOrderByName(searchString[0], PageRequest.of(pageNumber, pageSize, Sort.by(sortDirection, sortField)));
    }

    @Override
    public Genre save(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public void delete(Genre genre) {
        genreRepository.delete(genre);

    }

    @Override
    public Genre get(long id) {
        return genreRepository.findById(id).orElse(null); // Optional - обертка, в котором может быть значение или пусто (используется для исключение ошибки NullPointerException
    }
}