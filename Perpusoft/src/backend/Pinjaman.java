package backend;

import backend.interfaces.CRUD;

import javax.swing.table.DefaultTableModel;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Pinjaman {

    //  Deklarasi MySql
    Connection con;
    Statement stm;
    DBKoneksi DB = new DBKoneksi();



    //  Class Konstruktor
    public Pinjaman() {

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


        //  SQL Query Statement
        String sql = "INSERT INTO peminjaman(" +
                "id_peminjaman," +
                "id_anggota, " +
                "id_petugas, " +
                "tanggal_pinjam, " +
                "tanggal_harus_kembali, " +
                "is_returned ) VALUES('" + id_peminjaman + "','" + id_anggota + "','" + id_petugas + "','" + tglSkrg + "','" + tglHarusKembali + "','"+is_returned + "' )";

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
        dm.addColumn("Nama Peminjam");
        dm.addColumn("Petugas");
        dm.addColumn("Tgl Pinjam");
        dm.addColumn("Harus Kembali");

        //  SQL Query
        String sql = "SELECT * FROM peminjaman";

        try {

            //  Eksekusi query
            ResultSet result = stm.executeQuery(sql);

            //  Loop untuk mengambil semua data
            while (result.next()) {

                //  Ambil data
                String id_peminjaman = result.getString(1);
                String id_anggota = result.getString(2);
                String id_petugas = result.getString(3);
                String tgl_pinjam = result.getString(4);
                String harus_kembali = result.getString(5);

                String nama_anggota = new Anggota().getNamaById(id_anggota);
                String nama_petugas = new Petugas().getNamaById(id_petugas);

                //  Draw ke tabel
                dm.addRow(new String[]{
                        id_peminjaman,
                        nama_anggota,
                        nama_petugas,
                        tgl_pinjam,
                        harus_kembali,
                });
            }

            return dm;

            //  Exception
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }

}
