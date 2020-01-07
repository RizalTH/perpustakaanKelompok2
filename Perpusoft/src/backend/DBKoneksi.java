package backend;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBKoneksi {

    //  Deklarasi variabel
    Connection con;
    Statement stm;
    static String conString = "jdbc:mysql://localhost:3306/perpustakaan?useLegacyDatetimeCode=false&serverTimezone=UTC";
    static String username = "perpus";
    static String password = "perpus123";

    //  Fungsi utama untuk konek ke mysql
    public void connect(){
        try {
            con = DriverManager.getConnection(conString, username, password);
            stm = con.createStatement();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "koneksi gagal "+e.getMessage());
        }
    }
}
