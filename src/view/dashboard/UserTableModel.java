/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.dashboard;

import java.util.*;
import javax.swing.table.AbstractTableModel;
import model.User;

/**
 *
 * @author Haida
 */
class UserTableModel extends AbstractTableModel {
    private final String[] columnNames = {"Id", "Username", "Name", "Email", "No Telp", "Jenis Kelamin", "Alamat"};
    private final List<User> data;
    
    public UserTableModel(List<User> data) {
        this.data = data;
    }
    
    public int getColumnCount() {
        return columnNames.length;
    }
    
    public int getRowCount() {
        return data.size();
    }
    
    public String getColumnName(int col) {
        return columnNames[col];
    }
    
    public Object getValueAt(int row, int col) {
        User rowItem = data.get(row);
        String value = "";
        switch(col) {
            case 0:
                value = Integer.toString(rowItem.getId());
                break;
            case 1:
                value = rowItem.getUsername();
                break;
            case 2:
                value = rowItem.getName();
                break;
            case 3:
                value = rowItem.getEmail();
                break;
            case 4:
                value = rowItem.getNoTelp();
                break;
            case 5:
                value = rowItem.getJenisKelamin();
                break;
            case 6:
                value = rowItem.getAlamat();
                break;
        }
        return value;
    }
    
    public boolean isCellEditable(int row, int col) {
        return false;
    }
    
    public void add(User value) {
        data.add(value);
        fireTableRowsInserted(data.size() - 1, data.size() - 1);
    }
}
