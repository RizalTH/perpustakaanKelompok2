package frontend;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUIMainMenu {
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

    JFrame frame = new JFrame("GUIMainMenu");
    GUIDashboard guiDashboard = new GUIDashboard();
    GUIPeminjaman guiPinjaman = new GUIPeminjaman();
    GUIAnggota guiAnggota = new GUIAnggota();
    GUIPetugas guiPetugas = new GUIPetugas();
    GUIBuku guiBuku = new GUIBuku();


    //  Class Konstruktor
    public GUIMainMenu() {

        current = "dashboard";
        removeButtonState();
        btDashboard.setBackground(new Color(0, 81, 206));

        contentPane.removeAll();
        contentPane.add(guiDashboard.getParent());
        contentPane.revalidate();
        contentPane.repaint();


        //  --------------------------------------------------------------------
        //  Button DASHBOARD                                                  //
        //  --------------------------------------------------------------------
        //
        btDashboard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                if (current != "dashboard") {
                    super.mouseEntered(mouseEvent);
                    btDashboard.setBackground(new Color(100, 100, 100));
                }
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                if (current != "dashboard") {
                    super.mouseExited(mouseEvent);
                    btDashboard.setBackground(new Color(45, 45, 45));
                }
            }

            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                current = "dashboard";
                removeButtonState();
                btDashboard.setBackground(new Color(0, 81, 206));

                contentPane.removeAll();
                contentPane.add(guiDashboard.getParent());
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
                    btPinjaman.setBackground(new Color(100, 100, 100));
                }
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                if (current != "pinjaman") {
                    super.mouseExited(mouseEvent);
                    btPinjaman.setBackground(new Color(45, 45, 45));
                }
            }

            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                current = "pinjaman";
                removeButtonState();
                btPinjaman.setBackground(new Color(0, 81, 206));

                guiPinjaman.retrieve();
                contentPane.removeAll();
                contentPane.add(guiPinjaman.getParent());
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
                    btAnggota.setBackground(new Color(100, 100, 100));
                }
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                if (current != "anggota") {
                    super.mouseExited(mouseEvent);
                    btAnggota.setBackground(new Color(45, 45, 45));
                }
            }

            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                current = "anggota";
                removeButtonState();
                btAnggota.setBackground(new Color(0, 81, 206));

                contentPane.removeAll();
                contentPane.add(guiAnggota.getParent());
                contentPane.revalidate();
                contentPane.repaint();
            }
        });


        //  --------------------------------------------------------------------
        //  Button PETUGAS                                                    //
        //  --------------------------------------------------------------------
        //
        btPetugas.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                if (current != "petugas") {
                    super.mouseEntered(mouseEvent);
                    btPetugas.setBackground(new Color(100, 100, 100));
                }
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                if (current != "petugas") {
                    super.mouseExited(mouseEvent);
                    btPetugas.setBackground(new Color(45, 45, 45));
                }
            }

            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                current = "petugas";
                removeButtonState();
                btPetugas.setBackground(new Color(0, 81, 206));

                contentPane.removeAll();
                contentPane.add(guiPetugas.getParent());
                contentPane.revalidate();
                contentPane.repaint();
            }
        });


        //  --------------------------------------------------------------------
        //  Button BUKU                                                       //
        //  --------------------------------------------------------------------
        //
        btBuku.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                if (current != "buku") {
                    super.mouseEntered(mouseEvent);
                    btBuku.setBackground(new Color(100, 100, 100));
                }
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                if (current != "buku") {
                    super.mouseExited(mouseEvent);
                    btBuku.setBackground(new Color(45, 45, 45));
                }
            }

            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                current = "buku";
                removeButtonState();
                btBuku.setBackground(new Color(0, 81, 206));

                contentPane.removeAll();
                contentPane.add(guiBuku.getParent());
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
                btClose.setBackground(new Color(160, 7, 0));
                labelClose.setForeground(new Color(212, 224, 231));
            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited(mouseEvent);
                btClose.setBackground(new Color(212, 224, 231));
                labelClose.setForeground(new Color(160, 7, 0));
            }

            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                System.exit(0);

            }

        });

    }


    public void open() {

        frame.setContentPane(new GUIMainMenu().parentPane);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setUndecorated(true);

        GUIMainMenu.FrameDragListener frameDragListener = new GUIMainMenu.FrameDragListener(frame);
        frame.addMouseListener(frameDragListener);
        frame.addMouseMotionListener(frameDragListener);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setVisible(true);

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
        parentPane.setLayout(new GridLayoutManager(1, 2, new Insets(0, 0, 0, 0), 0, 0));
        Font parentPaneFont = this.$$$getFont$$$("Trebuchet MS", -1, -1, parentPane.getFont());
        if (parentPaneFont != null) parentPane.setFont(parentPaneFont);
        parentPane.setMinimumSize(new Dimension(800, 500));
        parentPane.setPreferredSize(new Dimension(800, 500));
        leftPane = new JPanel();
        leftPane.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), 0, 0));
        leftPane.setBackground(new Color(-13816531));
        parentPane.add(leftPane, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, 1, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(200, -1), new Dimension(200, -1), 0, false));
        head = new JPanel();
        head.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), 0, 0));
        head.setBackground(new Color(-13487566));
        leftPane.add(head, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_FIXED, 1, null, new Dimension(-1, 200), null, 0, false));
        final JLabel label1 = new JLabel();
        Font label1Font = this.$$$getFont$$$("Ubuntu Light", Font.BOLD, 26, label1.getFont());
        if (label1Font != null) label1.setFont(label1Font);
        label1.setForeground(new Color(-4144960));
        label1.setHorizontalTextPosition(10);
        label1.setText("Perpusoft");
        head.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_SOUTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        Font label2Font = this.$$$getFont$$$("Ubuntu Light", Font.PLAIN, 12, label2.getFont());
        if (label2Font != null) label2.setFont(label2Font);
        label2.setForeground(new Color(-4144960));
        label2.setHorizontalAlignment(4);
        label2.setHorizontalTextPosition(4);
        label2.setText("<html><center>Software Manajemen<br> Perpustakaan</center></html>");
        head.add(label2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        menuGroup = new JPanel();
        menuGroup.setLayout(new GridLayoutManager(5, 1, new Insets(0, 0, 0, 0), 0, 0));
        menuGroup.setBackground(new Color(-13816531));
        leftPane.add(menuGroup, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        btDashboard = new JPanel();
        btDashboard.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        btDashboard.setBackground(new Color(-13816531));
        menuGroup.add(btDashboard, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, 1, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(-1, 50), null, 0, false));
        final JLabel label3 = new JLabel();
        Font label3Font = this.$$$getFont$$$("Ubuntu Light", -1, -1, label3.getFont());
        if (label3Font != null) label3.setFont(label3Font);
        label3.setForeground(new Color(-1));
        label3.setText("DASHBOARD");
        btDashboard.add(label3, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btPinjaman = new JPanel();
        btPinjaman.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        btPinjaman.setBackground(new Color(-13816531));
        btPinjaman.setEnabled(false);
        menuGroup.add(btPinjaman, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, 1, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(-1, 50), null, 0, false));
        final JLabel label4 = new JLabel();
        Font label4Font = this.$$$getFont$$$("Ubuntu Light", -1, -1, label4.getFont());
        if (label4Font != null) label4.setFont(label4Font);
        label4.setForeground(new Color(-1));
        label4.setText("PINJAMAN");
        btPinjaman.add(label4, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btAnggota = new JPanel();
        btAnggota.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        btAnggota.setBackground(new Color(-13816531));
        menuGroup.add(btAnggota, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, 1, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(-1, 50), null, 0, false));
        final JLabel label5 = new JLabel();
        Font label5Font = this.$$$getFont$$$("Ubuntu Light", -1, -1, label5.getFont());
        if (label5Font != null) label5.setFont(label5Font);
        label5.setForeground(new Color(-1));
        label5.setText("ANGGOTA");
        btAnggota.add(label5, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btPetugas = new JPanel();
        btPetugas.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        btPetugas.setBackground(new Color(-13816531));
        menuGroup.add(btPetugas, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, 1, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(-1, 50), null, 0, false));
        final JLabel label6 = new JLabel();
        Font label6Font = this.$$$getFont$$$("Ubuntu Light", -1, -1, label6.getFont());
        if (label6Font != null) label6.setFont(label6Font);
        label6.setForeground(new Color(-1));
        label6.setText("PETUGAS");
        btPetugas.add(label6, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btBuku = new JPanel();
        btBuku.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        btBuku.setBackground(new Color(-13816531));
        menuGroup.add(btBuku, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, 1, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(-1, 50), null, 0, false));
        final JLabel label7 = new JLabel();
        Font label7Font = this.$$$getFont$$$("Ubuntu Light", -1, -1, label7.getFont());
        if (label7Font != null) label7.setFont(label7Font);
        label7.setForeground(new Color(-1));
        label7.setText("BUKU");
        btBuku.add(label7, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        rightPane = new JPanel();
        rightPane.setLayout(new GridLayoutManager(2, 1, new Insets(0, 0, 0, 0), 0, 0));
        rightPane.setBackground(new Color(-2826009));
        parentPane.add(rightPane, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        titleBar = new JPanel();
        titleBar.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        titleBar.setBackground(new Color(-2826009));
        rightPane.add(titleBar, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, 1, null, new Dimension(-1, 40), null, 0, false));
        controlButton = new JPanel();
        controlButton.setLayout(new GridLayoutManager(1, 3, new Insets(0, 0, 0, 0), -1, -1));
        controlButton.setBackground(new Color(-2826009));
        titleBar.add(controlButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, new Dimension(100, -1), null, 0, false));
        btMin = new JPanel();
        btMin.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        btMin.setBackground(new Color(-2826009));
        controlButton.add(btMin, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setBackground(new Color(-11776948));
        Font label8Font = this.$$$getFont$$$("Ubuntu Light", -1, -1, label8.getFont());
        if (label8Font != null) label8.setFont(label8Font);
        label8.setText("-");
        btMin.add(label8, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btMax = new JPanel();
        btMax.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        btMax.setBackground(new Color(-2826009));
        controlButton.add(btMax, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        final JLabel label9 = new JLabel();
        Font label9Font = this.$$$getFont$$$("Ubuntu Light", -1, -1, label9.getFont());
        if (label9Font != null) label9.setFont(label9Font);
        label9.setForeground(new Color(-11776948));
        label9.setText("O");
        btMax.add(label9, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        btClose = new JPanel();
        btClose.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
        btClose.setBackground(new Color(-2826009));
        btClose.setEnabled(false);
        controlButton.add(btClose, new GridConstraints(0, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        labelClose = new JLabel();
        Font labelCloseFont = this.$$$getFont$$$("Ubuntu Light", -1, -1, labelClose.getFont());
        if (labelCloseFont != null) labelClose.setFont(labelCloseFont);
        labelClose.setForeground(new Color(-6289664));
        labelClose.setText("X");
        btClose.add(labelClose, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout(0, 0));
        contentPane.setBackground(new Color(-2826009));
        rightPane.add(contentPane, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
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


    public void removeButtonState() {
        btDashboard.setBackground(new Color(45, 45, 45));
        btPinjaman.setBackground(new Color(45, 45, 45));
        btAnggota.setBackground(new Color(45, 45, 45));
        btBuku.setBackground(new Color(45, 45, 45));
        btPetugas.setBackground(new Color(45, 45, 45));

    }

}
