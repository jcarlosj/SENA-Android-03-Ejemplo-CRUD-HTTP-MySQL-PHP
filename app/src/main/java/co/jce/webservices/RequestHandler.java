package co.jce.webservices;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by jcarlosj on 9/11/15.
 */
public class RequestHandler {

    /*-> Método para enviar peticiones HTTP vía POST
    *    Parametros( URL de la solicitud, HashMap valores enviados ) */
    public String sendPostRequest(String requestURL, HashMap<String, String> postDataParams) {
        //-> Crea una variable de tipo URL
        URL url;

        //-> StringBuilder almacena el mensaje recuperado en el servidor
        StringBuilder sb = new StringBuilder();
        try {
            //-> Instancia Url
            url = new URL(requestURL);

            //-> Crea una conexión HttpURLConnection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //-> Configura propiedades de la conexión
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);

            //-> Crea el un flujo de salida
            OutputStream os = conn.getOutputStream();

            //-> Escribe parámetros en la solicitud
            BufferedWriter writer = new BufferedWriter( new OutputStreamWriter(os, "UTF-8") );
            writer.write(getPostDataString(postDataParams));    //: Utiliza getPostDataString para crear
                                                                //: cadena de valores para la petición
            Log .i("URL: ", getPostDataString(postDataParams));

            writer.flush();
            writer.close();
            os.close();

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {

                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                sb = new StringBuilder();
                String response;
                //Reading server response
                while ((response = br.readLine()) != null){
                    sb.append(response);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    /*-> Método para enviar peticiones HTTP vía GET
    *    Parametros( URL de la solicitud ) */
    public String sendGetRequest(String requestURL){
        StringBuilder sb =new StringBuilder();
        try {
            URL url = new URL(requestURL);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String s;
            while((s=bufferedReader.readLine())!=null){
                sb.append(s+"\n");
            }
        }catch(Exception e){
        }
        return sb.toString();
    }

    /*-> Método para enviar peticiones HTTP vía GET
    *    Parametros( URL de la solicitud, ID Registro ) */
    public String sendGetRequestParam(String requestURL, String id){
        StringBuilder sb =new StringBuilder();
        try {
            URL url = new URL(requestURL+id);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String s;
            while((s=bufferedReader.readLine())!=null){
                sb.append(s+"\n");
            }
        }catch(Exception e){
        }
        return sb.toString();
    }

    /*-> Método para generar cadena de valores para peticiones HTTP vía POST
    *    Parametros( HashMap valores enviados ) */
    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }

}
