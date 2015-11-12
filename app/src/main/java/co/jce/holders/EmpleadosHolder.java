package co.jce.holders;

import android.widget.EditText;

/**
 * Created by jcarlosj on 12/11/15.
 */
public class EmpleadosHolder {

    //-> Define los componentes
    private EditText etNombres,
                     etApellidos,
                     etCedula,
                     etCargo,
                     etCorreo;

    //-> Getters & Setters


    public EditText getEtNombres() {
        return etNombres;
    }

    public void setEtNombres( EditText etNombres ) {
        this .etNombres = etNombres;
    }

    public EditText getEtApellidos() {
        return etApellidos;
    }

    public void setEtApellidos( EditText etApellidos ) {
        this .etApellidos = etApellidos;
    }

    public EditText getEtCedula() {
        return etCedula;
    }

    public void setEtCedula( EditText etCedula ) {
        this .etCedula = etCedula;
    }

    public EditText getEtCargo() {
        return etCargo;
    }

    public void setEtCargo( EditText etCargo ) {
        this .etCargo = etCargo;
    }

    public EditText getEtCorreo() {
        return etCorreo;
    }

    public void setEtCorreo( EditText etCorreo ) {
        this .etCorreo = etCorreo;
    }

}
