package frontend;

import backend.Buku;
import backend.DetilPeminjaman;
import backend.Peminjaman;
import backend.Temp;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class GUIPeminjaman {
    private JPanel parentPane;
    private JPanel buttonGroup;
    private JPanel btTambah;
    private JTextField idAnggotaTextField;
    private JTextField durasiTextField;
    private JTable tableBuku;
    private JTable tablePinjaman;
    private JTable tableTemp;
    private JPanel btAddToList;
    private JPanel btUndoAdd;
    private JPanel btReturned;

    DefaultTableModel modelBuku = new DefaultTableModel();
    DefaultTableModel modelTemp = new DefaultTableModel();

    //  Class Konstruktor
    public GUIPeminjaman() {

        retrieve();

        btReturned.setVisible(false);
        btAddToList.setVisible(false);
        btUndoAdd.setVisible(false);

        tableBuku.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                btAddToList.setVisible(true);
                btUndoAdd.setVisible(false);
                btReturned.setVisible(false);
                btTambah.setVisible(true);
            }

        });

        tableTemp.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                btAddToList.setVisible(false);
                btUndoAdd.setVisible(true);
                btReturned.setVisible(false);
                btTambah.setVisible(true);
            }

        });

        tablePinjaman.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);

                String status = tablePinjaman.getValueAt(tablePinjaman.getSelectedRow(), 5).toString();

                if (status.equals("Sudah dikembalikan")) {
                    btAddToList.setVisible(false);
                    btUndoAdd.setVisible(false);
                    btReturned.setVisible(false);
                    btTambah.setVisible(true);

                } else {
                    btAddToList.setVisible(false);
                    btUndoAdd.setVisible(false);
                    btReturned.setVisible(true);
                    btTambah.setVisible(true);
                }

            }

        });

        //  Button Add to list queue
        //  --------------------------
        btAddToList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);

                //  Ambil Data
                Integer selectedRow = tableBuku.getSelectedRow();
                Integer stokBefore = Integer.parseInt(tableBuku.getValueAt(selectedRow, 5).toString());
                Integer stokAfter = stokBefore - 1;
                String id = tableBuku.getValueAt(tableBuku.getSelectedRow(), 0).toString();
                String judul = tableBuku.getValueAt(tableBuku.getSelectedRow(), 1).toString();


                if ((stokBefore > 0) && (new Temp().ifNotExist(id))) {

                    if (new Temp().create(id, judul)) {
                        if (new Buku().stockSet(id, stokAfter.toString())) {
                        }
                    }


                } else {
                    JOptionPane.showMessageDialog(null, "Buku sudah ada atau stok buku habis.");
                }

                //  Perbarui table
                retrieve();
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered(mouseEvent);
                btAddToList.setBackground(new Color(156, 98, 0));
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited(mouseEvent);
                btAddToList.setBackground(new Color(125, 79, 0));
            }
        });


        //  Button Undo add to list
        //  --------------------------
        btUndoAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);

                //  Ambil Data
                String id = tableTemp.getValueAt(tableTemp.getSelectedRow(), 0).toString();

                Integer stokBefore = new Buku().stockGet(id);
                Integer stokAfter = stokBefore + 1;

                if (new Temp().delete(id)) {
                    if (new Buku().stockSet(id, stokAfter.toString())) {
                    }

                    //  Perbarui table
                    retrieve();

                }
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered(mouseEvent);
                btUndoAdd.setBackground(new Color(163, 0, 15));
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited(mouseEvent);
                btUndoAdd.setBackground(new Color(125, 0, 12));
            }
        });


        //  Button Tambah Peminjaman
        //  --------------------------
        btTambah.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);

                Integer count = tableTemp.getRowCount();

                // create instance of Random class
                Random rand = new Random();

                // Generate random integers in range 0 to 999
                Integer id_peminjaman = rand.nextInt(1000000000);

                //  Jika tidak ada buku di database temp
                if (count == 0) {
                    JOptionPane.showMessageDialog(null, "Silahkan pilih dulu bukunya.");

                    //  Jika sudah memilih buku
                } else {

                    //  Add ke database peminjaman
                    if (new Peminjaman().create(id_peminjaman.toString(), idAnggotaTextField.getText(), durasiTextField.getText())) {
                        for (int i = 0; i < count; i++) {

                            Integer id_buku = Integer.parseInt(tableTemp.getValueAt(i, 0).toString());

                            //  Tambah ke table detilpeminjaman
                            if (new DetilPeminjaman().create(id_peminjaman.toString(), id_buku.toString())) {

                            }
                        }

                        if (new Temp().truncate()) {
                            retrieve();
                            JOptionPane.showMessageDialog(null, "Berhasil Ditambah.");
                        }
                    }


                }

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered(mouseEvent);
                btTambah.setBackground(new Color(0, 57, 146));
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited(mouseEvent);
                btTambah.setBackground(new Color(0, 49, 125));
            }
        });


        btReturned.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);

                //  Ambil Data
                String id = tablePinjaman.getValueAt(tablePinjaman.getSelectedRow(), 0).toString();

                if (new Peminjaman().sudahDikembalikan(id)) {
                    if (new DetilPeminjaman().balikinStock(id)) {
                        JOptionPane.showMessageDialog(null, "Buku berhasil dikembalikan.");
                        retrieve();
                    }
                }

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered(mouseEvent);
                btReturned.setBackground(new Color(0, 153, 11));
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited(mouseEvent);
                btReturned.setBackground(new Color(0, 125, 9));
            }
        });
    }


    public JPanel getParent() {
        return parentPane;
    }

    //  Retrieve data
    public void retrieve() {
        DefaultTableModel modelPinjaman = new Peminjaman().read();
        DefaultTableModel modelBuku = new Buku().read();
        DefaultTableModel modelTemp = new Temp().read();


        tablePinjaman.setModel(modelPinjaman);
        tableBuku.setModel(modelBuku);
        tableTemp.setModel(modelTemp);
    }

    private void addToList() {


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
        parentPane.setEnabled(true);
        parentPane.setMinimumSize(new Dimension(-1, -1));
        parentPane.setPreferredSize(new Dimension(-1, -1));
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-2826009));
        parentPane.add(panel1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        panel1.add(scrollPane1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        tablePinjaman = new JTable();
        tablePinjaman.setBackground(new Color(-2826009));
        tablePinjaman.setCellSelectionEnabled(false);
        tablePinjaman.setColumnSelectionAllowed(false);
        tablePinjaman.setEditingColumn(-1);
        tablePinjaman.setEditingRow(-1);
        tablePinjaman.setEnabled(true);
        Font tablePinjamanFont = this.$$$getFont$$$("Ubuntu Light", -1, -1, tablePinjaman.getFont());
        if (tablePinjamanFont != null) tablePinjaman.setFont(tablePinjamanFont);
        scrollPane1.setViewportView(tablePinjaman);
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(1, 1, new Insets(10, 0, 0, 0), -1, -1));
        panel2.setBackground(new Color(-2826009));
        parentPane.add(panel2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel3 = new JPanel();
        panel3.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), 10, -1));
        panel3.setBackground(new Color(-2826009));
        panel2.add(panel3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JScrollPane scrollPane2 = new JScrollPane();
        panel3.add(scrollPane2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        tableBuku = new JTable();
        tableBuku.setBackground(new Color(-2826009));
        tableBuku.setCellSelectionEnabled(false);
        tableBuku.setColumnSelectionAllowed(false);
        tableBuku.setEditingColumn(-1);
        tableBuku.setEditingRow(-1);
        tableBuku.setEnabled(true);
        Font tableBukuFont = this.$$$getFont$$$("Ubuntu Light", -1, -1, tableBuku.getFont());
        if (tableBukuFont != null) tableBuku.setFont(tableBukuFont);
        scrollPane2.setViewportView(tableBuku);
        final JPanel panel4 = new JPanel();
        panel4.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel4.setBackground(new Color(-2826009));
        panel3.add(panel4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), -1, 10));
        panel5.setBackground(new Color(-2826009));
        panel4.add(panel5, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final JPanel panel6 = new JPanel();
        panel6.setLayout(new GridLayoutManager(2, 3, new Insets(0, 0, 0, 0), 5, 5));
        panel6.setBackground(new Color(-2826009));
        Font panel6Font = this.$$$getFont$$$("Ubuntu Light", -1, -1, panel6.getFont());
        if (panel6Font != null) panel6.setFont(panel6Font);
        panel6.setForeground(new Color(-12828863));
        panel5.add(panel6, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, 1, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, new Dimension(150, -1), new Dimension(150, -1), null, 0, false));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$("Ubuntu Light", -1, -1, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-12369085));
        label1.setText("ID Anggota");
        panel6.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        idAnggotaTextField = new JTextField();
        idAnggotaTextField.setHorizontalAlignment(2);
        idAnggotaTextField.setMargin(new Insets(2, 6, 2, 6));
        panel6.add(idAnggotaTextField, new GridConstraints(0, 1, 1, 2, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$("Ubuntu Light", -1, -1, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setForeground(new Color(-12369085));
        label2.setText("Durasi Pinjam");
        panel6.add(label2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JPanel panel7 = new JPanel();
        panel7.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), 0, -1));
        panel7.setBackground(new Color(-2826009));
        panel6.add(panel7, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setForeground(new Color(-13290187));
        label3.setHorizontalAlignment(2);
        label3.setText("Hari");
        panel7.add(label3, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        durasiTextField = new JTextField();
        durasiTextField.setHorizontalAlignment(0);
        panel7.add(durasiTextField, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, new Dimension(35, -1), new Dimension(35, -1), null, 0, false));
        final JPanel panel8 = new JPanel();
        panel8.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel8.setBackground(new Color(-2826009));
        panel8.setEnabled(false);
        panel5.add(panel8, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JScrollPane scrollPane3 = new JScrollPane();
        panel8.add(scrollPane3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        tableTemp = new JTable();
        tableTemp.setPreferredScrollableViewportSize(new Dimension(50, 400));
        tableTemp.setPreferredSize(new Dimension(150, 40));
        scrollPane3.setViewportView(tableTemp);
        buttonGroup = new JPanel();
        buttonGroup.setLayout(new GridLayoutManager(1, 4, new Insets(10, 0, 0, 0), 15, -1));
        buttonGroup.setBackground(new Color(-2826009));
        parentPane.add(buttonGroup, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(-1, 60), new Dimension(-1, 60), 0, false));
        btTambah = new JPanel();
        btTambah.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        btTambah.setBackground(new Color(-16764547));
        btTambah.setEnabled(true);
        buttonGroup.add(btTambah, new GridConstraints(0, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        Font label4Font = this.$$$getFont$$$("Ubuntu Light", -1, -1, label4.getFont());
        if (label4Font != null) label4.setFont(label4Font);
        label4.setForeground(new Color(-1));
        label4.setText("PINJAMAN BARU");
        btTambah.add(label4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btReturned = new JPanel();
        btReturned.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        btReturned.setBackground(new Color(-16745207));
        btReturned.setEnabled(true);
        btReturned.setVisible(true);
        buttonGroup.add(btReturned, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        Font label5Font = this.$$$getFont$$$("Ubuntu Light", -1, -1, label5.getFont());
        if (label5Font != null) label5.setFont(label5Font);
        label5.setForeground(new Color(-1));
        label5.setText("PINJAMAN DIKEMBALIKAN");
        btReturned.add(label5, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btAddToList = new JPanel();
        btAddToList.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        btAddToList.setBackground(new Color(-8564992));
        btAddToList.setEnabled(true);
        btAddToList.setVisible(true);
        buttonGroup.add(btAddToList, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        Font label6Font = this.$$$getFont$$$("Ubuntu Light", -1, -1, label6.getFont());
        if (label6Font != null) label6.setFont(label6Font);
        label6.setForeground(new Color(-1));
        label6.setText("<  MASUKKAN KE LIST PINJAM");
        btAddToList.add(label6, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btUndoAdd = new JPanel();
        btUndoAdd.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        btUndoAdd.setBackground(new Color(-8585204));
        btUndoAdd.setEnabled(true);
        btUndoAdd.setVisible(true);
        buttonGroup.add(btUndoAdd, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        Font label7Font = this.$$$getFont$$$("Ubuntu Light", -1, -1, label7.getFont());
        if (label7Font != null) label7.setFont(label7Font);
        label7.setForeground(new Color(-1));
        label7.setText("NGGAK JADI PINJEM INI  >");
        btUndoAdd.add(label7, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
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
