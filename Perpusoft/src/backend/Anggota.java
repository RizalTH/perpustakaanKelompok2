package backend;

import backend.interfaces.CRUD;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class Anggota implements CRUD {

    //  Deklarasi MySql
    Connection con;
    Statement stm;
    DBKoneksi DB = new DBKoneksi();

    //  Hanya override untuk memenuhi permintaan interface. Nanti di overload di bawah.
    @Override
    public Boolean create(){
        return null;
    }

    //  Hanya override untuk memenuhi permintaan interface. Nanti di overload di bawah.
    @Override
    public Boolean update(){
        return null;
    }

    //  Hanya override untuk memenuhi permintaan interface. Nanti di overload di bawah.
    @Override
    public Boolean delete(){
        return null;
    }


    //  Class Konstruktor
    public Anggota() {

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
    public Boolean create(String nama_anggota, String jenis_kelamin, String tanggal_lahir, String tempat_lahir, String alamat, String nohp) {

        //  SQL Query Statement
        String sql = "INSERT INTO anggota(" +
                "nama_anggota, " +
                "jenis_kelamin, " +
                "tanggal_lahir, " +
                "tempat_lahir, " +
                "alamat, " +
                "nohp) VALUES('" + nama_anggota + "','" + jenis_kelamin + "','" + tanggal_lahir + "','" + tempat_lahir + "','" + alamat + "','" + nohp + "' )";

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
        dm.addColumn("Nama");
        dm.addColumn("Jenis Kelamin");
        dm.addColumn("Tgl Lahir");
        dm.addColumn("Tempat Lahir");
        dm.addColumn("Alamat");
        dm.addColumn("No. HP");

        //  SQL Query
        String sql = "SELECT * FROM anggota";

        try {

            //  Eksekusi query
            ResultSet rs = stm.executeQuery(sql);

            //  Loop untuk mengambil semua data
            while (rs.next()) {

                //  Ambil data
                String id_anggota = rs.getString(1);
                String nama_anggota = rs.getString(2);
                String jenis_kelamin = rs.getString(3);
                    if (jenis_kelamin.equals("1") ){
                        jenis_kelamin = "Laki - Laki";
                    } else {
                        jenis_kelamin = "Perempuan";
                    }
                String tanggal_lahir = rs.getString(4);
                String tempat_lahir = rs.getString(5);
                String alamat = rs.getString(6);
                String nohp = rs.getString(7);

                //  Draw ke tabel
                dm.addRow(new String[]{
                        id_anggota,
                        nama_anggota,
                        jenis_kelamin,
                        tanggal_lahir,
                        tempat_lahir,
                        alamat,
                        nohp
                });
            }

            return dm;

        //  Exception
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }


    //  ------------------------------------------------------------
    //  UPDATE DATA Method
    //  ------------------------------------------------------------
    //  Overloading
    //
    public Boolean update(String id, String nama_anggota, String jenis_kelamin, String tanggal_lahir, String tempat_lahir, String alamat, String nohp) {

        //  SQL Query
        String sql = "UPDATE anggota SET nama_anggota='" + nama_anggota + "', jenis_kelamin='" + jenis_kelamin + "',tanggal_lahir='" + tanggal_lahir + "',tempat_lahir='" + tempat_lahir + "',alamat='" + alamat + "',nohp='" + nohp + "' WHERE id_anggota='" + id + "'";

        try {

            //  Eksekusi SQL Query
            stm.execute(sql);

            return true;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }


    //  ------------------------------------------------------------
    //  DELETE DATA Method
    //  ------------------------------------------------------------
    //
    public Boolean delete(String id)
    {
        //  SQL Query
        String sql="DELETE FROM anggota WHERE id_anggota ='" + id + "'";

        try
        {

            //  Eksekusi Query
            stm.execute(sql);

            return true;

        } catch(Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
