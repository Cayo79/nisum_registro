package com.nisum.registro.bsd.impl;

import com.nisum.registro.bsd.BSDUsuarioInterface;
import com.nisum.registro.dto.Usuario;
import com.nisum.registro.jwt.Token;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class BSDUsuario implements BSDUsuarioInterface {
    private static final Logger LOG = LoggerFactory.getLogger(BSDUsuario.class);
    private static final int TOLIVE = 800000;

    Pattern clave = Pattern
            .compile("^[A-Z]{1,1}[a-z]+([0-9]{2})$");
    Pattern pattern = Pattern
            .compile("^[a-z]+@"
                    + "[a-z0-9]+(\\.[a-z]{2,})$");
    /*
    .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
     */

    @Autowired
    Token token;

    protected List<Usuario> listUsuario = new ArrayList<>();
    private Integer id = 0;

    @Override
    public List<Usuario> findAll() {
        return listUsuario;
    }

    @Override
    public Usuario save(Usuario user) throws IllegalAccessException {
        Usuario newUser = new Usuario();
        Usuario usuarioBusca = new Usuario();
        if (validarCorreo(user)) {
            if (validarClave(user)) {
                usuarioBusca.setEmail(user.getEmail());
                if (findByCriteria(usuarioBusca).size() == 0) {
                    this.id += 1;
                    newUser = new Usuario(this.id, user.getName(), user.getEmail(), user.getPassword(), user.getPhones(), "1234");
                    // Agregar Token
                    newUser.setToken(token.createJWT(newUser.getEmail(), newUser.getPassword(), newUser.getName(), TOLIVE));
                    listUsuario.add(newUser);
                } else {
                    throw new IllegalAccessException("El correo ya registrado");
                }
            } else {
                throw new IllegalAccessException("La clave no posee el formato correcto Una Mayuscula + letras minusculas + 2 numeros");
            }
        } else {
            throw new IllegalAccessException("El correo tiene formato invalido");
        }
        return newUser;
    }

    @Override
    public Usuario findById(Integer id) {
        for (int i = 0; i < listUsuario.size(); i++) {
            if (id.equals(listUsuario.get(i).getId())) {
                return listUsuario.get(i);
            }
        }
        return null;
    }

    @Override
    public void delete(Integer id) {
        for (int i = 0; i < listUsuario.size(); i++) {
            if (id.equals(listUsuario.get(i).getId())) {
                listUsuario.remove(i);
                break;
            }
        }
    }

    @Override
    public List<Usuario> findByCriteria(Usuario filtro) {
        List<Usuario> listUsers = new ArrayList<>();
        Usuario usuario;
        for (int i = 0; i < listUsuario.size(); i++) {
            if (filtro.getId() == null || filtro.getId().equals(listUsuario.get(i).getId())) {
                if (filtro.getName() == null || filtro.getName().equals(listUsuario.get(i).getName())) {
                    if (filtro.getIsactive() == null || filtro.getIsactive().equals(listUsuario.get(i).getIsactive())) {
                        if (filtro.getEmail() == null || filtro.getEmail().equals(listUsuario.get(i).getEmail())) {
                            usuario = new Usuario();
                            usuario = listUsuario.get(i);
                            listUsers.add(usuario);
                        }
                    }
                }
            }
        }
        return listUsers;
    }

    private Boolean validarCorreo(Usuario user) {
        Matcher mather = pattern.matcher(user.getEmail());
        return mather.find();
    }

    private Boolean validarClave(Usuario user) {
        Matcher mather = clave.matcher(user.getPassword());
        return mather.find();
    }
}
