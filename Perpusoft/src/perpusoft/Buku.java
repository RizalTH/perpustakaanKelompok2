/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package perpusoft;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Arwendy Melyndra
 */
public class Buku {
    
    //atribut
    public Integer id_buku;
    public String judul;
    public String penerbit;
    public String pengarang;
    public Integer stock;
    public Integer tahun_terbit;
    
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
        
    }
    
    // CREATE DATA BUKU
    public void create(koneksi m, Integer idBuku, String judulBuku, String penerbitBuku, String pengarangBuku, Integer stockBuku, Integer tahun){
        // Lakukan koneksi ke mysql
        Connection koneksi = m.con;
        
        // query sql untuk insert data buku
        String sql = "INSERT INTO buku (id_buku, judul, penerbit, pengarang, stock, tahun_terbit)";
 
        try {
            PreparedStatement statement = koneksi.prepareStatement(sql);
            
            // mapping nilai parameter dari query sql nya (sesuai urutan)
            statement.setString(1, idBuku.toString());
            statement.setString(2, judulBuku);
            statement.setString(3, penerbitBuku);
            statement.setString(4, pengarangBuku);
            statement.setString(5, stockBuku.toString());
            statement.setString(6, tahun.toString());

            // jalankan query (baca jumlah row affectednya)
            int rowsInserted = statement.executeUpdate();
            // jika ada row affected nya, maka status sukses
            if (rowsInserted > 0) {
                System.out.println("Insert data buku sukses");
            }

        } catch (SQLException ex) {
            // jika query gagal
            System.out.println("Insert data buku gagal");
        }
    }
   
    // DELETE DATA BUKU BERDASARKAN ID BUKU)
    public void delete(koneksi m, Integer idBuku){
        
        // QUERY SQL UNTUK UPDATE DATA BUKU BERDASARKAN ID BUKU
        String sql = "DELETE FROM buku WHERE id_buku=?";
        // lakukan koneksi ke mysql
        Connection koneksi = m.con;
        
        try {
            PreparedStatement statement;
            statement = koneksi.prepareStatement(sql);
            
            // mapping nilai parameter dari query sql nya
            statement.setString(1, idBuku.toString());
            
            // jalankan query, dan lihat jumlah row affected nya
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Data buku berhasil dihapus");
            }
        } catch (SQLException ex) {
            System.out.println("Hapus data buku gagal");
        }
        
    }
    
    // UPDATE DATA BUKU BERDASARKAN ID BUKU
    public void update(koneksi m, Integer idBuku, String judulBuku, String penerbitBuku, String pengarangBuku, Integer stockBuku, Integer tahun){
        
        // QUERY SQL UNTUK HAPUS DATA BUKU BERDASARKAN ID BUKU
        String sql = "UPDATE buku SET judul=?, penerbit=?, pengarang=?, stock=?, tahun_terbit=? WHERE id_buku=?";
        // lakukan koneksi ke mysql
        Connection koneksi = m.con;
        
        try {
            PreparedStatement statement = koneksi.prepareStatement(sql);
            // mapping nilai parameter ke query sqlnya
            statement.setString(1, idBuku.toString());
            statement.setString(2, judulBuku);
            statement.setString(3, penerbitBuku);
            statement.setString(4, pengarangBuku);
            statement.setString(5, stockBuku.toString());
            statement.setString(6, tahun.toString());

            // jalankan query, dan baca jumlah row affectednya
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Update data buku sukses");
            }
        } catch (SQLException ex) {
             System.out.println("Update data buku gagal");
        }
    }
    
    // MENAMPILKAN SEMUA DATA BUKU
    public void read(koneksi m){
        
        // query sql untuk select all data buku
        String sql = "SELECT * FROM buku";
        
        // lakukan koneksi ke mysql
        Connection koneksi = m.con;
        
        try { 
            Statement statement = koneksi.createStatement();
            // jalankan query
            ResultSet result = statement.executeQuery(sql);

            // membuat header table untuk output
            System.out.println("==============================================================================");
            String header = "%3s %20s %20s %20s %4s %4s";
            System.out.println(String.format(header, "ID", "JUDUL", "PENERBIT", "PENGARANG", "STOCK", "TAHUN"));
            System.out.println("------------------------------------------------------------------------------");
            
            // looping untuk baca data per record
            while (result.next()){
                // baca data buku per record
                String idBuku = result.getString("id_buku");
                String judulBuku = result.getString("judul");
                String penerbitBuku = result.getString("penerbit");
                String pengarangBuku = result.getString("pengarang");
                String stockBuku = result.getString("stock");
                String tahun = result.getString("tahun_terbit");

                // tampilkan data buku per record
                String output = "%3s %20s %20s %20s %4s %4s";
                System.out.println(String.format(output, idBuku, judulBuku, penerbitBuku, pengarangBuku, stockBuku, tahun));
            }
            
            System.out.println("==============================================================================");
            
        } catch (SQLException ex){
            System.out.println("Tampil data buku gagal");
        }
        
    }
}
