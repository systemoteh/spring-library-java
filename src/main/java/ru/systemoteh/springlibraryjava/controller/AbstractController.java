package ru.systemoteh.springlibraryjava.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

/**
 * Описывает необходимое поведение для всех контроллеров
 * @param <T> - конкретный контроллер
 */
public abstract class AbstractController<T> implements Serializable {
    // постранично выводит сущности
    public abstract Page<T> search(int first, int count, String sortField, Sort.Direction sortDirection);

    // для основных действий (CRUD)
    public abstract T findById(Long id);

    public abstract void addAction(T t);

    public abstract void editAction(T t);

    public abstract void deleteAction(T t);
}
