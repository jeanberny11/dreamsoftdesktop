package com.dscomponent;
/**
 * <h1>Interface Base</h1>
 * Este es el interface base para todos los componentes personalizados a usar en el sistema
 *
 * @author  Jean Berny Gay
 * @version 1.0
 * @since   2020-10-12
 */
public interface DsBaseComponent<T> {
    /**
     * Este procedimiento es para verificar si el componente debe ser validado.
     * @return boolean 'true' si hay que validar el componente.
     */
    boolean isValidar();

    /**
     * Este procedimiento es para verificar si el componente debe ser limpiado.
     * @return boolean 'true' si el componente puede ser limpiado.
     */
    boolean isLimpiar();

    /**
     * Este procedimiento es para verificar si el componente debe ser guardado.
     * @return boolean 'true' si hay que guardar los datis del componente.
     */
    boolean isSalvar();

    /**
     * Este procedimiento es para verificar si el componente debe ser activado.
     * @return boolean 'true' si hay que activar el componente.
     */
    boolean isActivar();

    /**
     * Nombre del campo donde se debe guardar la informacion del componente
     * @return Devuelve un string con el nonbre del campo de la base de datos al que referencia el componente.
     */
    String getFieldName();

    /**
     * .
     * @return Devuelve un valor con la info del componente.
     */
    T getValor();

    /**
     * Asigna un valor al componente
     * @param t el valor a asignar
     */
    void setValor(T t);

    /**
     * Este procedimiento verifica si el componente esta vacio.
     * @return Devuelve boolean 'true' si el componente esta vacio.
     */
    boolean isEmpty();

    /**
     * Este procedimiento verifica si el valor que tiene el componente es valido para guardar.
     * @return Devuelve boolean 'true' si el valor es valido.
     */
    boolean validar();

    /**
     * Devuelve el valor del componente a su valor por default.
     */
    void limpiar();
}
