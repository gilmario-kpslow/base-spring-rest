/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gilmariosoftware.generic;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author gilmario
 * @param <T>
 * @param <D>
 */
public interface GenericService<T, D extends GenericRepository<T>> extends BaseService<T> {

    @Override
    Optional<T> findById(Serializable id);

    @Override
    List<T> findAll(int page, int size);

    @Override
    T createOrUpdate(T entity);

    @Override
    T delete(Serializable id);

    @Override
    Long count();

}
