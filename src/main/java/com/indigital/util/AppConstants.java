package com.indigital.util;

import lombok.experimental.UtilityClass;

/**
 * la clase AppConstants es una clase de utilidad que contiene constantes de la aplicación.
 * @UtilityClass: Anotación de Lombok que permite crear una clase con métodos estáticos, sin necesidad de instanciar la clase.
 */
@UtilityClass
public class AppConstants {


    // CLIENTS ERRORS
    public static final String BAD_REQUEST = "P-404";
    public static final String BAD_REQUEST_CLIENT = "Client no encontrado con el ID: ";

    //MESSAGE CONTROLLER
    public static final String SUCCESS = "SUCCESS";
    public static final String MESSAGE_ID_CLIENT = "CLIENT ID: ";


    // =============================================================================================
    // CONSTANTES DE PAGINATION
    // =============================================================================================
    public static final String NUMERO_DE_PAGINA_POR_DEFECTO = "1";
    public static final String MEDIDA_DE_PAGINA_POR_DEFECTO = "10";
    public static final String ORDENAR_POR_DEFECTO = "id";
    public static final String ORDENAR_DIRECCION_POR_DEFECTO = "asc";

}
