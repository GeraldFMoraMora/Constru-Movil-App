package basedatos;

import android.provider.BaseColumns;

import java.sql.Date;

/**
 * Created by GeraldF on 06/11/2016.
 * Clase que contiene las constantes equivalentes a los nombres que van a tomar algunas de las tablas
 * y columnas de una entidad en especifico.
 *
 */
public class ModeloObjUsuario implements BaseColumns{
    public static final String USER="Usuario";
    public static final String CONTRASEÑA="Contrasena";
    public static final String CEDULA="Cedula";
    public static final String NOMBRE="Nombre";
    public static final String P_APELLIDO="P_Apellido";
    public static final String S_APELLIDO="S_Apellido";
    public static final String F_NACIMIENTO="F_Nacimiento";
    public static final String TELEFONO="Telefono";
    public static final String PROVINCIA="Provincia";
    public static final String CANTON="Canton";
    public static final String DISTRITO="Distrito";
}
