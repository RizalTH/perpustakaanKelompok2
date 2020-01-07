package frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainMenu {
    private JPanel parentPane;
    private JPanel leftPane;
    private JPanel rightPane;
    private JPanel head;
    private JPanel titleBar;
    private JPanel contentPane;
    private JPanel controlButton;
    private JPanel menuGroup;
    private JPanel btDashboard;
    private JPanel btClose;
    private JLabel labelClose;
    private JPanel btPinjaman;
    private JPanel btMax;
    private JPanel btMin;
    private JPanel btAnggota;
    private JPanel btPetugas;
    private JPanel btBuku;
    private String current;
    JFrame frame = new JFrame("Main");
    Dashboard dashboardForm = new Dashboard();
    Pinjaman pinjamanForm = new Pinjaman();
    GUIAnggota GUIAnggotaForm = new GUIAnggota();



    public MainMenu() {

        //  --------------------------------------------------------------------
        //  Button DASHBOARD                                                  //
        //  --------------------------------------------------------------------
        //
        btDashboard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                if (current != "dashboard") {
                    super.mouseEntered(mouseEvent);
                    btDashboard.setBackground(new Color(100, 100,100));
                }
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                if (current != "dashboard") {
                    super.mouseExited(mouseEvent);
                    btDashboard.setBackground(new Color(45, 45,45));
                }
            }

            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                current = "dashboard";
                removeButtonState();
                btDashboard.setBackground(new Color(0, 81,206));

                contentPane.removeAll();
                contentPane.add(dashboardForm.getParent());
                contentPane.revalidate();
                contentPane.repaint();
            }
        });



        //  --------------------------------------------------------------------
        //  Button PINJAMAN                                                   //
        //  --------------------------------------------------------------------
        //
        btPinjaman.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                if (current != "pinjaman") {
                    super.mouseEntered(mouseEvent);
                    btPinjaman.setBackground(new Color(100, 100,100));
                }
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                if (current != "pinjaman") {
                    super.mouseExited(mouseEvent);
                    btPinjaman.setBackground(new Color(45, 45,45));
                }
            }

            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                current = "pinjaman";
                removeButtonState();
                btPinjaman.setBackground(new Color(0, 81,206));

                contentPane.removeAll();
                contentPane.add(pinjamanForm.getParent());
                contentPane.revalidate();
                contentPane.repaint();
            }
        });



        //  --------------------------------------------------------------------
        //  Button ANGGOTA                                                    //
        //  --------------------------------------------------------------------
        //
        btAnggota.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                if (current != "anggota") {
                    super.mouseEntered(mouseEvent);
                    btAnggota.setBackground(new Color(100, 100,100));
                }
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                if (current != "anggota") {
                    super.mouseExited(mouseEvent);
                    btAnggota.setBackground(new Color(45, 45,45));
                }
            }

            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                current = "anggota";
                removeButtonState();
                btAnggota.setBackground(new Color(0, 81,206));

                contentPane.removeAll();
                contentPane.add(GUIAnggotaForm.getParent());
                contentPane.revalidate();
                contentPane.repaint();
            }
        });



    //  --------------------------------------------------------------------
    //  Button CLOSE                                                      //
    //  --------------------------------------------------------------------
    //
        btClose.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered(mouseEvent);
                btClose.setBackground(new Color(160, 7,0));
                labelClose.setForeground(new Color(212, 224,231));
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited(mouseEvent);
                btClose.setBackground(new Color(212, 224,231));
                labelClose.setForeground(new Color(160, 7,0));
            }

            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                System.exit(0);

            }

        });

    }



    public void open() {

        frame.setContentPane(new MainMenu().parentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);

        MainMenu.FrameDragListener frameDragListener = new MainMenu.FrameDragListener(frame);
        frame.addMouseListener(frameDragListener);
        frame.addMouseMotionListener(frameDragListener);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

    }


    //  ==============================================================
    //  Frame drag listener class
    //  ==============================================================
    public static class FrameDragListener extends MouseAdapter {
        private final JFrame frame;
        private Point mouseDownCompCoords = null;

        public FrameDragListener(JFrame frame) {
            this.frame = frame;
        }
        public void mouseReleased(MouseEvent e) {
            mouseDownCompCoords = null;
        }
        public void mousePressed(MouseEvent e) {
            mouseDownCompCoords = e.getPoint();
        }
        public void mouseDragged(MouseEvent e) {
            Point currCoords = e.getLocationOnScreen();
            frame.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
        }
    }


    public void removeButtonState(){
        btDashboard.setBackground(new Color(45, 45,45));
        btPinjaman.setBackground(new Color(45, 45,45));
        btAnggota.setBackground(new Color(45, 45,45));
        btBuku.setBackground(new Color(45, 45,45));
        btPetugas.setBackground(new Color(45, 45,45));

    }

}
