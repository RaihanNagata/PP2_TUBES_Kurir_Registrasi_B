package view.auth;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import model.User;
import dao.UserDao;
import view.dashboard.*;

public class Login extends JFrame {
    private JTextField txtUserEmail;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton btnRegis;
    private JButton btnCancel;
    private JButton btnForgotPassword;
    private JPanel mainPanel;

    public Login() {
        initializeFrame();
        createComponents();
        setupLayout();
        setupListeners();
        add(mainPanel);
    }

    private void initializeFrame() {
        setTitle("Login Aplikasi");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    private void createComponents() {
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        txtUserEmail = new JTextField(20);
        txtPassword = new JPasswordField(20);
        btnLogin = new JButton("Login");
        btnRegis = new JButton("Register");
        btnCancel = new JButton("Cancel");
        btnForgotPassword = new JButton("Lupa Password");
        // Hilangkan border
        btnForgotPassword.setBorderPainted(false);
        // Hilangkan background
        btnForgotPassword.setFocusPainted(false);
        btnForgotPassword.setContentAreaFilled(false);
        // Set warna teks agar seperti link (opsional)
        btnForgotPassword.setForeground(Color.BLACK);
        // Set kursor menjadi 'hand'
        btnForgotPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    private void setupLayout() {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitle = new JLabel("Login");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(lblTitle, gbc);

        addFormField("Username/Email:", txtUserEmail, gbc, 1);
        addFormField("Password:", txtPassword, gbc, 2);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        buttonPanel.add(btnLogin);
        buttonPanel.add(btnRegis);
        buttonPanel.add(btnCancel);
        buttonPanel.add(btnForgotPassword);

        gbc.gridx = 0;
        gbc.gridy = 3;
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
        btnLogin.addActionListener(_ -> validateLogin());
        btnRegis.addActionListener(_ -> handleRegist());
        btnCancel.addActionListener(_ -> System.exit(0));
        btnForgotPassword.addActionListener(_ -> handleForgotPassword());

        KeyListener enterKeyListener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    validateLogin();
                }
            }
        };

        txtUserEmail.addKeyListener(enterKeyListener);
        txtPassword.addKeyListener(enterKeyListener);
    }

    private void validateLogin() {
        String userInput = txtUserEmail.getText().trim();
        String password = new String(txtPassword.getPassword());

        if (userInput.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Username/Email dan Password tidak boleh kosong!",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }

        User user = User.authenticate(userInput, password);
        if (user != null) {
            JOptionPane.showMessageDialog(this,
                    "Selamat datang, " + user.getName() + "!",
                    "Login Berhasil",
                    JOptionPane.INFORMATION_MESSAGE);

            clearForm();
            this.dispose();
            user.setId(Integer.toString(new UserDao().findIdByEmailOrUsername(userInput)));
            FramePrimary dashboard = new FramePrimary();
            dashboard.setUserLogin(user);
            dashboard.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Username/Email atau Password salah!\nSilakan coba lagi.",
                    "Login Gagal",
                    JOptionPane.ERROR_MESSAGE);
            txtPassword.setText("");
            txtPassword.requestFocus();
        }
    }

    private void handleRegist() {
        dispose();
        new Registrasi().setVisible(true);
    }

    private void handleForgotPassword() {
        dispose();
        new ForgotPassword().setVisible(true);
    }

    private void clearForm() {
        txtUserEmail.setText("");
        txtPassword.setText("");
        txtUserEmail.requestFocus();
    }
}