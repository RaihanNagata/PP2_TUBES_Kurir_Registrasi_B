package view.auth;

import dao.UserDao;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ChangePassword extends JFrame {
    private JPasswordField txtNewPassword;
    private JPasswordField txtConfirmPassword;
    private JButton btnChange;
    private JButton btnCancel;
    private JPanel mainPanel;
    private final String email;

    public ChangePassword(String email) {
        this.email = email;
        initializeFrame();
        createComponents();
        setupLayout();
        setupListeners();
        add(mainPanel);
    }

    private void initializeFrame() {
        setTitle("Ubah Password");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void createComponents() {
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        txtNewPassword = new JPasswordField(20);
        txtConfirmPassword = new JPasswordField(20);
        btnChange = new JButton("Ubah Password");
        btnCancel = new JButton("Batal");
    }

    private void setupLayout() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitle = new JLabel("Ubah Password");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(lblTitle, gbc);

        addFormField("Password Baru:", txtNewPassword, gbc, 2);
        addFormField("Konfirmasi Password:", txtConfirmPassword, gbc, 3);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.add(btnChange);
        buttonPanel.add(btnCancel);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(15, 5, 5, 5);
        mainPanel.add(buttonPanel, gbc);
    }

    private void addFormField(String labelText, JComponent field, GridBagConstraints gbc, int row) {
        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(new JLabel(labelText), gbc);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(field, gbc);
    }

    private void setupListeners() {
        btnChange.addActionListener(this::handleChangePassword);
        btnCancel.addActionListener(_ -> new ForgotPassword().setVisible(true));
    }

    private void handleChangePassword(ActionEvent e) {
        String newPassword = new String(txtNewPassword.getPassword());
        String confirmPassword = new String(txtConfirmPassword.getPassword());

        if (!newPassword.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this,
                    "Password baru dan konfirmasi password tidak sesuai!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            txtNewPassword.setText("");
            txtConfirmPassword.setText("");
            return;
        }

        try {
            UserDao userDao = new UserDao();
            if (userDao.updatePasswordByEmail(email, newPassword)) {
                JOptionPane.showMessageDialog(this, "Password berhasil diubah!", "Sukses",
                        JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                new Login().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Gagal memperbarui password!", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ChangePassword.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + ex.getMessage(), "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}