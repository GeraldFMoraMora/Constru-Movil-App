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
public class MotorBaseDatos extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="construmovil23.db";

    public ModeloObjUsuario _modeloObjUsuario= new ModeloObjUsuario();
    public ModeloObjSucursal _modeloObjSucursal= new ModeloObjSucursal();
    public ModeloObjRol _modeloObjRol=new ModeloObjRol();
    public ModeloObjCat_Pro _modeloObjCat_Pro= new ModeloObjCat_Pro();
    public ModeloObjCategoria _modeloObjCategoria= new ModeloObjCategoria();
    public ModeloObjCli_Ped _modeloObjCli_ped= new ModeloObjCli_Ped();
    public ModeloObjPedido _modeloObjPedido=new ModeloObjPedido();
    public ModeloObjPro_Ped _modeloObjPro_ped=new ModeloObjPro_Ped();
    public ModeloObjPro_Pro _modeloObjPro_pro=new ModeloObjPro_Pro();
    public ModeloObjRol_Usu _modeloObjRol_usu= new ModeloObjRol_Usu();
    public ModeloObjSuc_Ped _modeloObjSuc_ped=new ModeloObjSuc_Ped();
    public ModeloObjSuc_Pro _modeloObjSuc_pro=new ModeloObjSuc_Pro();
    public ModeloObjProducto _modeloObjProducto=new ModeloObjProducto();
    public ModeloObjVendedor_Pedido _modeloObjVendedor_pedido=new ModeloObjVendedor_Pedido();

    /**
     * METODO CONSTRUCTOR DE LA CLASE
     * @param context
     */
    public MotorBaseDatos(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
    /**
     *
     * @param db
     *
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+"USUARIO"+" ( "+
                this._modeloObjUsuario.USER+" VARCHAR (40) NOT NULL PRIMARY KEY,"+
                this._modeloObjUsuario.CONTRASEÑA+" VARCHAR (20) NOT NULL,"+
                this._modeloObjUsuario.CEDULA+" INTEGER NOT NULL,"+
                this._modeloObjUsuario.NOMBRE+" VARCHAR (40) NULL,"+
                this._modeloObjUsuario.P_APELLIDO+" VARCHAR (40) NOT NULL,"+
                this._modeloObjUsuario.S_APELLIDO+" VARCHAR (40) NOT NULL,"+
                this._modeloObjUsuario.F_NACIMIENTO+" VARCHAR (40) NOT NULL,"+
                this._modeloObjUsuario.TELEFONO+" VARCHAR (10) NULL,"+
                this._modeloObjUsuario.PROVINCIA+" VARCHAR (40) NOT NULL,"+
                this._modeloObjUsuario.CANTON+" VARCHAR (40) NOT NULL,"+
                this._modeloObjUsuario.DISTRITO+" VARCHAR (40) "+" ) ");
        db.execSQL("CREATE TABLE ROL ("+
                this._modeloObjRol.ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                this._modeloObjRol.TIPO+" VARCHAR (40) NOT NULL)");
        db.execSQL("CREATE TABLE PRODUCTO("+
                this._modeloObjProducto.ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                this._modeloObjProducto.NOMBRE+" VARCHAR(40) NOT NULL,"+
                this._modeloObjProducto.DESCRIPCION+" VARCHAR(100) NOT NULL,"+
                this._modeloObjProducto.EXENTO+" BIT NOT NULL,"+
                this._modeloObjProducto.CANTIDAD+" INT NOT NULL DEFAULT 0,"+
                this._modeloObjProducto.PRECIO+" DECIMAL(10,3) NOT NULL DEFAULT 0"+
                ")");
        db.execSQL("CREATE TABLE CATEGORIA("+
                this._modeloObjCategoria.ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                this._modeloObjCategoria.NOMBRE+" VARCHAR(40) NOT NULL,"+
                this._modeloObjCategoria.DESCRIPCION+" VARCHAR(100) NOT NULL"+
                " ) ");
        db.execSQL("CREATE TABLE "+"SUCURSAL"+"("+
                this._modeloObjSucursal.ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                this._modeloObjSucursal.NOMBRE+" VARCHAR (40)"+")");
        db.execSQL("CREATE TABLE PEDIDO("+
                this._modeloObjPedido.ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                this._modeloObjPedido.FECHA+" DATE NOT NULL,"+
                this._modeloObjPedido.HORA+" TIME(0) NOT NULL,"+
                this._modeloObjPedido.TOTAL+" DECIMAL(10,3) DEFAULT 0,"+
                this._modeloObjPedido.ESTADO+" BIT DEFAULT 1"+
                ")");
        db.execSQL("CREATE TABLE CATEGORIA_PRODUCTO("+
                this._modeloObjCat_Pro.ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                this._modeloObjCat_Pro.ID_PRODUCTO+" INT REFERENCES PRODUCTO(Id) NOT NULL,"+
                this._modeloObjCat_Pro.ID_CATEGORIA+" INT REFERENCES CATEGORIA(Id) NOT NULL"+
                ")");
        db.execSQL("CREATE TABLE PROVEEDOR_PRODUCTO("+
                this._modeloObjPro_pro.ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                this._modeloObjPro_pro.ID_PRODUCTO+" INT REFERENCES PRODUCTO(Id) NOT NULL,"+
                this._modeloObjPro_pro.ID_USUARIO+" VARCHAR(40) REFERENCES USUARIO(Usuario) NOT NULL"+
                ")");
        db.execSQL("CREATE TABLE ROL_USUARIO("+
                this._modeloObjRol_usu.ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                this._modeloObjRol_usu.ID_USUARIO+" VARCHAR(40) REFERENCES USUARIO(Usuario) NOT NULL,"+
                this._modeloObjRol_usu.ID_ROL+" INT REFERENCES ROL(Id) NOT NULL"+
                ")");
        db.execSQL("CREATE TABLE CLIENTE_PEDIDO("+
                this._modeloObjCli_ped.ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                this._modeloObjCli_ped.ID_USARIO+" VARCHAR(40) REFERENCES USUARIO(Usuario) NOT NULL,"+
                this._modeloObjCli_ped.ID_PEDIDO+" INT REFERENCES PEDIDO(Id) NOT NULL"+
                ")");
        db.execSQL("CREATE TABLE SUCURSAL_PEDIDO("+
                this._modeloObjSuc_ped.ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                this._modeloObjSuc_ped.ID_SUCURSAL+" INT REFERENCES SUCURSAL(Id) NOT NULL,"+
                this._modeloObjSuc_ped.ID_PEDIDO+" INT REFERENCES PEDIDO(Id) NOT NULL"+
                ")");
        db.execSQL("CREATE TABLE PRODUCTO_PEDIDO("+
                this._modeloObjPro_ped.ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
                this._modeloObjPro_ped.ID_PRODUCTO+" INT REFERENCES PRODUCTO(Id) NOT NULL, "+
                this._modeloObjPro_ped.ID_PEDIDO+" INT REFERENCES PEDIDO(Id) NOT NULL "+
                ")");
        db.execSQL("CREATE TABLE SUCURSAL_PRODUCTO("+
                this._modeloObjSuc_pro.ID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+
                this._modeloObjSuc_pro.ID_SUCURSAL+" INT REFERENCES SUCURSAL(Id) NOT NULL,"+
                this._modeloObjSuc_pro.ID_PRODUCTO+" INT REFERENCES PRODUCTO(Id) NOT NULL"+
                ")");
        db.execSQL("CREATE TABLE VENDEDOR_PEDIDO(" +
                this._modeloObjVendedor_pedido.ID+" INTEGER PRIMARY KEY AUTOINCREMENT," +
                this._modeloObjVendedor_pedido.ID_PEDIDO+" INT REFERENCES PEDIDO(Id) NOT NULL," +
                this._modeloObjVendedor_pedido.USER_Vendedor+" VARCHAR(40) REFERENCES USUARIO(Usuario) " +
                ");");
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
