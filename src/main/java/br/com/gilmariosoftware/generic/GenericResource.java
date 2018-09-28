/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gilmariosoftware.generic;

import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author gilmario
 * @param <T>
 * @param <S>
 */
public abstract class GenericResource<T, S extends BaseService<T>> {

    @Autowired
    public S service;

    @ApiOperation("Retorna uma lista com todas as informações")
    @GetMapping()
    public List<T> list(@RequestParam("page") int page, @RequestParam("size") int size) {
        return this.service.findAll(page, size);
    }

    @ApiOperation("Cria uma nova entidade")
    @PostMapping
    public T create(@RequestBody T entity) {
        return this.service.createOrUpdate(entity);
    }

    @ApiOperation("Atualiza uma nova entidade")
    @PutMapping()
    public T update(@RequestBody T entity) {
        return this.service.createOrUpdate(entity);
    }

    @ApiOperation("Deleta um novo registro")
    @DeleteMapping(value = "{id}")
    public void delete(@PathVariable(value = "id") long id) {
        this.service.delete(id);
    }

    @ApiOperation("Busca uma entidade pela sua identificação única")
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public Optional<T> get(@PathVariable(value = "id") long id) {
        return this.service.findById(id);
    }

    @ApiOperation("Conta as ocorrencias de uma entidade")
    @RequestMapping(path = "count", method = RequestMethod.GET)
    public Long count() {
        return this.service.count();
    }
}
