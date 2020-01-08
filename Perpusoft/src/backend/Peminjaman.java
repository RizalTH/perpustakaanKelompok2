package backend;

//import method from class CRUD
import backend.interfaces.CRUD;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Peminjaman {

    //Deklarasi MySql
    Connection con;
    Statement stm;
    DBKoneksi DB = new DBKoneksi();



    //Definisi Class Konstruktor
    public Peminjaman() {

        //Konek ke Database
        DB.connect();
        con = DB.con;
        stm = DB.stm;
    }

    //------------------------------------------------------------
    //INSERT DATA Method
    //------------------------------------------------------------
    //Overloading
    //
    public Boolean create(String id_peminjaman, String id_anggota, String durasi) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        String tglSkrg = dateFormat.format(date);

        Integer d = Integer.parseInt(durasi);

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, d);
        date = c.getTime();
        String tglHarusKembali = dateFormat.format(date);

        String is_returned = "0";
        String id_petugas = new perpusoft.GlobalConfig().getCurrentIdPetugas();


        //SQL Query Statement
        String sql = "INSERT INTO peminjaman(" +
                "id_peminjaman," +
                "id_anggota, " +
                "id_petugas, " +
                "tanggal_pinjam, " +
                "tanggal_harus_kembali, " +
                "is_returned ) VALUES('" + id_peminjaman + "','" + id_anggota + "','" + id_petugas + "','" + tglSkrg + "','" + tglHarusKembali + "','"+is_returned + "' )";

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


    //------------------------------------------------------------
    //GET DATA Method
    //------------------------------------------------------------
    //
    public DefaultTableModel read() {

        //Buat Model Tabel
        DefaultTableModel dm = new DefaultTableModel() {

            //Tabel Tidak Bisa Di Edit Langsung
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        //Tambah Kolom ke Model Tabel
        dm.addColumn("ID");
        dm.addColumn("Nama Peminjam");
        dm.addColumn("Petugas");
        dm.addColumn("Tgl Pinjam");
        dm.addColumn("Harus Kembali");

        //SQL Query
        String sql = "SELECT * FROM peminjaman";

        try {

            //Eksekusi Query
            ResultSet result = stm.executeQuery(sql);

            //Loop untuk Mengambil Semua Data
            while (result.next()) {

                //Ambil Data
                String id_peminjaman = result.getString(1);
                String id_anggota = result.getString(2);
                String id_petugas = result.getString(3);
                String tgl_pinjam = result.getString(4);
                String harus_kembali = result.getString(5);

                String nama_anggota = new Anggota().getNamaById(id_anggota);
                String nama_petugas = new Petugas().getNamaById(id_petugas);

                //Draw ke Tabel
                dm.addRow(new String[]{
                        id_peminjaman,
                        nama_anggota,
                        nama_petugas,
                        tgl_pinjam,
                        harus_kembali,
                });
            }

            return dm;

            //Exception
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

}
 */
public class Peminjaman implements CRUD{
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
    public Peminjaman() {

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
        String sql = "INSERT INTO peminjaman(" +
                "nama_buku, " +
                "tanggal_peminjaman, " +
                "tanggal_harus_dikembalikan, " +
                "tanggal_dikembalikan")
                 VALUES('" + nama_buku + "','" + tanggal_peminjaman + "','" + tanggal_harus_dikembalikan + "','" + tanggal_dikembalikan + "' )";

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
        dm.addColumn("tanggal_peminjaman");
        dm.addColumn("tanggal_harus_dikembalikan");
        dm.addColumn("tanggal_dikembalikan");

        //  SQL Query
        String sql = "SELECT * FROM peminjaman";

        try {

            //  Eksekusi query
            ResultSet rs = stm.executeQuery(sql);

            //  Loop untuk mengambil semua data
            while (rs.next()) {

                //  Ambil data
                String id_peminjaman = rs.getString(1);
                String id_anggota = rs.getString(2);
                String id_petugas = rs.getString(3);
                String tanggal_pinjam = rs.getString(4);
                String tanggal_harus_kembali = rs.getString(5);
                String is_returned  = rs.getString(6);
                String tanggal_dikembalikan = rs.getString(7);

                //  Draw ke tabel
                dm.addRow(new String[]{
                        id_peminjaman,
                        id_anggota,
                        id_petugas,
                        tanggal_pinjam,
                        tanggal_harus_kembali,
                        id_returned,
                        tanggal_dikembalikan,
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
    public Boolean update(String id, String tanggal_pinjam, String tanggal_harus_dikembalikan, String tanggal_dikembalikan) {

        //  SQL Query
        String sql = "UPDATE anggota SET tanggal_peminjaman='" + tanggal_peminjaman + "', tanggal_harus_dikembalikan='" + tanggal_harus_dikembalikan + "',tanggal_dikembalikan='" + tanggal_dikembalikan + "' WHERE id_peminjaman='" + id + "'";

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
        String sql="DELETE FROM peminjaman WHERE id_peminjaman ='" + id + "'";

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
