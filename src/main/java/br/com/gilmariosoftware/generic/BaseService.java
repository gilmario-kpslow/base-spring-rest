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
 */
public interface BaseService<T> {

    Optional<T> findById(Serializable id);

    List<T> findAll(int page, int count);

    T createOrUpdate(T entity);

    T delete(Serializable id);

    Long count();
}
