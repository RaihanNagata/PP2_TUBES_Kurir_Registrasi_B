package view.auth;

import model.User;
import dao.UserDao;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChangePassword extends JFrame {
    private JPasswordField txtOldPassword;
    private JPasswordField txtNewPassword;
    private JPasswordField txtConfirmPassword;
    private JButton btnChange;
    private JButton btnCancel;
    private JPanel mainPanel;

    public ChangePassword() {
        initializeFrame();
        createComponents();
        setupLayout();
        setupListeners();
        add(mainPanel);
    }

    private void initializeFrame() {
        setTitle("Ubah Password");
        setSize(400, 300); // Sesuaikan ukuran jendela untuk kenyamanan
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void createComponents() {
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        txtOldPassword = new JPasswordField(20);
        txtNewPassword = new JPasswordField(20);
        txtConfirmPassword = new JPasswordField(20);
        btnChange = new JButton("Ubah Password");
        btnCancel = new JButton("Batal");
    }

    private void setupLayout() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL; // Isi ruang horizontal yang tersedia

        JLabel lblTitle = new JLabel("Ubah Password");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(lblTitle, gbc);

        addFormField("Password Lama:", txtOldPassword, gbc, 1);
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
        btnChange.addActionListener(_ -> handleChangePassword());
        btnCancel.addActionListener(_ -> this.dispose());
    }

    private void handleChangePassword() {
        String oldPassword = new String(txtOldPassword.getPassword());
        String newPassword = new String(txtNewPassword.getPassword());
        String confirmPassword = new String(txtConfirmPassword.getPassword());

        if (oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Semua kolom harus diisi!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (!newPassword.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this,
                    "Password baru dan konfirmasi password tidak sesuai!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            txtNewPassword.setText("");
            txtConfirmPassword.setText("");
            return;
        }

        User user = User.getCurrentUser(); // Dapatkan pengguna yang sedang login
        if (user != null && user.authenticate(user.getUsername(), oldPassword)) {
            user.setPassword(newPassword);
            UserDao userDao = new UserDao();
            userDao.update(user);

            JOptionPane.showMessageDialog(this,
                    "Password berhasil diubah!",
                    "Sukses",
                    JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                    "Password lama salah!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            txtOldPassword.setText("");
            txtOldPassword.requestFocus();
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new ChangePassword().setVisible(true);
        });
    }
}
