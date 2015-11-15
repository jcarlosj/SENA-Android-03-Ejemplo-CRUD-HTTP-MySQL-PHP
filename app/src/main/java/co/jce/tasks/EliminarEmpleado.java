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
public class EliminarEmpleado extends AsyncTask<String,Void,String> {

    Context contexto;
    ProgressDialog loading;

    public EliminarEmpleado(Context contexto) {
        this.contexto = contexto;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        loading = ProgressDialog.show( contexto,"Agregando...","Espere por favor...",false,false);
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
        params.put( Configuracion .C_CEDULA, campos[ 0 ] );

        Log .i( "doInBackground", "Cedula    : " + Configuracion .C_CEDULA );

        Log .i( "doInBackground", "URL: " + Configuracion .URL_ELIMINAR_EMPLEADO );
        for( int i = 0; i < campos .length; i++ ) {
            Log .i( "Valores entrada", i + " " + campos[ i ]);
        }

        RequestHandler rh = new RequestHandler();
        String res = rh.sendPostRequest( Configuracion.URL_ELIMINAR_EMPLEADO, params );

        return res;
    }
}
