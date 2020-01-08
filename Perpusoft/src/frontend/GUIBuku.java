package frontend;

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

    public JPanel getParent(){
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
                if (new backend.Buku().create(
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
                btTambah.setBackground(new Color(0,69,176));


            }

            //  On Exit Hover
            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited(mouseEvent);
                btTambah.setBackground(new Color(0,49,125));
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

                    if (new backend.Buku().delete(id)) {
                        JOptionPane.showMessageDialog(null, "Berhasil Dihapus");

                        clearTextField();

                        //  Perbarui tabel
                        retrieve();
                    } else
                    {
                        JOptionPane.showMessageDialog(null, "Gagal");
                    }

                }
            }

            //  On Hover
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered(mouseEvent);
                btHapus.setBackground(new Color(171,0,3));
            }

            //  On Hover Exit
            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited(mouseEvent);
                btHapus.setBackground(new Color(125,0,2));

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
                btUbah.setBackground(new Color(168,171,0));
            }

            //  On Hover Exit
            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited(mouseEvent);
                btUbah.setBackground(new Color(123,125,0));
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

                if (new backend.Buku().update(id, judulTextField.getText(), penerbitTextField.getText(), pengarangTextField.getText(), stokAwalTextField.getText(), tahunTerbitTextField.getText()) ) {
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
                btSimpan.setBackground(new Color(0,125,9));
            }

            //  On Hover Ecit
            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited(mouseEvent);
                btSimpan.setBackground(new Color(0,52,11));
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
                btCancel.setBackground(new Color(155,152,0));
            }

            //  On Hover Exit
            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited(mouseEvent);
                btCancel.setBackground(new Color(125,122,0));
            }
        });

    }

    //  Retrieve data
    private void retrieve()
    {
        DefaultTableModel dm = new backend.Buku().read();
        dm.isCellEditable(0,0);
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

}
