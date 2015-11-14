package co.jce.tasks;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import co.jce.androidcrudmysqlphp.Configuracion;
import co.jce.webservices.RequestHandler;

/**
 * Created by jcarlosj on 11/11/15.
 */
public class ListarEmpleados extends AsyncTask<Void,Void,String> {

    Context contexto;
    ProgressDialog loading;

    public ListarEmpleados(Context contexto) {
        this.contexto = contexto;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        loading = ProgressDialog.show( contexto,"Cargando datos...","Espere por favor...",false,false);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        loading.dismiss();
        Log .i( "onPostExecute", "Retorna > " + s );
        Toast.makeText( contexto, "< " + s + " >", Toast.LENGTH_LONG ).show();
    }

    @Override
    protected String doInBackground( Void... campos ) {

        Log .i( "doInBackground", "URL: " + Configuracion .URL_MOSTRAR_EMPLEADOS );

        RequestHandler rh = new RequestHandler();
        String res = rh.sendGetRequest( Configuracion.URL_MOSTRAR_EMPLEADOS );

        return res;
    }
}
