package co.jce.androidcrudmysqlphp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    //-> Define los componentes
    private EditText etNombres,
                     etApellidos,
                     etCedula,
                     etCargo,
                     etCorreoElectronico;

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
    }
}
