package co.jce.androidcrudmysqlphp;

/**
 * Created by jcarlosj on 9/11/15.
 */
public class Configuracion {

    //-> Enlaces de los scripts
    public static final String URL_AGREGAR_EMPLEADO    = "http://192.168.0.17/simplefieldcoding/empleados/insertar.php"; //: http://android-testing.webcindario.com/empleados/insertar.php
    public static final String URL_MOSTRAR_EMPLEADOS   = "http://192.168.0.17/simplefieldcoding/empleados/listar_empleados.php";
    public static final String URL_MOSTRAR_EMPLEADO    = "http://192.168.0.17/simplefieldcoding/empleados/empleado.php?id=";
    public static final String URL_ACTUALIZAR_EMPLEADO = "http://192.168.0.17/simplefieldcoding/empleados/editar.php";
    public static final String URL_ELIMINAR_EMPLEADO   = "http://192.168.0.17/simplefieldcoding/empleados/eliminar.php?id=";

    //-> Campos que serán usados en las solicitudes (campos de la tabla).
    public static final String C_ID        = "id_empleado";
    public static final String C_NOMBRES   = "nombres";
    public static final String C_APELLIDOS = "apellidos";
    public static final String C_CEDULA    = "cedula";
    public static final String C_CARGO     = "cargo";
    public static final String C_CORREO    = "correo";

    //-> Identificación para solicitar información del empleado
    public static final String EMPLEADO_ID = "id";

}
