package co.jce.estructurasdedatos;

/**
 * Created by jcarlosj on 12/11/15.
 */
public class Empleado {

    //-> Atributos (Comunes)
    private int id_empleado;
    private String vNombres,
                   vApellidos,
                   vCedula,
                   vCargo,
                   vCorreo;

    //-> Constructor
    public Empleado( String vNombres, String vApellidos, String vCedula, String vCargo, String vCorreo ) {
        this .vNombres   = vNombres;
        this .vApellidos = vApellidos;
        this .vCedula    = vCedula;
        this .vCargo     = vCargo;
        this .vCorreo    = vCorreo;
    }

    //-> Getters & Setters.
    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado( int id_empleado ) {
        this .id_empleado = id_empleado;
    }

    public String getvNombres() {
        return vNombres;
    }

    public void setvNombres( String vNombres ) {
        this .vNombres = vNombres;
    }

    public String getvApellidos() {
        return vApellidos;
    }

    public void setvApellidos( String vApellidos ) {
        this .vApellidos = vApellidos;
    }

    public String getvCedula() {
        return vCedula;
    }

    public void setvCedula( String vCedula ) {
        this .vCedula = vCedula;
    }

    public String getvCargo() {
        return vCargo;
    }

    public void setvCargo( String vCargo ) {
        this .vCargo = vCargo;
    }

    public String getvCorreo() {
        return vCorreo;
    }

    public void setvCorreo( String vCorreo ) {
        this .vCorreo = vCorreo;
    }
}
