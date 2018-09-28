/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gilmariosoftware.usuario;

import br.com.gilmariosoftware.generic.GenericService;

/**
 *
 * @author gilmario
 */
public interface UsuarioService extends GenericService<Usuario, UsuarioRepository> {

    Usuario findByUsername(String username);

}
