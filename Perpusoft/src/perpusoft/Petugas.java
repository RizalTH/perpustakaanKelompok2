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
 * @author Affifah
 */
public class Petugas {
       /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
     // atribut buku
    
    public Integer idPetugas;
    public String namaPetugas;
    public String jenisKelamin;
    public String tempatLahir;
    public String tanggalLahir;
    public String jabatan;
    public String alamat;
    public Integer noHp;
    
    // constructor
    public Petugas(Integer idPetugas, String namaPetugas, String jenisKelamin, String tempatLahir, String tanggalLahir,String jabatan, String alamat, Integer noHp ){
        this.idPetugas = idPetugas;
        this.namaPetugas = namaPetugas;
        this.tempatLahir = tempatLahir;
        this.tanggalLahir = tanggalLahir;
        this.jabatan = jabatan;
        this.alamat = alamat;
        this.noHp = noHp;
    }
    
    public Petugas(){
        
    }
    
    // CREATE DATA PETUGAS
    public void create(koneksi m, Integer idPetugas, String namaPetugas, String jenisKelamin, String tempatLahir, String tanggalLahir,String jabatan, String alamat, Integer noHp){
        // lakukan koneksi ke mysql
        Connection koneksi = m.con;
        
        // query sql untuk insert data petugas
        String sql = "INSERT INTO petugas (idPetugas,namaPetugas,jenisKelamin,tempatLahir,tanggalLahir,jabatan,alamat,noHp)";
 
        try {
            PreparedStatement statement = koneksi.prepareStatement(sql);
            
            // mapping nilai parameter dari query sql nya (sesuai urutan)
            statement.setString(1, idPetugas.toString());
            statement.setString(2, namaPetugas);
            statement.setString(3, jenisKelamin);
            statement.setString(4, tempatLahir);
            statement.setString(5, tanggalLahir);
            statement.setString(6, jabatan);
            statement.setString(7, alamat);
            statement.setString(8, noHp.toString());

            // jalankan query (baca jumlah row affectednya)
            int rowsInserted = statement.executeUpdate();
            // jika ada row affected nya, maka status sukses
            if (rowsInserted > 0) {
                System.out.println("Insert data petugas sukses");
            }

        } catch (SQLException ex) {
            // jika query gagal
            System.out.println("Insert data petugas gagal");
        }
    }
   
    // DELETE DATA BUKU BERDASARKAN ID PETUGAS)
    public void delete(koneksi m, Integer idPetugas){
        
        // QUERY SQL UNTUK HAPUS DATA BUKU BERDASARKAN ID PETUGAS
        String sql = "DELETE FROM petugas WHERE idPetugas=?";
        // lakukan koneksi ke mysql
        Connection koneksi = m.con;
        
        try {
            PreparedStatement statement;
            statement = koneksi.prepareStatement(sql);
            
            // mapping nilai parameter dari query sql nya
            statement.setString(1, idPetugas.toString());
            
            // jalankan query, dan lihat jumlah row affected nya
            int rowsDeleted = statement.executeUpdate();
            if (rowsDeleted > 0) {
                System.out.println("Data Petugas berhasil dihapus");
            }
        } catch (SQLException ex) {
            System.out.println("Hapus data petugas gagal");
        }
        
    }
    
    // UPDATE DATA PETGAS BERDASARKAN ID PETUGAS
    public void update(koneksi m, Integer idPetugas, String namaPetugas, String jenisKelamin, String tempatLahir, String tanggalLahir,String jabatan, String alamat, Integer noHp){
        
        // QUERY SQL UNTUK UPDATE DATA PETUGAS BERDASARKAN ID PETUGAS
        String sql = "UPDATE petugas SET namaPetugas=?, jenisKelamin=?, tempatLahir=?, tanggalLahir=?, jabatan=?, alamat=?, noHp=?  WHERE idPetugas=?";
        // lakukan koneksi ke mysql
        Connection koneksi = m.con;
        
        try {
            PreparedStatement statement = koneksi.prepareStatement(sql);
            // mapping nilai parameter ke query sqlnya
            statement.setString(1, idPetugas.toString());
            statement.setString(2, namaPetugas);
            statement.setString(3, jenisKelamin);
            statement.setString(4, tempatLahir);
            statement.setString(5, tanggalLahir);
            statement.setString(6, jabatan);
            statement.setString(7, alamat);
            statement.setString(8, noHp.toString());

            // jalankan query, dan baca jumlah row affectednya
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Update data petugas sukses");
            }
        } catch (SQLException ex) {
             System.out.println("Update data petugas gagal");
        }
    }
    
    // MENAMPILKAN SEMUA DATA PETUGAS
    public void read(koneksi m){
        
        // query sql untuk select all data petugas
        String sql = "SELECT * FROM petugas";
        
        // lakukan koneksi ke mysql
        Connection koneksi = m.con;
        
        try { 
            Statement statement = koneksi.createStatement();
            // jalankan query
            ResultSet result = statement.executeQuery(sql);

            // membuat header table untuk output
            System.out.println("==============================================================================");
            String header = "%3s %20s %20s %20s %20s %20s %20s %4s";
            System.out.println(String.format(header, "ID", "NAMA", "JENIS KELAMIN", "TEMPAT LAHIR", "TANGGAL LAHIR", "JABATAN", "ALAMAT", "NOMER HP"));
            System.out.println("------------------------------------------------------------------------------");
            
            // looping untuk baca data per record
            while (result.next()){
                // baca data buku per record
                String idPetugas = result.getString("idPetugas");
                String namaPetugas = result.getString("namaPetugas");
                String jenisKelamin = result.getString("jenisKelamin");
                String tempatLahir = result.getString("tempatLahir");
                String tanggalLahir = result.getString("tanggalLahir");
                String jabatan = result.getString("jabatan");
                String alamat = result.getString("alamat");
                String noHp = result.getString("noHp");
                // tampilkan data buku per record
                String output = "%3s %20s %20s %20s %20s %20s %20s %4s";
                System.out.println(String.format(output, idPetugas,namaPetugas,jenisKelamin,tempatLahir,tanggalLahir,jabatan,alamat,noHp));
            }
            
            System.out.println("==============================================================================");
            
        } catch (SQLException ex){
            System.out.println("Tampil data petugas gagal");
        }
        
    }
}
 


    

