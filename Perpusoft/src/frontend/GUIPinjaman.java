package frontend;

import backend.Buku;
import backend.Temp;
import perpusoft.GlobalConfig;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.FocusAdapter;import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class GUIPinjaman {
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
    public GUIPinjaman() {

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
                btAddToList.setVisible(false);
                btUndoAdd.setVisible(false);
                btReturned.setVisible(true);
                btTambah.setVisible(true);
            }

        });

        btAddToList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);

                //  Ambil Data
                Integer selectedRow = tableBuku.getSelectedRow();
                Integer stokBefore = Integer.parseInt( tableBuku.getValueAt(selectedRow, 5).toString() );
                Integer stokAfter = stokBefore - 1;
                String id = tableBuku.getValueAt(tableBuku.getSelectedRow(), 0).toString();
                String judul = tableBuku.getValueAt(tableBuku.getSelectedRow(), 1).toString();


                if ( (stokBefore > 0) && (new Temp().ifNotExist(id)) ){

                    if (new backend.Temp().create(id, judul )) {
                        if(new backend.Buku().stockSet(id, stokAfter.toString()) ){
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
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited(mouseEvent);
            }
        });

        btUndoAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);

                //  Ambil Data
                Integer selectedRow = tableTemp.getSelectedRow();
                String id = tableTemp.getValueAt(tableTemp.getSelectedRow(), 0).toString();
                String judul = tableTemp.getValueAt(tableTemp.getSelectedRow(), 1).toString();

                Integer stokBefore = new backend.Buku().stockGet(id);
                Integer stokAfter = stokBefore + 1;


                if (new backend.Temp().delete(id)){
                    if(new backend.Buku().stockSet(id, stokAfter.toString()) ){

                    }

                    //  Perbarui table
                    retrieve();

                } else {

                }
            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered(mouseEvent);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited(mouseEvent);
            }
        });

        btTambah.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);

                Integer count = tableTemp.getRowCount();

                // create instance of Random class
                Random rand = new Random();

                // Generate random integers in range 0 to 999
                Integer id_peminjaman = rand.nextInt(1000000000);

                if (count == 0) {

                } else {
                    if (new backend.Pinjaman().create(
                            id_peminjaman.toString(),
                            idAnggotaTextField.getText(),
                            durasiTextField.getText()
                    )) {

                        retrieve();
                    }

                    JOptionPane.showMessageDialog(null, "Berhasil Ditambah.");

                }

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered(mouseEvent);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited(mouseEvent);
            }
        });


        btReturned.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered(mouseEvent);
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited(mouseEvent);
            }
        });
    }


    public JPanel getParent() {
        return parentPane;
    }

    //  Retrieve data
    public void retrieve()
    {
        DefaultTableModel tPinjaman = new backend.Pinjaman().read();
        DefaultTableModel modelBuku = new backend.Buku().read();
        DefaultTableModel modelTemp = new backend.Temp().read();


        tablePinjaman.setModel(tPinjaman);
        tableBuku.setModel(modelBuku);
        tableTemp.setModel(modelTemp);
    }

    private void addToList(){


    }

}
