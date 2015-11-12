package co.jce.androidcrudmysqlphp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by jcarlosj on 11/11/15.
 */
public class ListarEmpleadosActivity extends AppCompatActivity {

    //-> Atributos (Comunes)
    private ArrayList<String[]> alEmpleados;

    //-> Define los componentes
    private ListView lvEmpleados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_empleados);
        init();
    }

    private void init() {

        //-> Accedemos a los componentes del "Activity".
        lvEmpleados = ( ListView ) findViewById( R .id .lvEmpleados );        //: ListView

    }

}
