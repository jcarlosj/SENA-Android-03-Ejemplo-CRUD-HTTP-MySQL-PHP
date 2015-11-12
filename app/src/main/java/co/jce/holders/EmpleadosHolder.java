package co.jce.holders;

import android.widget.TextView;

/**
 * Created by jcarlosj on 12/11/15.
 */
public class EmpleadosHolder {

    //-> Define los componentes
    private TextView etNombres,
                     etApellidos,
                     etCedula,
                     etCargo,
                     etCorreo;

    //-> Getters & Setters.

    public TextView getEtNombres() {
        return etNombres;
    }

    public void setEtNombres( TextView etNombres ) {
        this .etNombres = etNombres;
    }

    public TextView getEtApellidos() {
        return etApellidos;
    }

    public void setEtApellidos( TextView etApellidos ) {
        this .etApellidos = etApellidos;
    }

    public TextView getEtCedula() {
        return etCedula;
    }

    public void setEtCedula( TextView etCedula ) {
        this .etCedula = etCedula;
    }

    public TextView getEtCargo() {
        return etCargo;
    }

    public void setEtCargo( TextView etCargo ) {
        this .etCargo = etCargo;
    }

    public TextView getEtCorreo() {
        return etCorreo;
    }

    public void setEtCorreo( TextView etCorreo ) {
        this .etCorreo = etCorreo;
    }
    
}
