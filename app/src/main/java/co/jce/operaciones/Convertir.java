package co.jce.operaciones;

import android.util.Log;

import java.util.ArrayList;

import co.jce.estructurasdedatos.Empleado;

/**
 * Created by jcarlosj on 11/11/15.
 */
public class Convertir {

    public static ArrayList<String[]> cadenaEnArrayListDeArray( String datos ) {

        //-> Segmenta y extrae cada registro y lo agrega a un "Array" de tipo "String"
        int agrega = 0;                         //: Contando veces que se registro en el ArrayList
        String[] registro, campos = null;
        ArrayList alDatos;
        alDatos = new ArrayList<String[]>();

        //-> Divide cada que encuentra un :: (Siempre genera una división adicional)
        registro = datos.split("::");       //: El último registro no trae nada, por eso se reduce en 1.

        //-> Recorre el "Array" registro
        for( int i = 0; i < registro .length - 1; i++ ) {           //: Reducimos en uno el tamaño del Array.
            /*-> Segmenta y extrae cada campo de cada uno de los registros
                 y lo agrega a un "Array" de tipo "String"                  */
            campos = registro[ i ] .split(",");
            //-(T) System .out .println( "Array (registro): " + registro[ i ] );
            alDatos .add( campos );
            agrega++;
        }
        Log .i( "alDatos", "agrego " + agrega + " registros" );

        //-> Imprime "Array" campos para verificar su contenido (PRUEBA)
        for( int i = 0; i < campos .length; i++ ) {
            System .out.println("Convertir campos[" + i + "]: " + campos[ i ] );
        }

        return alDatos;
    }

    public static String[] arrayListDeArrayEnArray( ArrayList<String[]> datos, int filtro ) {

        String aDatos[] = new String[ datos.size() ];

        Log .i( "Longitud", "filas    :" + datos.size() );
        Log .i( "Longitud", "columnas :" + datos.get(0) .length );

        for( int i = 0; i < datos.size(); i++ ) {
            for( int j = 0; j < datos.get( 0 ).length; j++ ) {
                //System .out .println( datos.get( i )[ j ] );
                aDatos[ i ] = datos.get( i )[ filtro ];

            }
        }

        return aDatos;
    }

    public static ArrayList<Empleado> arrayListDeArrayEnArrayListEmpleado( ArrayList<String[]> datos ) {

        ArrayList<Empleado> empleados = new ArrayList<Empleado>();
        Empleado empleado;

        Log .i( "Registros", " :" + datos.size() );

        for( int i = 0; i < datos.size(); i++ ) {
            empleados .add( new Empleado( datos.get( i )[ 1 ], datos.get( i )[ 2 ], datos.get( i )[ 3 ], datos.get( i )[ 4 ], datos.get( i )[ 5 ] ) );
        }

        return empleados;
    }

}
