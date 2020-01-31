package com.nisum.registro.bsd;

import com.nisum.registro.dto.Usuario;

import java.util.List;

public interface BSDUsuarioInterface {
    
    public List<Usuario> findAll();

    public Usuario save(Usuario user) throws IllegalAccessException;

    public Usuario findById(Integer id);

    public void delete(Integer id);

    public List<Usuario> findByCriteria(Usuario filtro);
}
