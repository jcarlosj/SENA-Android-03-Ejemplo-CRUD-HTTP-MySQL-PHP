package co.jce.androidcrudmysqlphp;

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
import co.jce.tasks.ListarEmpleados;

/**
 * Created by jcarlosj on 11/11/15.
 */
public class ListarEmpleadosActivity extends AppCompatActivity {

    //-> Atributos (Comunes)
    private ArrayList<Empleado> alEmpleado;
    private ArrayList<String[]> alEmpleados;
    private EmpleadosAdapter adaptadorEmpleados;

    //-> Define los componentes
    private ListView lvEmpleados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_empleados);
        init();
    }

    private ArrayList<Empleado> mostrarEmpleados() {

        String salida = null;
        String aDatos[];

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

        //-> Convierte una cadena a un ArrayList<String[]>
        alEmpleados = Convertir .cadenaEnArrayListDeArray(salida);

        //-> Convierte un ArrayList<String[]> a un ArrayList<Empleado>
        alEmpleado = Convertir .arrayListDeArrayEnArrayListEmpleado( alEmpleados );

        return alEmpleado;
    }

    private void init() {

        //-> Accedemos a los componentes del "Activity".
        lvEmpleados = ( ListView ) findViewById( R .id .lvEmpleados );        //: ListView

        mostrarEmpleados();

        //-> Instanciamos el Adaptador.
        //   Asociamos el "Adapter" al "ArrayList".
        adaptadorEmpleados = new EmpleadosAdapter( getApplicationContext(), alEmpleado );

        //-> Asociamos el "Adapter" con el "ListView".
        lvEmpleados .setAdapter( adaptadorEmpleados );

        //-> Agregamos el escuchador al "ListView"
        lvEmpleados .setOnItemClickListener( new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast .makeText( getApplicationContext(), "Seleccionaste " + position, Toast .LENGTH_SHORT ) .show();
            }
        });
        
    }

}
