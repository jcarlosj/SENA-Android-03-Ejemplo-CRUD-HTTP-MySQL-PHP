package co.jce.androidcrudmysqlphp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import co.jce.adapters.EmpleadosAdapter;
import co.jce.estructurasdedatos.Empleado;
import co.jce.operaciones.Convertir;
import co.jce.tasks.EliminarEmpleado;
import co.jce.tasks.ListarEmpleados;

/**
 * Created by jcarlosj on 11/11/15.
 */
public class ListarEmpleadosActivity extends AppCompatActivity {

    //-> Atributos (Comunes)
    private ArrayList<Empleado> alEmpleado;
    private ArrayList<String[]> alEmpleados;
    private EmpleadosAdapter adaptadorEmpleados;

    //-> Atributos (Especiales)
    private AlertDialog .Builder adbVentana;
    private Dialog dialog;
    private Bundle bundle;
    private Intent in;

    //-> Define los componentes
    private ListView lvEmpleados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this .bundle = savedInstanceState;
        setContentView(R.layout.activity_listar_empleados);
        init();
    }

    private ArrayList<Empleado> mostrarEmpleados() {

        String salida = null;
        alEmpleado = null;

        //-> Ejecuto mi Tarea Asincrona ListarEmpleados y le paso el parámetro
        ListarEmpleados cadena = (ListarEmpleados) new ListarEmpleados( this ) .execute();

        try {
            salida = cadena.get();          //: Obtengo el valor de retorno de mi tarea.
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Log.i("ListarEmpleadosActivity", "Recibe: " + salida);

        //-> Validamos si la cadena de retorno esta vacia.
        if( salida != "" ) {
            //-> Convierte una cadena a un ArrayList<String[]>
            alEmpleados = Convertir .cadenaEnArrayListDeArray(salida);

            //-> Convierte un ArrayList<String[]> a un ArrayList<Empleado>
            alEmpleado = Convertir .arrayListDeArrayEnArrayListEmpleado( alEmpleados );
        }

        return alEmpleado;
    }

    private void init() {

        ArrayList<Empleado> existenRegistros = null;

        //-> Accedemos a los componentes del "Activity".
        lvEmpleados = ( ListView ) findViewById( R .id .lvEmpleados );        //: ListView

        if( mostrarEmpleados() == null ) {
            Toast .makeText( this, "No hay registros", Toast .LENGTH_SHORT ) .show();
        }
        else {
            mostrarEmpleados();

            //-> Instanciamos el Adaptador.
            //   Asociamos el "Adapter" al "ArrayList".
            adaptadorEmpleados = new EmpleadosAdapter( getApplicationContext(), alEmpleado );

            //-> Asociamos el "Adapter" con el "ListView".
            lvEmpleados .setAdapter( adaptadorEmpleados );
        }

        //-> Agregamos el escuchador al "ListView"
        lvEmpleados .setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                lanzaVentanaDeDialogo( position );
            }
        });

    }

    private void lanzaVentanaDeDialogo( final int posicion ) {

        //-> Se instancia (crea) la ventana de dialogo
        adbVentana = new AlertDialog.Builder( this );

        //-> Se dan características a la ventana de dialogo una vez creada.
        adbVentana .setTitle(R.string.opciones)
                   .setItems(R.array.opciones, new DialogInterface.OnClickListener() {
                       public void onClick(DialogInterface dialog, int which) {
                           // The 'which' argument contains the index position
                           // of the selected item
                           if (which == 0) {
                               /*-> A través de un Intent enviamos los datos al Activity donde esta el formulario.
                                    En este caso se pretende reutilizar el MainActivity para insertar y editar   */
                               in = new Intent(getApplicationContext(), MainActivity.class);
                               in .putExtra( "nombres",   alEmpleado.get( posicion ) .getvNombres() );
                               in .putExtra( "apellidos", alEmpleado.get( posicion ) .getvApellidos() );
                               in .putExtra( "cedula",    alEmpleado.get( posicion ) .getvCedula() );
                               in .putExtra( "cargo",     alEmpleado.get( posicion ) .getvCargo() );
                               in .putExtra("correo", alEmpleado.get(posicion).getvCorreo());
                               startActivity(in);
                               finish();
                               //-(T) Toast.makeText(getApplicationContext(), "Editar.", Toast.LENGTH_LONG).show();
                           }
                           if (which == 1) {
                               lanzarVentanaEliminar( alEmpleado.get( posicion ) .getvCedula() );
                           }
                           if (which == 2) {
                           }
                       }
                   });

        //-> Se crea y muestra la ventana.
        dialog = adbVentana .create();
        dialog .show();
    }

    private void lanzarVentanaEliminar( final String cedula ) {

        //-> Se instancia (crea) la ventana de dialogo
        adbVentana = new AlertDialog.Builder( this );

        //-> Se dan características a la ventana de dialogo una vez creada.
        adbVentana .setTitle( R .string .eliminar )
                    .setMessage( R .string .mensaje_eliminar )
                    .setPositiveButton( getResources().getString(R .string .eliminar ), new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which) {
                        eliminarEmpleado( cedula );
                        }

                    })
                            //-> Acciones para el botón CANCELAR
                .setNeutralButton( getResources() .getString( R .string .cancelar ), new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int which) {
                        //-(T) Toast .makeText( getApplicationContext(), "No enviaría nada.", Toast .LENGTH_LONG ) .show();
                    }

                });


        //-> Se crea y muestra la ventana.
        dialog = adbVentana .create();
        dialog .show();
    }

    private void eliminarEmpleado( final String cedula ) {

        //-> Ejecuto mi Tarea Asincrona ListarEmpleados y le paso el parámetro
        EliminarEmpleado cadena = (EliminarEmpleado) new EliminarEmpleado( this ) .execute( cedula );

        //-> Actualizamos el "ListView"
        startActivity( new Intent( this, ListarEmpleadosActivity.class ) );
        finish();
    }

}
