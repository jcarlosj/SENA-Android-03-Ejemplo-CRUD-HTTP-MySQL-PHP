package co.jce.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.util.HashMap;

import co.jce.androidcrudmysqlphp.Configuracion;
import co.jce.webservices.RequestHandler;

/**
 * Created by jcarlosj on 11/11/15.
 */
public class EditarEmpleado extends AsyncTask<String,Void,String> {

    Context contexto;
    ProgressDialog loading;

    public EditarEmpleado(Context contexto) {
        this.contexto = contexto;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        loading = ProgressDialog.show( contexto,"Modificando...","Espere por favor...",false,false);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        loading.dismiss();
        Log .i( "onPostExecute", "Retorna > " + s );
        Toast.makeText( contexto, "< " + s + " >", Toast.LENGTH_LONG).show();
    }

    @Override
    protected String doInBackground( String... campos ) {

        HashMap<String,String> params = new HashMap<>();
        params.put( Configuracion.C_NOMBRES,   campos[ 0 ].toString() );
        params.put( Configuracion.C_APELLIDOS, campos[ 1 ].toString() );
        params.put( Configuracion.C_CEDULA,    campos[ 2 ].toString() );
        params.put( Configuracion.C_CARGO,     campos[ 3 ].toString() );
        params.put( Configuracion.C_CORREO,    campos[ 4 ].toString() );

        Log .i( "doInBackground", "Nombres   : " + Configuracion.C_NOMBRES );
        Log .i( "doInBackground", "Apellidos : " + Configuracion.C_APELLIDOS );
        Log .i( "doInBackground", "Cedula    : " + Configuracion.C_CEDULA );
        Log .i( "doInBackground", "Cargo     : " + Configuracion.C_CARGO );
        Log .i( "doInBackground", "Correo    : " + Configuracion.C_CORREO );


        Log .i( "doInBackground", "URL: " + Configuracion .URL_ACTUALIZAR_EMPLEADO );
        for( int i = 0; i < campos .length; i++ ) {
            Log .i( "Valores entrada", i + " " + campos[ i ]);
        }

        RequestHandler rh = new RequestHandler();
        String res = rh.sendPostRequest( Configuracion.URL_ACTUALIZAR_EMPLEADO, params );

        return res;
    }
}
