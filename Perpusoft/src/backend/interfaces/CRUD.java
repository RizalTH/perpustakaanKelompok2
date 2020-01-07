package backend.interfaces;

import javax.swing.table.DefaultTableModel;

public interface CRUD {
    Boolean create();
    DefaultTableModel read();
    Boolean update();
    Boolean delete();
}
