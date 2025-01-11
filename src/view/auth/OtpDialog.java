package view.auth;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class OtpDialog extends JDialog {
    private final JTextField txtOtp;
    private boolean isVerified = false;
    private final String correctOtp;

    public OtpDialog(JFrame parent, String otp) {
        super(parent, "Verifikasi OTP", true);
        this.correctOtp = otp;
        
        // Inisialisasi komponen
        txtOtp = new JTextField(6);
        txtOtp.setFont(new Font("Arial", Font.BOLD, 20));
        txtOtp.setHorizontalAlignment(JTextField.CENTER);
        
        // Setup layout utama
        setLayout(new BorderLayout(10, 10));
        
        // Panel utama dengan padding
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Label instruksi
        JLabel lblInstruksi = new JLabel("Masukkan kode OTP yang dikirim ke email:");
        lblInstruksi.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // Panel untuk text field
        JPanel inputPanel = new JPanel();
        inputPanel.add(txtOtp);
        
        // Panel tombol
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnVerify = new JButton("Verifikasi");
        JButton btnCancel = new JButton("Batal");
        buttonPanel.add(btnVerify);
        buttonPanel.add(btnCancel);
        
        // Tambahkan komponen ke panel utama
        mainPanel.add(lblInstruksi);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(inputPanel);
        mainPanel.add(Box.createVerticalStrut(10));
        mainPanel.add(buttonPanel);
        
        // Tambahkan panel utama ke dialog
        add(mainPanel, BorderLayout.CENTER);
        
        // Event handlers
        btnVerify.addActionListener(e -> verifyOtp());
        btnCancel.addActionListener(e -> {
            isVerified = false;
            dispose();
        });
        
        txtOtp.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    verifyOtp();
                }
            }
        });
        
        // Setup dialog
        setSize(300, 200);
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        
        // Set focus ke text field
        SwingUtilities.invokeLater(() -> txtOtp.requestFocusInWindow());
    }
    
    private void verifyOtp() {
        String inputOtp = txtOtp.getText().trim();
        if (inputOtp.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Masukkan kode OTP!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        if (inputOtp.equals(correctOtp)) {
            isVerified = true;
            JOptionPane.showMessageDialog(this,
                    "Verifikasi OTP berhasil!",
                    "Sukses",
                    JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Kode OTP salah! Silakan coba lagi.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            txtOtp.setText("");
            txtOtp.requestFocus();
        }
    }
    
    public boolean isVerified() {
        return isVerified;
    }
} 