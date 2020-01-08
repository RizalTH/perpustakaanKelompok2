/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;
/**
 *
 * @affifah
 */
       
import backend.abstracts.GetNamaById;
import backend.interfaces.CRUD;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class Petugas extends GetNamaById implements CRUD {

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
    public Petugas() {

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
    public Boolean create(String nama_petugas, String jenis_kelamin, String tanggal_lahir, String tempat_lahir, String jabatan, String alamat, String nohp) {

        //  SQL Query Statement
        String sql = "INSERT INTO petugas(" +
                "nama_petugas, " +
                "jenis_kelamin, " +
                "tanggal_lahir, " +
                "tempat_lahir, " +
                "jabatan, " +
                "alamat, " +
                "nohp) VALUES('" + nama_petugas + "','" + jenis_kelamin + "','" + tanggal_lahir + "','" + tempat_lahir + "','"+jabatan+ "','" + alamat + "','" + nohp + "' )";

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
        dm.addColumn("Jabatan");
        dm.addColumn("Alamat");
        dm.addColumn("No. HP");

        //  SQL Query
        String sql = "SELECT * FROM petugas";

        try {

            //  Eksekusi query
            ResultSet result = stm.executeQuery(sql);

            //  Loop untuk mengambil semua data
            while (result.next()) {

                //  Ambil data
                String id_petugas = result.getString(1);
                String nama_petugas = result.getString(2);
                String jenis_kelamin = result.getString(3);
                    if (jenis_kelamin.equals("1") ){
                        jenis_kelamin = "Laki - Laki";
                    } else {
                        jenis_kelamin = "Perempuan";
                    }
                String tanggal_lahir = result.getString(4);
                String tempat_lahir = result.getString(5);
                String jabatan = result.getString(6);
                String alamat = result.getString(7);
                String nohp = result.getString(8);

                //  Draw ke tabel
                dm.addRow(new String[]{
                        id_petugas,
                        nama_petugas,
                        jenis_kelamin,
                        tanggal_lahir,
                        tempat_lahir,
                        jabatan,
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
    public Boolean update(String id_petugas, String nama_petugas, String jenis_kelamin, String tanggal_lahir, String tempat_lahir, String jabatan, String alamat, String nohp) {

        //  SQL Query
        String sql = "UPDATE petugas SET nama_petugas='" + nama_petugas + "', jenis_kelamin='" + jenis_kelamin + "',tanggal_lahir='" + tanggal_lahir + "',tempat_lahir='" + tempat_lahir + "',jabatan='" + jabatan +"',alamat='" + alamat + "',nohp='" + nohp + "' WHERE id_petugas='" + id_petugas + "'";

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
        String sql="DELETE FROM petugas WHERE id_petugas ='" + id + "'";

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




    //  Get Nama Petugas by ID
    //  ------------------------------------------------------------
    //
    public String getNamaById(String id)
    {
        //  SQL Query
        String sql="SELECT * FROM petugas WHERE id_petugas ='" + id + "'";

        String nama_petugas = "";
        try
        {

            //  Eksekusi Query
            stm.execute(sql);

            //  Eksekusi query
            ResultSet result = stm.executeQuery(sql);

            //  Loop untuk mengambil semua data
            while (result.next()) {

                //  Ambil data
                nama_petugas = result.getString(2);
                return nama_petugas;

            }


        } catch(Exception ex) {
            ex.printStackTrace();
        }

        return nama_petugas;
    }


}


