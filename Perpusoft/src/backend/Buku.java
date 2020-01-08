/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import backend.interfaces.CRUD;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

/**
 *
 * @author Arwendy Melyndra
 */
public class Buku implements CRUD{
    //atribut
    public Integer id_buku;
    public String judul;
    public String penerbit;
    public String pengarang;
    public Integer stock;
    public Integer tahun_terbit;

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

    //constructor
    public Buku(Integer idBuku, String judulBuku, String penerbitBuku, String pengarangBuku,Integer stockBuku, Integer tahun){
        this.id_buku = idBuku;
        this.judul = judulBuku;
        this.penerbit = penerbitBuku;
        this.pengarang = pengarangBuku;
        this.stock = stockBuku;
        this.tahun_terbit = tahun;
    }

    public Buku(){
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
    public Boolean create(String judulBuku, String penerbitBuku, String pengarangBuku, String stockBuku, String tahun){

        // query sql untuk insert data buku
        String sql = "INSERT INTO buku (judul, penerbit, pengarang, stock, tahun_terbit) VALUES (?, ?, ?, ?, ?)";

        try {
            PreparedStatement statement = con.prepareStatement(sql);

            // mapping nilai parameter dari query sql nya (sesuai urutan)
            statement.setString(1, judulBuku);
            statement.setString(2, penerbitBuku);
            statement.setString(3, pengarangBuku);
            statement.setString(4, stockBuku);
            statement.setString(5, tahun);

            // jalankan query (baca jumlah row affectednya)
            int rowsInserted = statement.executeUpdate();

            // jika ada row affected nya, maka status sukses
            if (rowsInserted > 0) {
                return true;
            }

        } catch (SQLException ex) {
            // jika query gagal
            ex.printStackTrace();
        }
        return false;
    }

    //  ------------------------------------------------------------
    //  DELETE DATA Method
    //  ------------------------------------------------------------
    //
    public Boolean delete(String idBuku){

        // QUERY SQL UNTUK UPDATE DATA BUKU BERDASARKAN ID BUKU
        String sql = "DELETE FROM buku WHERE id_buku=?";

        try {
            PreparedStatement statement = con.prepareStatement(sql);

            // mapping nilai parameter dari query sql nya
            statement.setString(1, idBuku);

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
    //  UPDATE DATA Method
    //  ------------------------------------------------------------
    //  Overloading
    //
    public Boolean update(String idBuku, String judulBuku, String penerbitBuku, String pengarangBuku, String stockBuku, String tahun){

        // QUERY SQL UNTUK HAPUS DATA BUKU BERDASARKAN ID BUKU
        String sql = "UPDATE buku SET judul=?, penerbit=?, pengarang=?, stock=?, tahun_terbit=? WHERE id_buku=?";

        try {
            PreparedStatement statement = con.prepareStatement(sql);

            // mapping nilai parameter ke query sqlnya
            statement.setString(1, judulBuku);
            statement.setString(2, penerbitBuku);
            statement.setString(3, pengarangBuku);
            statement.setString(4, stockBuku);
            statement.setString(5, tahun);
            statement.setString(6, idBuku);

            // jalankan query, dan baca jumlah row affectednya
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                return true;
            }

        } catch (SQLException ex) {
        }
        return false;
    }

    //  ------------------------------------------------------------
    //  GET DATA Method
    //  ------------------------------------------------------------
    //
    @Override
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
        dm.addColumn("Pengarang");
        dm.addColumn("Penerbit");
        dm.addColumn("Tahun");
        dm.addColumn("Stock");

        //  SQL Query
        String sql = "SELECT * FROM buku";

        try {

            //  Eksekusi query
            ResultSet result = stm.executeQuery(sql);

            // looping untuk baca data per record
            while (result.next()) {

                // baca data buku per record
                String idBuku = result.getString("id_buku");
                String judulBuku = result.getString("judul");
                String penerbitBuku = result.getString("penerbit");
                String pengarangBuku = result.getString("pengarang");
                String stockBuku = result.getString("stock");
                String tahun = result.getString("tahun_terbit");

                // tampilkan data buku per record
                dm.addRow(new String[]{
                        idBuku,
                        judulBuku,
                        pengarangBuku,
                        penerbitBuku,
                        tahun,
                        stockBuku
                });
            }

            return dm;

        } catch (SQLException ex){
            ex.printStackTrace();
        }

        return null;

    }



    //  Stok GETTER
    //  ------------------------------------------------------------
    public Integer stockGet(String idBuku){

        Integer id = 0;
        //  SQL Query
        String sql = "SELECT stock FROM buku WHERE id_buku=" + idBuku;

        try {

            //  Eksekusi query
            ResultSet result = stm.executeQuery(sql);

            // looping untuk baca data per record
            while (result.next()) {
                // baca data
                id = Integer.parseInt(result.getString("stock"));

            }

        } catch (SQLException ex) {

        }

        return id;

    }


    //  Stok SETTER
    //  ------------------------------------------------------------
    public boolean stockSet(String idBuku, String stock){

        //  SQL Query
        String sql = "UPDATE buku SET stock=? WHERE id_buku=?";

        try {
            PreparedStatement statement = con.prepareStatement(sql);

            // mapping nilai parameter ke query sqlnya
            statement.setString(1, stock);
            statement.setString(2, idBuku);

            // jalankan query, dan baca jumlah row affectednya
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                return true;
            }

        } catch (SQLException ex) {
        }
        return false;
    }
}
