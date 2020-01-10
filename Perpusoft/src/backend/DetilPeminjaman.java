package backend;

//import method from class CRUD
import backend.interfaces.CRUD;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DetilPeminjaman {

    //  Deklarasi MySql
    Connection con;
    Statement stm;
    Statement stmm;

    DBKoneksi DB = new DBKoneksi();


    //  Class Konstruktor
    public DetilPeminjaman() {

        //  Konek ke Database
        DB.connect();
        con = DB.con;
        stm = DB.stm;
        stmm = DB.stm;

    }

    //  ------------------------------------------------------------
    //  INSERT DATA Method
    //  ------------------------------------------------------------
    //
    //
    public Boolean create(String id_peminjaman, String id_buku) {



        //SQL Query Statement
        String sql = "INSERT INTO detilpeminjaman(" +
                "id_peminjaman," +
                "id_buku ) VALUES('" + id_peminjaman + "','" + id_buku + "' )";

        try {

            //Eksekusi query
            stm.execute(sql);

            //Jika Berhasil, Return True.
            return true;

            //Jika Ada Error
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return false;
    }


    //  ------------------------------------------------------------
    //  Balikin stock
    //  ------------------------------------------------------------
    //
    public Boolean balikinStock(String id_peminjaman) {

        Integer stock;

        //  SQL Query
        String sqlSelect = "SELECT * FROM detilpeminjaman WHERE id_peminjaman='" + id_peminjaman + "'";
        try {
            //  Eksekusi query
            ResultSet result = stm.executeQuery(sqlSelect);

            //  Loop untuk mengambil semua data
            while (result.next()) {

                //  Ambil data
                String idBuku = result.getString(2);
                stock = new backend.Buku().stockGet(idBuku);
                Integer afterStock = stock + 1;


                //  SQL Query
                String sql = "UPDATE buku SET stock='" + afterStock + "' WHERE id_buku='" + idBuku + "'";
                try {
                    //  Eksekusi SQL Query
                    System.out.println(idBuku);
                    System.out.println(afterStock);


                    stm.execute(sql);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
            return true;

            //  Exception
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    return false;
    }

}