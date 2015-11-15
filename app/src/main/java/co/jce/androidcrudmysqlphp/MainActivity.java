package co.jce.androidcrudmysqlphp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import co.jce.tasks.AgregarEmpleado;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //-> Atributos (Comunes)
    private String vNombres,
                   vApellidos,
                   vCedula,
                   vCargo,
                   vCorreo;

    //-> Define los componentes
    private EditText etNombres,
                     etApellidos,
                     etCedula,
                     etCargo,
                     etCorreo;
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
        etNombres   = ( EditText ) findViewById( R .id .etNombres );
        etApellidos = ( EditText ) findViewById( R .id .etApellidos );
        etCedula    = ( EditText ) findViewById( R .id .etCedula );
        etCargo     = ( EditText ) findViewById( R .id .etCargo );
        etCorreo    = ( EditText ) findViewById( R .id .etCorreoElectronico );

        btnAgregar = ( Button ) findViewById( R .id .btnAgregar );
        btnMostrar = ( Button ) findViewById( R .id .btnMostrar );

        //-> Agregamos los escuchadores a los componentes Button
        btnAgregar .setOnClickListener(this);
        btnMostrar .setOnClickListener(this);

        validaExtras();
    }

    private void validaExtras() {
        Log .i( "CEDULA >>> ", String .valueOf( getIntent() .hasExtra("cedula") ) );

        //-> Valida si el "Activity" esta recibiendo valores desde un "Intent"
        if( getIntent() .hasExtra( "cedula" ) ) {

            etNombres .setText( getIntent() .getStringExtra( "nombres" ) );
            etApellidos .setText( getIntent() .getStringExtra("apellidos") );
            etCedula .setText( getIntent() .getStringExtra( "cedula" ) );
            etCargo .setText( getIntent() .getStringExtra( "cargo" ) );
            etCorreo .setText( getIntent() .getStringExtra( "correo" ) );

            Toast .makeText( this, "Recibe datos", Toast .LENGTH_SHORT ) .show();
            Log .i( "Intent", "Recibe datos: " + getIntent() .toString() );
        }
        else {
            Toast .makeText( this, "No recibe datos", Toast .LENGTH_SHORT ) .show();
            Log .i( "Intent", "NO recibe datos: " + this .getIntent() .toString() );
        }
    }

    //-> Agregar empleado
    private void agregarEmpleado() {

        obtenerValores();

        String salida = null;
        //-> Ejecuto mi Tarea Asincrona GetString y le paso el parámetro
        AgregarEmpleado cadena = (AgregarEmpleado) new AgregarEmpleado( this ) .execute(vNombres, vApellidos, vCedula, vCargo, vCorreo);

        try {
            salida = cadena.get();          //: Obtengo el valor de retorno de mi tarea.
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Log.i("MainActivity", "Recibe: " + salida);

    }

    private void obtenerValores() {
        //->
        vNombres   = etNombres .getText() .toString();
        vApellidos = etApellidos .getText() .toString();
        vCedula    = etCedula .getText() .toString();
        vCargo     = etCargo .getText() .toString();
        vCorreo    = etCorreo .getText() .toString();
    }

    @Override
    public void onClick(View v) {
        if( v .getId() == R .id .btnAgregar ) {
            agregarEmpleado();
        }
        if( v .getId() == R .id .btnMostrar ) {
            startActivity( new Intent( this, ListarEmpleadosActivity.class  ) );
        }
    }
}
