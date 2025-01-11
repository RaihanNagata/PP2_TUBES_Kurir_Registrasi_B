/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.dashboard;

import model.User;
import dao.UserDao;
import java.awt.Window;
import view.auth.Login;
import java.awt.event.*;
import java.io.File;
import java.util.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 *
 * @author Haida
 */
public class FrameAccount extends FramePrimary {
    private FramePrimary parent;
    private List<User> data;
    private UserDao userDao;
    private UserTableModel tableModel;
    public FrameAccount(FramePrimary parent) {
        super.panelContent = new JPanel();
        
        this.parent = parent;
        this.userDao = new UserDao();
        this.data = userDao.findAll();
        
        JLabel lJudul = new JLabel("Account", JLabel.CENTER);
        JTable table = new JTable();
        tableModel = new UserTableModel(data);
        JScrollPane scrollableTable = new JScrollPane(table);
        table.setModel(tableModel);
        JButton btnDelAcc = new JButton("Hapus Akun");
        JButton btnAddPhoto = new JButton("Tambah Gambar");
        JButton btnLogout = new JButton("Logout");
        
        panelContent.add(lJudul);
        panelContent.add(scrollableTable);
        panelContent.add(btnDelAcc);
        panelContent.add(btnAddPhoto);
        panelContent.add(btnLogout);
        
        btnDelAcc.addActionListener(_ -> delAcc());
        btnAddPhoto.addActionListener(_ -> addPhoto());
        btnLogout.addActionListener(_ -> logout());
    }
    
    private void delAcc() {
        User user = data.get(data.size() - 1);
        userDao.delete(user);

        JOptionPane.showMessageDialog(null,
            "Akun milik " + user.getName() + " berhasil di hapus",
            "Delete Berhasil",
            JOptionPane.INFORMATION_MESSAGE);

        parent.dispose();
        new Login().setVisible(true);
    }
    
    public void addPhoto() {
        JTextField ktp = new JTextField();
        JTextField kk = new JTextField();
        User user = data.get(data.size() - 1);
        Object[] message = {
            "KTP:", ktp,
            "KK:", kk
        };

        int option = JOptionPane.showConfirmDialog(null, message, "Tambah Gambar", JOptionPane.OK_CANCEL_OPTION);
        
        if (option == JOptionPane.OK_OPTION) {
            File fktp = new File(ktp.getText().replace("\"", ""));
            File fkk = new File(kk.getText().replace("\"", ""));
            if ((fktp.exists() && !fktp.isDirectory()) && (fkk.exists() && !fkk.isDirectory())) {
                user.updatePhoto(user.getId(), fktp.getPath(), fkk.getPath());
                JOptionPane.showMessageDialog(null,
                    "File KTP atau KK milik " + user.getName() + " berhasil ditambahkan",
                    "Update Berhasil",
                    JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                    "File KTP atau KK milik " + user.getName() + " tidak ada",
                    "Update Gagal",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        }
        
        this.revalidate();
        this.repaint();
    }
    
    public void logout() {
        parent.dispose(); // Dispose the frame
        new Login().setVisible(true); // Launch Login frame
    }
}
