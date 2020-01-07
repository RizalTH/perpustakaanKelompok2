package frontend;

import javax.swing.*;

public class GUIBuku {
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
    private JPanel btCancel;
    private JPanel btUbah;

    public JPanel getParent(){
        return parentPane;
    }

}
