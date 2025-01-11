/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.dashboard;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Haida
 */
public class FramePrimary extends JFrame {
    protected JPanel panelContent;
    private JPanel curPanel;
    public FramePrimary() {
        // Tampilkan tomobol exit di pojok kanan atas
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Definisikan ukuran halaman serta jenis layout
        this.setSize(600, 600);
        this.setLayout(new BorderLayout());
        
        // Definisikan berbagai komponen JFrame yang akan ditampilkan
        JButton btnDashboard = new JButton("Dashboard");
        JButton btnAccount = new JButton("Account");
        JPanel panelBtn = new JPanel();
        
        // Definisikan tata letak panel button
        this.add(panelBtn, BorderLayout.WEST);
        
        // Definisikan jenis layout serta tata letak tiap-tiap komponen pada panel button
        panelBtn.setLayout(new GridLayout(10, 1));
        panelBtn.setBackground(Color.gray);
        panelBtn.add(btnDashboard);
        panelBtn.add(btnAccount);
        
        // Panggil kelas dashboard, definisikan sebagai current panel, lalu tetapkan tata letak panelContent yang ada di dalamnya
        //FrameDashboard dashboard = new FrameDashboard();
        //curPanel = dashboard.panelContent;
        //this.add(curPanel, BorderLayout.CENTER);
        
        // Definisikan perilaku tiap-tiap button
        btnDashboard.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Jika sebelumnya ada panelContent yang mengisi
                if (curPanel != null) {
                    // Hapus panelContent tersebut
                    FramePrimary.this.remove(curPanel);
                }
                // Panggil kelas FrameDashboard, lalu definisikan sebagai current panel
                FrameDashboard dashboard = new FrameDashboard();
                curPanel = dashboard.panelContent;
                
                // Reload panelContent berdasarkan currentPanel yang terakhir
                FramePrimary.this.add(curPanel, BorderLayout.CENTER);
                FramePrimary.this.revalidate();
                FramePrimary.this.repaint();
            }
        });
        
        btnAccount.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Jika sebelumnya ada panelContent yang mengisi
                if (curPanel != null) {
                    // Hapus panelContent tersebut
                    FramePrimary.this.remove(curPanel);
                }
                // Panggil kelas FrameAccount, lalu definisikan sebagai current panel
                FrameAccount account = new FrameAccount(FramePrimary.this);
                curPanel = account.panelContent;
                
                // Reload panelContent berdasarkan currentPanel yang terakhir
                FramePrimary.this.add(curPanel, BorderLayout.CENTER);
                FramePrimary.this.revalidate();
                FramePrimary.this.repaint();
            }
        });
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                FramePrimary app = new FramePrimary();
                app.setVisible(true);
            }
        });
    }
}
