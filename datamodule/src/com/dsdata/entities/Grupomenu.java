package com.dsdata.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@NamedNativeQueries(
        value = {
                @NamedNativeQuery(
                        name = "get_grupomenuxperfil",
                        query = "select grupomenu.grupomenuid,grupomenu.nombre,grupomenu.orden,grupomenu.activo,grupomenu.icono " +
                                "    from grupomenu " +
                                "    where grupomenu.activo=true and grupomenu.grupomenuid in " +
                                "    (select m.grupomenuid from menus m,menuperfil u where m.menuid=u.menuid and u.perfilid=:xperfilid and m.moduloid=:xmoduloid group by m.grupomenuid)" +
                                "    order by grupomenu.orden",
                        resultClass = Grupomenu.class
                ),
                @NamedNativeQuery(
                        name = "get_grupomenuxmodulo",
                        query = "select grupomenu.grupomenuid,grupomenu.nombre,grupomenu.orden,grupomenu.activo,grupomenu.icono" +
                                "    from grupomenu " +
                                "    where grupomenu.activo=true and grupomenu.grupomenuid in " +
                                "    (select m.grupomenuid from menus m,menuperfil u where m.menuid=u.menuid and m.moduloid=:xmoduloid group by m.grupomenuid)" +
                                "    order by grupomenu.orden",
                        resultClass = Grupomenu.class
                )
        }
)
@Table(name = "grupomenu")
public class Grupomenu implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int grupomenuid;
    @Column
    private String nombre;
    @Column
    private int orden;
    @Column
    private boolean activo;
    @Column
    private String icono;

    public Grupomenu() {
    }

    public Grupomenu(int grupomenuid, String nombre, int orden, boolean activo, String icono) {
        this.grupomenuid = grupomenuid;
        this.nombre = nombre;
        this.orden = orden;
        this.activo = activo;
        this.icono = icono;
    }

    public int getGrupomenuid() {
        return grupomenuid;
    }

    public void setGrupomenuid(int grupomenuid) {
        this.grupomenuid = grupomenuid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getOrden() {
        return orden;
    }

    public void setOrden(int orden) {
        this.orden = orden;
    }

    public boolean getActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }
}
