/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.dashboard;

import model.User;
import dao.UserDao;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Haida
 */
public class FrameDashboard extends FramePrimary {
    public FrameDashboard() {
        super.panelContent = new JPanel();
        panelContent.setLayout(new BorderLayout());
        
        JLabel lJudul = new JLabel("Dashboard", JLabel.CENTER);
        JLabel lContent = new JLabel("Selamat datang, " + super.user.getName(), JLabel.CENTER);
        
        panelContent.add(lJudul, BorderLayout.NORTH);
        panelContent.add(lContent, BorderLayout.CENTER);
    }
}
