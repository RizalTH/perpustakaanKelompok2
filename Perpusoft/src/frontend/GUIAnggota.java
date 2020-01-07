package frontend;

import backend.Anggota;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUIAnggota {
    private JTable tableAnggota;
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
                if (new backend.Anggota().create(
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
                    int index = tableAnggota.getSelectedRow();
                    String id = tableAnggota.getValueAt(index, 0).toString();

                    if (new backend.Anggota().delete(id)) {
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
                String nama = tableAnggota.getValueAt(tableAnggota.getSelectedRow(), 1).toString();
                String jenis_kelamin = tableAnggota.getValueAt(tableAnggota.getSelectedRow(), 2).toString();
                String tanggal_lahir = tableAnggota.getValueAt(tableAnggota.getSelectedRow(), 3).toString();
                String tempat_lahir = tableAnggota.getValueAt(tableAnggota.getSelectedRow(), 4).toString();
                String alamat = tableAnggota.getValueAt(tableAnggota.getSelectedRow(), 5).toString();
                String nohp = tableAnggota.getValueAt(tableAnggota.getSelectedRow(), 6).toString();

                //  Masukin ke text field
                namaTextField.setText(nama);
                if( jenis_kelamin.equals("Laki - Laki") ){
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
                int index = tableAnggota.getSelectedRow();
                String id = tableAnggota.getValueAt(index, 0).toString();

                if (new backend.Anggota().update(id, namaTextField.getText(), jenisKelaminGroup.getSelection().getActionCommand(), tglLahirTextField.getText(), tempatLahirTextField.getText(), alamatTextField.getText(), noHpTextField.getText()  )) {
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

    public JPanel getParent() {
        return parentPane;
    }

    //  Retrieve data
    private void retrieve()
    {

        DefaultTableModel dm = new backend.Anggota().read();
        dm.isCellEditable(0,0);
        tableAnggota.setModel(dm);
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

}
