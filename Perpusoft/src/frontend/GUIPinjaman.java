package frontend;

import backend.Anggota;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class GUIPinjaman {
    private JPanel parentPane;
    private JTable tableAnggota;
    private JTextField namaTextField;
    private JRadioButton lakiLakiRadioButton;
    private JRadioButton perempuanRadioButton;
    private JTextField tempatLahirTextField;
    private JTextField alamatTextField;
    private JTextField noHpTextField;
    private JTextField tglLahirTextField;
    private JPanel buttonGroup;
    private JPanel btTambah;
    private JPanel btSimpan;
    private JPanel btHapus;
    private JTable tablePeminjaman;

    public JPanel getParent() {
        return parentPane;
    }


}
