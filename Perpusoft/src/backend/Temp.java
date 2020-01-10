package backend;

import com.sun.org.apache.xpath.internal.operations.Bool;

import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class Temp {

    //  Deklarasi MySql
    Connection con;
    Statement stm;
    DBKoneksi DB = new DBKoneksi();

    //  Class Konstruktor
    public Temp() {

        //  Konek ke database
        DB.connect();
        con = DB.con;
        stm = DB.stm;
    }

    //  ------------------------------------------------------------
    //  INSERT DATA Method
    //  ------------------------------------------------------------
    //  Overloading
    //
    public Boolean create(String id_buku, String judul) {

        //  SQL Query Statement
        String sql = "INSERT INTO temppeminjaman(" +
                "id_buku, " +
                "judul) VALUES('" + id_buku + "','" + judul +"' )";

        try {

            //  Eksekusi query
            stm.execute(sql);

            //  Jika berhasil, return true.
            return true;

            //  Jika ada error
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

    //  ------------------------------------------------------------
    //  GET DATA Method
    //  ------------------------------------------------------------
    //
    public DefaultTableModel read() {

        //  Buat model tabel
        DefaultTableModel dm = new DefaultTableModel() {

            //  Tabel tidak bisa diedit langsung
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        //  Tambah kolom ke model tabel
        dm.addColumn("ID");
        dm.addColumn("Judul");

        //  SQL Query
        String sql = "SELECT * FROM temppeminjaman";

        try {

            //  Eksekusi query
            ResultSet result = stm.executeQuery(sql);

            // looping untuk baca data per record
            while (result.next()) {

                // baca data buku per record
                String idBuku = result.getString("id_buku");
                String judulBuku = result.getString("judul");

                // tampilkan data buku per record
                dm.addRow(new String[]{
                        idBuku,
                        judulBuku
                });
            }

            return dm;

        } catch (SQLException ex){
            ex.printStackTrace();
        }

        return null;

    }


    //  ------------------------------------------------------------
    //  DELETE DATA Method
    //  ------------------------------------------------------------
    //
    public Boolean delete(String idBuku){

        // QUERY SQL UNTUK UPDATE DATA BUKU BERDASARKAN ID BUKU
        String sql = "DELETE FROM temppeminjaman WHERE id_buku=" + idBuku;

        try {
            PreparedStatement statement = con.prepareStatement(sql);

            // jalankan query, dan lihat jumlah row affected nya
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
            }
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }


    //  ------------------------------------------------------------
    //  TRUNCATE  Method
    //  ------------------------------------------------------------
    //  Menghapus semua data

    public Boolean truncate(){

        // QUERY SQL UNTUK UPDATE DATA BUKU BERDASARKAN ID BUKU
        String sql = "TRUNCATE TABLE temppeminjaman";

        try {
            PreparedStatement statement = con.prepareStatement(sql);

            // jalankan query, dan lihat jumlah row affected nya
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
            }
            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }


    //  IF EXIST Method
    //  ------------------------------------------------------------
    //
    public Boolean ifNotExist(String id){

        //  SQL Query
        String sql = "SELECT * FROM temppeminjaman WHERE id_buku='" + id + "'";

        try {
            int count = 0;
            //  Eksekusi query
            ResultSet result = stm.executeQuery(sql);

            while(result.next()) {
                count = result.getInt(0);
            }

            if (count == 0){
                return true;
            } else {
                return false;
            }

        } catch (SQLException ex){
            ex.printStackTrace();
            return false;
        }
    }

}
