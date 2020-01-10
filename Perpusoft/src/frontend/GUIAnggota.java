package frontend;

import backend.Anggota;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUIAnggota {
    private JTable tablePetugas;
    private JPanel parentPane;
    private JPanel btTambah;
    private JTextField namaTextField;
    private JTextField tglLahirTextField;
    private JRadioButton lakiLakiRadioButton;
    private JRadioButton perempuanRadioButton;
    private JTextField tempatLahirTextField;
    private JTextField alamatTextField;
    private JTextField noHpTextField;
    private JPanel buttonGroup;
    private JPanel btSimpan;
    private JPanel btCancel;
    private JPanel btHapus;
    private JPanel btUbah;
    private JLabel labelTambah;
    private JLabel labelSimpan;
    private JLabel labelHapus;
    private JLabel labelCancel;
    private JLabel labelUbah;

    private ButtonGroup jenisKelaminGroup = new ButtonGroup();

    //  Class Konstruktor
    public GUIAnggota() {
        jenisKelaminGroup.add(lakiLakiRadioButton);
        jenisKelaminGroup.add(perempuanRadioButton);
        lakiLakiRadioButton.setActionCommand("1");
        perempuanRadioButton.setActionCommand("0");
        btSimpan.setVisible(false);
        btCancel.setVisible(false);
        retrieve();


        //  Button TAMBAH
        //  --------------------------
        btTambah.addMouseListener(new MouseAdapter() {

            //  On Click
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                if (new Anggota().create(
                        namaTextField.getText(),
                        jenisKelaminGroup.getSelection().getActionCommand(),
                        tglLahirTextField.getText(),
                        tempatLahirTextField.getText(),
                        alamatTextField.getText(),
                        noHpTextField.getText()
                )) {

                    JOptionPane.showMessageDialog(null, "Berhasil Ditambah.");

                    clearTextField();

                    //  Perbarui table
                    retrieve();
                } else {
                    JOptionPane.showMessageDialog(null, "Gagal");
                }
            }

            //  On Hover
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered(mouseEvent);
                btTambah.setBackground(new Color(0, 69, 176));


            }

            //  On Exit Hover
            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited(mouseEvent);
                btTambah.setBackground(new Color(0, 49, 125));
            }
        });


        //  Button HAPUS
        //  --------------------------
        btHapus.addMouseListener(new MouseAdapter() {

            //  On Klik
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                String[] options = {"Ya", "Tidak"};
                int answ = JOptionPane.showOptionDialog(null, "Yakin ingin menghapus?", "Konfirmasi Hapus", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[1]);

                if (answ == 0) {
                    int index = tablePetugas.getSelectedRow();
                    String id = tablePetugas.getValueAt(index, 0).toString();

                    if (new Anggota().delete(id)) {
                        JOptionPane.showMessageDialog(null, "Berhasil Dihapus");

                        clearTextField();

                        //  Perbarui tabel
                        retrieve();
                    } else {
                        JOptionPane.showMessageDialog(null, "Gagal");
                    }

                }
            }

            //  On Hover
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered(mouseEvent);
                btHapus.setBackground(new Color(171, 0, 3));
            }

            //  On Hover Exit
            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited(mouseEvent);
                btHapus.setBackground(new Color(125, 0, 2));

            }
        });

        //  Button UBAH
        //  --------------------------
        btUbah.addMouseListener(new MouseAdapter() {

            //  On Click
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);

                //  Ambil value dari table
                String nama = tablePetugas.getValueAt(tablePetugas.getSelectedRow(), 1).toString();
                String jenis_kelamin = tablePetugas.getValueAt(tablePetugas.getSelectedRow(), 2).toString();
                String tanggal_lahir = tablePetugas.getValueAt(tablePetugas.getSelectedRow(), 3).toString();
                String tempat_lahir = tablePetugas.getValueAt(tablePetugas.getSelectedRow(), 4).toString();
                String alamat = tablePetugas.getValueAt(tablePetugas.getSelectedRow(), 5).toString();
                String nohp = tablePetugas.getValueAt(tablePetugas.getSelectedRow(), 6).toString();

                //  Masukin ke text field
                namaTextField.setText(nama);
                if (jenis_kelamin.equals("Laki - Laki")) {
                    lakiLakiRadioButton.setSelected(true);
                } else {
                    perempuanRadioButton.setSelected(true);
                }
                tglLahirTextField.setText(tanggal_lahir);
                tempatLahirTextField.setText(tempat_lahir);
                alamatTextField.setText(alamat);
                noHpTextField.setText(nohp);

                //  Masuk ke mode ubah
                btTambah.setVisible(false);
                btHapus.setVisible(false);
                btUbah.setVisible(false);
                btSimpan.setVisible(true);
                btCancel.setVisible(true);
            }

            //  On Hover
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered(mouseEvent);
                btUbah.setBackground(new Color(168, 171, 0));
            }

            //  On Hover Exit
            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited(mouseEvent);
                btUbah.setBackground(new Color(123, 125, 0));
            }
        });

        //  Button SIMPAN
        //  --------------------------
        btSimpan.addMouseListener(new MouseAdapter() {

            //  On Click
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                int index = tablePetugas.getSelectedRow();
                String id = tablePetugas.getValueAt(index, 0).toString();

                if (new Anggota().update(id, namaTextField.getText(), jenisKelaminGroup.getSelection().getActionCommand(), tglLahirTextField.getText(), tempatLahirTextField.getText(), alamatTextField.getText(), noHpTextField.getText())) {
                    JOptionPane.showMessageDialog(null, "Berhasil Diubah");

                    clearTextField();

                    btSimpan.setVisible(false);
                    btCancel.setVisible(false);
                    btHapus.setVisible(true);
                    btTambah.setVisible(true);
                    btUbah.setVisible(true);

                    retrieve();

                } else {
                    JOptionPane.showMessageDialog(null, "Gagal");
                }
            }

            //  On Hover
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered(mouseEvent);
                btSimpan.setBackground(new Color(0, 125, 9));
            }

            //  On Hover Ecit
            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited(mouseEvent);
                btSimpan.setBackground(new Color(0, 52, 11));
            }
        });

        //  Button CANCEL
        //  --------------------------
        btCancel.addMouseListener(new MouseAdapter() {

            //  On Click
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                clearTextField();
                btSimpan.setVisible(false);
                btCancel.setVisible(false);
                btTambah.setVisible(true);
                btUbah.setVisible(true);
                btHapus.setVisible(true);
            }

            //  On Hover
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered(mouseEvent);
                btCancel.setBackground(new Color(155, 152, 0));
            }

            //  On Hover Exit
            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited(mouseEvent);
                btCancel.setBackground(new Color(125, 122, 0));
            }
        });

    }

    public JPanel getParent() {
        return parentPane;
    }

    //  Retrieve data
    private void retrieve() {

        DefaultTableModel dm = new Anggota().read();
        dm.isCellEditable(0, 0);
        tablePetugas.setModel(dm);
    }


    //  Clear text field
    private void clearTextField() {
        namaTextField.setText("");
        jenisKelaminGroup.clearSelection();
        tglLahirTextField.setText("");
        tempatLahirTextField.setText("");
        alamatTextField.setText("");
        noHpTextField.setText("");
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        parentPane = new JPanel();
        parentPane.setLayout(new GridLayoutManager(3, 1, new Insets(10, 10, 10, 10), 0, 0));
        parentPane.setBackground(new Color(-2826009));
        parentPane.setMinimumSize(new Dimension(-1, -1));
        parentPane.setPreferredSize(new Dimension(-1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-2826009));
        parentPane.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel1.add(scrollPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        tablePetugas = new JTable();
        tablePetugas.setBackground(new Color(-2826009));
        tablePetugas.setCellSelectionEnabled(false);
        tablePetugas.setColumnSelectionAllowed(false);
        tablePetugas.setEditingColumn(-1);
        tablePetugas.setEditingRow(-1);
        tablePetugas.setEnabled(true);
        Font tablePetugasFont = this.$$$getFont$$$("Ubuntu Light", -1, -1, tablePetugas.getFont());
        if (tablePetugasFont != null) tablePetugas.setFont(tablePetugasFont);
        scrollPane1.setViewportView(tablePetugas);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 2, new Insets(10, 0, 0, 0), -1, -1));
        panel2.setBackground(new Color(-2826009));
        parentPane.add(panel2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(6, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.setBackground(new Color(-2826009));
        Font panel3Font = this.$$$getFont$$$("Ubuntu Light", -1, -1, panel3.getFont());
        if (panel3Font != null) panel3.setFont(panel3Font);
        panel3.setForeground(new Color(-12828863));
        panel2.add(panel3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$("Ubuntu Light", -1, -1, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setText("Nama");
        panel3.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$("Ubuntu Light", -1, -1, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setText("Jenis Kelamin");
        panel3.add(label2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        Font label3Font = this.$$$getFont$$$("Ubuntu Light", -1, -1, label3.getFont());
        if (label3Font != null) label3.setFont(label3Font);
        label3.setText("Tanggal Lahir");
        panel3.add(label3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        Font label4Font = this.$$$getFont$$$("Ubuntu Light", -1, -1, label4.getFont());
        if (label4Font != null) label4.setFont(label4Font);
        label4.setText("Tempat Lahir");
        panel3.add(label4, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        Font label5Font = this.$$$getFont$$$("Ubuntu Light", -1, -1, label5.getFont());
        if (label5Font != null) label5.setFont(label5Font);
        label5.setText("Alamat");
        panel3.add(label5, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        Font label6Font = this.$$$getFont$$$("Ubuntu Light", -1, -1, label6.getFont());
        if (label6Font != null) label6.setFont(label6Font);
        label6.setText("No. HP");
        panel3.add(label6, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(6, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel4.setBackground(new Color(-2826009));
        panel2.add(panel4, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        namaTextField = new JTextField();
        Font namaTextFieldFont = this.$$$getFont$$$("Ubuntu Light", -1, -1, namaTextField.getFont());
        if (namaTextFieldFont != null) namaTextField.setFont(namaTextFieldFont);
        panel4.add(namaTextField, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel5.setBackground(new Color(-2826009));
        Font panel5Font = this.$$$getFont$$$("Ubuntu Light", -1, -1, panel5.getFont());
        if (panel5Font != null) panel5.setFont(panel5Font);
        panel4.add(panel5, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        lakiLakiRadioButton = new JRadioButton();
        lakiLakiRadioButton.setBackground(new Color(-2826009));
        Font lakiLakiRadioButtonFont = this.$$$getFont$$$("Ubuntu Light", -1, -1, lakiLakiRadioButton.getFont());
        if (lakiLakiRadioButtonFont != null) lakiLakiRadioButton.setFont(lakiLakiRadioButtonFont);
        lakiLakiRadioButton.setText("Laki - Laki");
        panel5.add(lakiLakiRadioButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        perempuanRadioButton = new JRadioButton();
        perempuanRadioButton.setBackground(new Color(-2826009));
        Font perempuanRadioButtonFont = this.$$$getFont$$$("Ubuntu Light", -1, -1, perempuanRadioButton.getFont());
        if (perempuanRadioButtonFont != null) perempuanRadioButton.setFont(perempuanRadioButtonFont);
        perempuanRadioButton.setText("Perempuan");
        panel5.add(perempuanRadioButton, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        tempatLahirTextField = new JTextField();
        Font tempatLahirTextFieldFont = this.$$$getFont$$$("Ubuntu Light", -1, -1, tempatLahirTextField.getFont());
        if (tempatLahirTextFieldFont != null) tempatLahirTextField.setFont(tempatLahirTextFieldFont);
        panel4.add(tempatLahirTextField, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        alamatTextField = new JTextField();
        Font alamatTextFieldFont = this.$$$getFont$$$("Ubuntu Light", -1, -1, alamatTextField.getFont());
        if (alamatTextFieldFont != null) alamatTextField.setFont(alamatTextFieldFont);
        panel4.add(alamatTextField, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        noHpTextField = new JTextField();
        Font noHpTextFieldFont = this.$$$getFont$$$("Ubuntu Light", -1, -1, noHpTextField.getFont());
        if (noHpTextFieldFont != null) noHpTextField.setFont(noHpTextFieldFont);
        panel4.add(noHpTextField, new GridConstraints(5, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tglLahirTextField = new JTextField();
        Font tglLahirTextFieldFont = this.$$$getFont$$$("Ubuntu Light", -1, -1, tglLahirTextField.getFont());
        if (tglLahirTextFieldFont != null) tglLahirTextField.setFont(tglLahirTextFieldFont);
        panel4.add(tglLahirTextField, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        buttonGroup = new JPanel();
        buttonGroup.setLayout(new GridLayoutManager(1, 5, new Insets(10, 0, 0, 0), 15, -1));
        buttonGroup.setBackground(new Color(-2826009));
        parentPane.add(buttonGroup, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 60), new Dimension(-1, 60), 0, false));
        btTambah = new JPanel();
        btTambah.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        btTambah.setBackground(new Color(-16764547));
        btTambah.setEnabled(true);
        buttonGroup.add(btTambah, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        labelTambah = new JLabel();
        Font labelTambahFont = this.$$$getFont$$$("Ubuntu Light", -1, -1, labelTambah.getFont());
        if (labelTambahFont != null) labelTambah.setFont(labelTambahFont);
        labelTambah.setForeground(new Color(-1));
        labelTambah.setText("TAMBAH ANGGOTA BARU");
        btTambah.add(labelTambah, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btSimpan = new JPanel();
        btSimpan.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        btSimpan.setBackground(new Color(-16745207));
        btSimpan.setEnabled(true);
        btSimpan.setVisible(true);
        buttonGroup.add(btSimpan, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        labelSimpan = new JLabel();
        Font labelSimpanFont = this.$$$getFont$$$("Ubuntu Light", -1, -1, labelSimpan.getFont());
        if (labelSimpanFont != null) labelSimpan.setFont(labelSimpanFont);
        labelSimpan.setForeground(new Color(-1));
        labelSimpan.setText("SIMPAN");
        btSimpan.add(labelSimpan, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btHapus = new JPanel();
        btHapus.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        btHapus.setBackground(new Color(-8585214));
        btHapus.setEnabled(true);
        buttonGroup.add(btHapus, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        labelHapus = new JLabel();
        Font labelHapusFont = this.$$$getFont$$$("Ubuntu Light", -1, -1, labelHapus.getFont());
        if (labelHapusFont != null) labelHapus.setFont(labelHapusFont);
        labelHapus.setForeground(new Color(-1));
        labelHapus.setText("HAPUS");
        btHapus.add(labelHapus, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btCancel = new JPanel();
        btCancel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        btCancel.setBackground(new Color(-8553984));
        btCancel.setEnabled(true);
        btCancel.setVisible(true);
        buttonGroup.add(btCancel, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        labelCancel = new JLabel();
        Font labelCancelFont = this.$$$getFont$$$("Ubuntu Light", -1, -1, labelCancel.getFont());
        if (labelCancelFont != null) labelCancel.setFont(labelCancelFont);
        labelCancel.setForeground(new Color(-1));
        labelCancel.setText("BATAL");
        btCancel.add(labelCancel, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btUbah = new JPanel();
        btUbah.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        btUbah.setBackground(new Color(-8684288));
        btUbah.setEnabled(true);
        buttonGroup.add(btUbah, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        labelUbah = new JLabel();
        Font labelUbahFont = this.$$$getFont$$$("Ubuntu Light", -1, -1, labelUbah.getFont());
        if (labelUbahFont != null) labelUbah.setFont(labelUbahFont);
        labelUbah.setForeground(new Color(-1));
        labelUbah.setText("UBAH");
        btUbah.add(labelUbah, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return parentPane;
    }

}
