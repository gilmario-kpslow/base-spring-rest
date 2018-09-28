/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gilmariosoftware.usuario;

import br.com.gilmariosoftware.generic.GenericResource;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author gilmario
 */
@RestController
@RequestMapping(value = "usuario")
public class UsuarioResource extends GenericResource<Usuario, UsuarioService> {

    @ApiOperation("Retorna uma lista com todas as informações")
    @GetMapping(value = "username")
    public Usuario get(@RequestParam("username") String username) {
        return this.service.findByUsername(username);
    }
}
