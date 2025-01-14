package view.auth;

import dao.UserDao;
import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class ForgotPassword extends JFrame {
  private JTextField txtEmail;
  private JButton btnSendCode;
  private JButton btnCancel;
  private JPanel mainPanel;

  public ForgotPassword() {
    initializeFrame();
    createComponents();
    setupLayout();
    setupListeners();
    add(mainPanel);
  }

  private void initializeFrame() {
    setTitle("Lupa Password");
    setSize(400, 300);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setLocationRelativeTo(null);
  }

  private void createComponents() {
    mainPanel = new JPanel(new GridBagLayout());
    mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

    txtEmail = new JTextField(20);
    btnSendCode = new JButton("Kirim Kode Verifikasi");
    btnCancel = new JButton("Batal");
  }

  private void setupLayout() {
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.insets = new Insets(5, 5, 5, 5);
    gbc.fill = GridBagConstraints.HORIZONTAL;

    JLabel lblTitle = new JLabel("Lupa Password");
    lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
    gbc.gridx = 0;
    gbc.gridy = 0;
    gbc.gridwidth = 2;
    gbc.anchor = GridBagConstraints.CENTER;
    mainPanel.add(lblTitle, gbc);

    addFormField("Email:", txtEmail, gbc, 2);

    JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
    buttonPanel.add(btnSendCode);
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
    btnSendCode.addActionListener(this::handleSendCode);
    btnCancel.addActionListener(_ -> new Login().setVisible(true));
  }

  private void handleSendCode(ActionEvent e) {
    String email = txtEmail.getText().trim();
    if (email.isEmpty()) {
      JOptionPane.showMessageDialog(this, "Email tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    UserDao userDao = new UserDao();
    int userId = userDao.findIdByEmailOrUsername(email);
    if (userId == 0) {
      JOptionPane.showMessageDialog(this, "Email tidak terdaftar!", "Error", JOptionPane.ERROR_MESSAGE);
      return;
    }

    JOptionPane.showMessageDialog(this,
        "Kode verifikasi telah dikirim ke email anda!\n(Dalam aplikasi nyata, kode ini akan dikirim via email)",
        "Informasi",
        JOptionPane.INFORMATION_MESSAGE);
    this.dispose();
    new ChangePassword(email).setVisible(true);
  }
}