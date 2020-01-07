package frontend;

import backend.Anggota;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Pinjaman {
    private JPanel parentPane;
    private JTable tablePeminjaman;
    private JPanel btTambah;


    public Pinjaman() {
        retrieve();
    }

    public JPanel getParent() {
        return parentPane;
    }

    private void retrieve()
    {
        DefaultTableModel dm = new Anggota().read();
        tablePeminjaman.setModel(dm);
    }

}
