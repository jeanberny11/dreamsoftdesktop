package com.dreamsoft.models;

import com.dsdata.entities.Usuarios;
import javafx.beans.property.*;

public class UsuarioModel {
    private final IntegerProperty usuarioid=new SimpleIntegerProperty(this,"usuarioid",0);
    private final StringProperty usuario=new SimpleStringProperty(this,"usuario","");
    private final StringProperty clave=new SimpleStringProperty(this,"clave","");
    private final StringProperty nombre=new SimpleStringProperty(this,"nombre","");
    private final StringProperty direccion=new SimpleStringProperty(this,"direccion","");
    private final StringProperty telefono=new SimpleStringProperty(this,"telefono","");
    private final StringProperty email=new SimpleStringProperty(this,"email","");
    private final IntegerProperty levelid=new SimpleIntegerProperty(this,"levelid",0);
    private final IntegerProperty perfilid=new SimpleIntegerProperty(this,"perfilid",0);
    private final BooleanProperty activo=new SimpleBooleanProperty(this,"activo",false);

    public UsuarioModel(){

    }

    public UsuarioModel(int usuarioid, String usuario, String clave, String nombre, String direccion, String telefono, String email, int levelid, int perfilid, boolean activo) {
        this.usuarioid.set(usuarioid);
        this.usuario.set(usuario);
        this.clave.set(clave);
        this.nombre.set(nombre);
        this.direccion.set(direccion);
        this.telefono.set(telefono);
        this.email.set(email);
        this.levelid.set(levelid);
        this.perfilid.set(perfilid);
        this.activo.set(activo);
    }

    public UsuarioModel(Usuarios user) {
        this(user.getUsuarioid(),user.getUsuario(),user.getClave(),user.getNombre(),user.getDireccion(),user.getTelefono(),user.getEmail(),user.getUsuariolevels().getLevelid(),user.getPerfilUsuarios().getPerfilid(),user.isActivo());
    }

    public int getUsuarioid() {
        return usuarioid.get();
    }

    public IntegerProperty usuarioidProperty() {
        return usuarioid;
    }

    public void setUsuarioid(int usuarioid) {
        this.usuarioid.set(usuarioid);
    }

    public String getUsuario() {
        return usuario.get();
    }

    public StringProperty usuarioProperty() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario.set(usuario);
    }

    public String getClave() {
        return clave.get();
    }

    public StringProperty claveProperty() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave.set(clave);
    }

    public String getNombre() {
        return nombre.get();
    }

    public StringProperty nombreProperty() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getDireccion() {
        return direccion.get();
    }

    public StringProperty direccionProperty() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }

    public String getTelefono() {
        return telefono.get();
    }

    public StringProperty telefonoProperty() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono.set(telefono);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public int getLevelid() {
        return levelid.get();
    }

    public IntegerProperty levelidProperty() {
        return levelid;
    }

    public void setLevelid(int levelid) {
        this.levelid.set(levelid);
    }

    public int getPerfilid() {
        return perfilid.get();
    }

    public IntegerProperty perfilidProperty() {
        return perfilid;
    }

    public void setPerfilid(int perfilid) {
        this.perfilid.set(perfilid);
    }

    public boolean isActivo() {
        return activo.get();
    }

    public BooleanProperty activoProperty() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo.set(activo);
    }
}
