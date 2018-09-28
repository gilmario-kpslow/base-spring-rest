/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.gilmariosoftware.usuario;

import br.com.gilmariosoftware.generic.GenericRepository;

/**
 *
 * @author gilmario
 */
public interface UsuarioRepository extends GenericRepository<Usuario> {

    Usuario findByUsername(String username);
}
