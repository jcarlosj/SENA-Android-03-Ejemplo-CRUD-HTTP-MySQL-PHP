package co.jce.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import co.jce.androidcrudmysqlphp.R;
import co.jce.estructurasdedatos.Empleado;
import co.jce.holders.EmpleadosHolder;

/**
 * Created by jcarlosj on 12/11/15.
 */
public class EmpleadosAdapter extends ArrayAdapter<Empleado> {

    //-> Atributos (Comunes)
    private ArrayList alEmpleados;

    //-> Atributos (Personalizados)
    private EmpleadosHolder empleadosHolder;
    private Empleado empleadoPosicion;

    //-> Atributos (Especiales)
    private Context cContexto;


    //-> Define los componentes
    private TextView tvCedula,
                     tvNombres,
                     tvApellidos;

    public EmpleadosAdapter( Context contexto, ArrayList<Empleado> empleados ) {
        super( contexto, 0, empleados );     //: super( contexto, recurso, coleccion de objetos );
        this .cContexto = contexto;
        this .alEmpleados = empleados;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        //-> Accedemos al objeto "Animal" contenido en el indice indicado del ArrayList
        this .empleadoPosicion = ( Empleado ) this .alEmpleados .get( position );

//-> Valida si el "View" del "item" del "ListView" NO existe.
        //   En caso de NO existir infla la "View" para el "layout" del "item" nuevo para el "ListView"
        if( convertView == null ) {

            //-> Inflamos la vista del "layout" creado para el despliegue de cada "item" del "ListView" y lo asignamos a la vista.
            convertView = LayoutInflater .from( this .cContexto ) .inflate( R .layout .list_item_empleados, null );

            //-> Instanciamos el "holder" para mantener los datos.
            this .empleadosHolder = new EmpleadosHolder();

            //-> Accedemos a los componentes de "list_item_animals.xml"
            //   y se los asignamos al "Holder" para mantenerlos disponibles
            //   Este será el contenedor de las referencias a los componentes de "list_item_animals.xml"
            this .empleadosHolder .setEtCedula( ( TextView ) convertView .findViewById( R .id .tvCedula ) );
            this .empleadosHolder .setEtNombres( ( TextView ) convertView .findViewById(R.id.tvNombres) );
            this .empleadosHolder .setEtApellidos( ( TextView ) convertView .findViewById(R.id.tvApellidos) );
            convertView .setTag( this .empleadosHolder );    //: Guardamos el "Holder" dentro del "View" para luego poder recuperarlo
        }

        //-> Reasignamos la referencia de los componentes contenidos en el "View" al "Holder"
        this .empleadosHolder = ( EmpleadosHolder ) convertView .getTag();

        //-> Asignamos los valores al los componentes del "list_item_animals.xml" contenidos en el "Holder"
        this .empleadosHolder .getEtCedula() .setText( empleadoPosicion .getvCedula() );
        this .empleadosHolder .getEtNombres() .setText( empleadoPosicion.getvNombres() );
        this .empleadosHolder .getEtApellidos() .setText( empleadoPosicion .getvApellidos() );

        return convertView;
    }
}
