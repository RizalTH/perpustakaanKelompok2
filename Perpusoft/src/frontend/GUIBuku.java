package frontend;

import backend.Buku;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUIBuku {
    private JPanel parentPane;
    private JTable tableBuku;
    private JTextField judulTextField;
    private JRadioButton lakiLakiRadioButton;
    private JRadioButton perempuanRadioButton;
    private JTextField tahunTerbitTextField;
    private JTextField stokAwalTextField;
    private JTextField noHpTextField;
    private JTextField penerbitTextField;
    private JPanel buttonGroup;
    private JPanel btTambah;
    private JPanel btSimpan;
    private JPanel btHapus;
    private JPanel btCancel;
    private JPanel btUbah;
    private JTextField pengarangTextField;

    public JPanel getParent() {
        return parentPane;
    }

    //  Class Konstruktor
    public GUIBuku() {
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
                if (new Buku().create(
                        judulTextField.getText(),
                        penerbitTextField.getText(),
                        pengarangTextField.getText(),
                        stokAwalTextField.getText(),
                        tahunTerbitTextField.getText()
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
                    int index = tableBuku.getSelectedRow();
                    String id = tableBuku.getValueAt(index, 0).toString();

                    if (new Buku().delete(id)) {
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
                String judul = tableBuku.getValueAt(tableBuku.getSelectedRow(), 1).toString();
                String pengarang = tableBuku.getValueAt(tableBuku.getSelectedRow(), 2).toString();
                String penerbit = tableBuku.getValueAt(tableBuku.getSelectedRow(), 3).toString();
                String tahun = tableBuku.getValueAt(tableBuku.getSelectedRow(), 4).toString();
                String stock = tableBuku.getValueAt(tableBuku.getSelectedRow(), 5).toString();

                //  Masukin ke text field
                judulTextField.setText(judul);
                pengarangTextField.setText(pengarang);
                penerbitTextField.setText(penerbit);
                tahunTerbitTextField.setText(tahun);
                stokAwalTextField.setText(stock);

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
                int index = tableBuku.getSelectedRow();
                String id = tableBuku.getValueAt(index, 0).toString();

                if (new Buku().update(id, judulTextField.getText(), penerbitTextField.getText(), pengarangTextField.getText(), stokAwalTextField.getText(), tahunTerbitTextField.getText())) {
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

    //  Retrieve data
    private void retrieve() {
        DefaultTableModel dm = new Buku().read();
        dm.isCellEditable(0, 0);
        tableBuku.setModel(dm);
    }

    //  Clear text field
    private void clearTextField() {
        judulTextField.setText("");
        pengarangTextField.setText("");
        penerbitTextField.setText("");
        stokAwalTextField.setText("");
        tahunTerbitTextField.setText("");
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
        tableBuku = new JTable();
        tableBuku.setBackground(new Color(-2826009));
        tableBuku.setCellSelectionEnabled(false);
        tableBuku.setColumnSelectionAllowed(false);
        tableBuku.setEditingColumn(-1);
        tableBuku.setEditingRow(-1);
        tableBuku.setEnabled(true);
        Font tableBukuFont = this.$$$getFont$$$("Ubuntu Light", -1, -1, tableBuku.getFont());
        if (tableBukuFont != null) tableBuku.setFont(tableBukuFont);
        scrollPane1.setViewportView(tableBuku);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 2, new Insets(10, 0, 0, 0), -1, -1));
        panel2.setBackground(new Color(-2826009));
        parentPane.add(panel2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(5, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel3.setBackground(new Color(-2826009));
        Font panel3Font = this.$$$getFont$$$("Ubuntu Light", -1, -1, panel3.getFont());
        if (panel3Font != null) panel3.setFont(panel3Font);
        panel3.setForeground(new Color(-12828863));
        panel2.add(panel3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setBackground(new Color(-13026242));
        Font label1Font = this.$$$getFont$$$("Ubuntu Light", -1, -1, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-13750738));
        label1.setText("Judul");
        panel3.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setBackground(new Color(-13026242));
        Font label2Font = this.$$$getFont$$$("Ubuntu Light", -1, -1, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setForeground(new Color(-13750738));
        label2.setText("Pengarang");
        panel3.add(label2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setBackground(new Color(-13026242));
        Font label3Font = this.$$$getFont$$$("Ubuntu Light", -1, -1, label3.getFont());
        if (label3Font != null) label3.setFont(label3Font);
        label3.setForeground(new Color(-13750738));
        label3.setText("Penerbit");
        panel3.add(label3, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setBackground(new Color(-13026242));
        Font label4Font = this.$$$getFont$$$("Ubuntu Light", -1, -1, label4.getFont());
        if (label4Font != null) label4.setFont(label4Font);
        label4.setForeground(new Color(-13750738));
        label4.setText("Tahun Terbit");
        panel3.add(label4, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setBackground(new Color(-13026242));
        Font label5Font = this.$$$getFont$$$("Ubuntu Light", -1, -1, label5.getFont());
        if (label5Font != null) label5.setFont(label5Font);
        label5.setForeground(new Color(-13750738));
        label5.setText("Stok Awal");
        panel3.add(label5, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(5, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel4.setBackground(new Color(-2826009));
        panel2.add(panel4, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        judulTextField = new JTextField();
        Font judulTextFieldFont = this.$$$getFont$$$("Ubuntu Light", -1, -1, judulTextField.getFont());
        if (judulTextFieldFont != null) judulTextField.setFont(judulTextFieldFont);
        panel4.add(judulTextField, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        tahunTerbitTextField = new JTextField();
        Font tahunTerbitTextFieldFont = this.$$$getFont$$$("Ubuntu Light", -1, -1, tahunTerbitTextField.getFont());
        if (tahunTerbitTextFieldFont != null) tahunTerbitTextField.setFont(tahunTerbitTextFieldFont);
        panel4.add(tahunTerbitTextField, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        stokAwalTextField = new JTextField();
        Font stokAwalTextFieldFont = this.$$$getFont$$$("Ubuntu Light", -1, -1, stokAwalTextField.getFont());
        if (stokAwalTextFieldFont != null) stokAwalTextField.setFont(stokAwalTextFieldFont);
        panel4.add(stokAwalTextField, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        penerbitTextField = new JTextField();
        Font penerbitTextFieldFont = this.$$$getFont$$$("Ubuntu Light", -1, -1, penerbitTextField.getFont());
        if (penerbitTextFieldFont != null) penerbitTextField.setFont(penerbitTextFieldFont);
        panel4.add(penerbitTextField, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        pengarangTextField = new JTextField();
        panel4.add(pengarangTextField, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        buttonGroup = new JPanel();
        buttonGroup.setLayout(new GridLayoutManager(1, 5, new Insets(10, 0, 0, 0), 15, -1));
        buttonGroup.setBackground(new Color(-2826009));
        parentPane.add(buttonGroup, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 60), new Dimension(-1, 60), 0, false));
        btTambah = new JPanel();
        btTambah.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        btTambah.setBackground(new Color(-16764547));
        btTambah.setEnabled(true);
        buttonGroup.add(btTambah, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        Font label6Font = this.$$$getFont$$$("Ubuntu Light", -1, -1, label6.getFont());
        if (label6Font != null) label6.setFont(label6Font);
        label6.setForeground(new Color(-1));
        label6.setText("TAMBAH BUKU BARU");
        btTambah.add(label6, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btSimpan = new JPanel();
        btSimpan.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        btSimpan.setBackground(new Color(-16745207));
        btSimpan.setEnabled(true);
        btSimpan.setVisible(true);
        buttonGroup.add(btSimpan, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        Font label7Font = this.$$$getFont$$$("Ubuntu Light", -1, -1, label7.getFont());
        if (label7Font != null) label7.setFont(label7Font);
        label7.setForeground(new Color(-1));
        label7.setText("SIMPAN");
        btSimpan.add(label7, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btHapus = new JPanel();
        btHapus.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        btHapus.setBackground(new Color(-8585214));
        btHapus.setEnabled(true);
        buttonGroup.add(btHapus, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final JLabel label8 = new JLabel();
        Font label8Font = this.$$$getFont$$$("Ubuntu Light", -1, -1, label8.getFont());
        if (label8Font != null) label8.setFont(label8Font);
        label8.setForeground(new Color(-1));
        label8.setText("HAPUS");
        btHapus.add(label8, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btCancel = new JPanel();
        btCancel.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        btCancel.setBackground(new Color(-8553984));
        btCancel.setEnabled(true);
        btCancel.setVisible(true);
        buttonGroup.add(btCancel, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final JLabel label9 = new JLabel();
        Font label9Font = this.$$$getFont$$$("Ubuntu Light", -1, -1, label9.getFont());
        if (label9Font != null) label9.setFont(label9Font);
        label9.setForeground(new Color(-1));
        label9.setText("BATAL");
        btCancel.add(label9, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btUbah = new JPanel();
        btUbah.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        btUbah.setBackground(new Color(-8684288));
        btUbah.setEnabled(true);
        buttonGroup.add(btUbah, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final JLabel label10 = new JLabel();
        Font label10Font = this.$$$getFont$$$("Ubuntu Light", -1, -1, label10.getFont());
        if (label10Font != null) label10.setFont(label10Font);
        label10.setForeground(new Color(-1));
        label10.setText("UBAH");
        btUbah.add(label10, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
