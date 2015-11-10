package co.jce.androidcrudmysqlphp;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

import co.jce.manejadorpeticiones.RequestHandler;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //-> Define los componentes
    private EditText etNombres,
                     etApellidos,
                     etCedula,
                     etCargo,
                     etCorreoElectronico;
    private Button btnAgregar,
                   btnMostrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }
    
    private void init() {
        //-> Accedemos a los compomentes del Activity
        etNombres           = ( EditText ) findViewById( R .id .etNombres );
        etApellidos         = ( EditText ) findViewById( R .id .etApellidos );
        etCedula            = ( EditText ) findViewById( R .id .etCedula );
        etCargo             = ( EditText ) findViewById( R .id .etCargo );
        etCorreoElectronico = ( EditText ) findViewById( R .id .etCorreoElectronico);

        btnAgregar = ( Button ) findViewById( R .id .btnAgregar );
        btnMostrar = ( Button ) findViewById( R .id .btnMostrar );

        //-> Agregamos los escuchadores a los componentes Button
        btnAgregar .setOnClickListener( this );
        btnMostrar .setOnClickListener( this );

    }

    //-> Agregar empleado
    private void agregarEmpleado() {

        final String vNombres           = etNombres .getText() .toString() .trim(),
                     vApellidos         = etApellidos .getText() .toString() .trim(),
                     vCedula            = etCedula .getText() .toString() .trim(),
                     vCargo             = etCargo .getText() .toString() .trim(),
                     vCorreoElectronico = etCorreoElectronico .getText() .toString() .trim();

        class AgregarEmpleado extends AsyncTask<Void,Void,String> {

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this,"Agregando...","Espere por favor...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {

                HashMap<String,String> params = new HashMap<>();
                params.put( Configuracion.C_NOMBRES, vNombres );
                params.put( Configuracion.C_APELLIDOS, vApellidos );
                params.put( Configuracion.C_CEDULA, vCedula );
                params.put( Configuracion.C_CARGO, vCargo );
                params.put( Configuracion.C_CARGO, vCorreoElectronico );

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest( Configuracion.URL_AGREGAR_EMPLEADO, params);
                return res;
            }
        }

        AgregarEmpleado ae = new AgregarEmpleado();
        ae.execute();

    }

    @Override
    public void onClick(View v) {
        if( v .getId() == R .id .btnAgregar ) {
            agregarEmpleado();
        }
        if( v .getId() == R .id .btnMostrar ) {
            // Ir a ver el listado de empleados.
        }
    }
}
