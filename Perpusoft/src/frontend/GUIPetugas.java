package frontend;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUIPetugas {
    private JPanel parentPane;
    private JTable tablePetugas;
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
    private JTextField jabatanTextField;

    private ButtonGroup jenisKelaminGroup = new ButtonGroup();

    //  Class Konstruktor
    public GUIPetugas() {
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
                if (new backend.Petugas().create(
                        namaTextField.getText(),
                        jenisKelaminGroup.getSelection().getActionCommand(),
                        tglLahirTextField.getText(),
                        tempatLahirTextField.getText(),
                        jabatanTextField.getText(),
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
                    int index = tablePetugas.getSelectedRow();
                    String id = tablePetugas.getValueAt(index, 0).toString();

                    if (new backend.Petugas().delete(id)) {
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
                String nama = tablePetugas.getValueAt(tablePetugas.getSelectedRow(), 1).toString();
                String jenis_kelamin = tablePetugas.getValueAt(tablePetugas.getSelectedRow(), 2).toString();
                String tanggal_lahir = tablePetugas.getValueAt(tablePetugas.getSelectedRow(), 3).toString();
                String tempat_lahir = tablePetugas.getValueAt(tablePetugas.getSelectedRow(), 4).toString();
                String jabatan = tablePetugas.getValueAt(tablePetugas.getSelectedRow(), 5).toString();
                String alamat = tablePetugas.getValueAt(tablePetugas.getSelectedRow(), 6).toString();
                String nohp = tablePetugas.getValueAt(tablePetugas.getSelectedRow(), 7).toString();

                //  Masukin ke text field
                namaTextField.setText(nama);
                if( jenis_kelamin.equals("Laki - Laki") ){
                    lakiLakiRadioButton.setSelected(true);
                } else {
                    perempuanRadioButton.setSelected(true);
                }
                tglLahirTextField.setText(tanggal_lahir);
                tempatLahirTextField.setText(tempat_lahir);
                jabatanTextField.setText(jabatan);
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
                int index = tablePetugas.getSelectedRow();
                String id = tablePetugas.getValueAt(index, 0).toString();

                if (new backend.Petugas().update(id, namaTextField.getText(), jenisKelaminGroup.getSelection().getActionCommand(), tglLahirTextField.getText(), tempatLahirTextField.getText(), jabatanTextField.getText(), alamatTextField.getText(), noHpTextField.getText()  )) {
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
    public JPanel getParent(){
        return parentPane;
    }

    //  Retrieve data
    private void retrieve()
    {

        DefaultTableModel dm = new backend.Petugas().read();
        tablePetugas.setModel(dm);
    }


    //  Clear text field
    private void clearTextField() {
        namaTextField.setText("");
        jenisKelaminGroup.clearSelection();
        tglLahirTextField.setText("");
        tempatLahirTextField.setText("");
        jabatanTextField.setText("");
        alamatTextField.setText("");
        noHpTextField.setText("");
    }

}
