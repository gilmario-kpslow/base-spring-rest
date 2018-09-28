/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gilmariosoftware.generic;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;

/**
 *
 * @author gilmario
 * @param <T>
 * @param <D>
 */
public class GenericServiceImpl<T, D extends GenericRepository<T>> implements GenericService<T, D> {

    @Autowired
    protected D repository;

    public GenericServiceImpl() {
    }

    @Override
    public Optional<T> findById(Serializable id) {
        return this.repository.findById(id);
    }

    @Override
    public List<T> findAll(int page, int size) {
        PageRequest pageable = PageRequest.of(page, size);
        return (List<T>) repository.findAll(pageable).getContent();
    }

    @Override
    public T createOrUpdate(T entity) {
        return this.repository.save(entity);
    }

    @Override
    public T delete(Serializable id) {
        T t = findById(id).get();
        this.repository.delete(t);
        return t;
    }

    @Override
    public Long count() {
        return this.repository.count();
    }

}
