package basedatos;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by GeraldF on 06/11/2016.
 * Esta clase se encargará de crear las tablas por medio de las clases con informacion ya suministrada,
 * ademas de que podra utilizarse para hacer el llenado de cada una de ellas asi como su consulta.
 *
 */
public class BaseDatosSecundaria extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="manejodatos15.db";


    /**
     * METODO CONSTRUCTOR DE LA CLASE
     * @param context
     */
    public BaseDatosSecundaria(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    /**
     *
     * @param db
     *
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE DATOS("+
                "Id"+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "Producto"+" VARCHAR(40),"+
                "Vendedor"+" VARCHAR(40),"+
                "Id_Producto"+" VARCHAR(40),"+
                "Precio"+" VARCHAR(40),"+
                "Cantidad"+" VARCHAR(40)"+
                " ) ");
    }

    /**
     *
     * @param pDatabase
     * @param i
     * @param i1
     */
    @Override
    public void onUpgrade(SQLiteDatabase pDatabase, int i, int i1){
    }

}
