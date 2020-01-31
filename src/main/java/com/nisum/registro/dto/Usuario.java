package com.nisum.registro.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Usuario implements Serializable {
    private static final long serialVersionUID = -7835460012124994497L;

    private Integer id;
    private String name;
    private String email;
    private String password;
    private ArrayList<Telefono> phones;
    private Date created;
    private Date modified;
    private Date last_login;
    private Boolean isactive;
    private String token;

    public Usuario(Integer id, String name, String email, String password, ArrayList<Telefono> listTel, String token) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phones = listTel;
        this.token = token;

        this.created = new Date();
        this.modified = new Date();
        this.isactive = true;
    }

    public Usuario() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Telefono> getPhones() {
        return phones;
    }

    public void setPhones(ArrayList<Telefono> phones) {
        this.phones = phones;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

    public Date getLast_login() {
        return last_login;
    }

    public void setLast_login(Date last_login) {
        this.last_login = last_login;
    }

    public Boolean getIsactive() {
        return isactive;
    }

    public void setIsactive(Boolean isactive) {
        this.isactive = isactive;
    }
}
