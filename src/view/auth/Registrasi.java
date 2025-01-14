package view.auth;

import javax.swing.*;
import model.User;
import util.OtpGenerator;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Registrasi extends JFrame {
    private static final int FIELD_WIDTH = 20;
    private static final int TEXT_AREA_ROWS = 3;
    private final JTextField txtUsername;
    private final JPasswordField txtPassword;
    private final JPasswordField txtConfirmPassword;
    private final JTextField txtNama;
    private final JTextField txtEmail;
    private final JTextField txtNoTelp;
    private final JTextArea txtAlamat;
    private final JRadioButton rbPria;
    private final JRadioButton rbWanita;

    private static final ArrayList<User> users = new ArrayList<>();

    public Registrasi() {
        txtUsername = new JTextField(FIELD_WIDTH);
        txtPassword = new JPasswordField(FIELD_WIDTH);
        txtConfirmPassword = new JPasswordField(FIELD_WIDTH);
        txtNama = new JTextField(FIELD_WIDTH);
        txtEmail = new JTextField(FIELD_WIDTH);
        txtNoTelp = new JTextField(FIELD_WIDTH);
        txtAlamat = new JTextArea(TEXT_AREA_ROWS, FIELD_WIDTH);
        rbPria = new JRadioButton("Pria");
        rbWanita = new JRadioButton("Wanita");

        initializeFrame();
        createMainPanel();
    }

    // Inisialisasi frame utama
    private void initializeFrame() {
        setTitle("Form Registrasi");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    // Membuat panel utama dan komponennya
    private void createMainPanel() {
        JPanel mainPanel = createPanelWithLayout();
        createFormComponents(mainPanel);
        createButtonPanel(mainPanel);
        add(mainPanel);
    }

    private JPanel createPanelWithLayout() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return mainPanel;
    }

    private void createFormComponents(JPanel mainPanel) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Menambahkan judul
        addTitle(mainPanel, gbc);

        // Menambahkan form fields
        addFormFields(mainPanel, gbc);

        // Menambahkan radio buttons untuk jenis kelamin
        addGenderSelection(mainPanel, gbc);

        // Menambahkan text area untuk alamat
        addAddressField(mainPanel, gbc);
    }

    private void addTitle(JPanel mainPanel, GridBagConstraints gbc) {
        JLabel lblTitle = new JLabel("Register");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(lblTitle, gbc);

        // Reset gridwidth
        gbc.gridwidth = 1;
    }

    private void addFormFields(JPanel mainPanel, GridBagConstraints gbc) {
        // Mengatur ukuran font yang konsisten
        Font labelFont = new Font("Arial", Font.PLAIN, 12);
        Font fieldFont = new Font("Arial", Font.PLAIN, 12);

        // Username
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(labelFont);
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(lblUsername, gbc);

        txtUsername.setFont(fieldFont);
        gbc.gridx = 1;
        mainPanel.add(txtUsername, gbc);

        // Password
        JLabel lblPassword = new JLabel("Password:");
        lblPassword.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(lblPassword, gbc);

        txtPassword.setFont(fieldFont);
        gbc.gridx = 1;
        mainPanel.add(txtPassword, gbc);

        // Confirm Password
        JLabel lblConfirmPassword = new JLabel("Konfirmasi Password:");
        lblConfirmPassword.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 3;
        mainPanel.add(lblConfirmPassword, gbc);

        txtConfirmPassword.setFont(fieldFont);
        gbc.gridx = 1;
        mainPanel.add(txtConfirmPassword, gbc);

        // Nama Lengkap
        JLabel lblNama = new JLabel("Nama Lengkap:");
        lblNama.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 4;
        mainPanel.add(lblNama, gbc);

        txtNama.setFont(fieldFont);
        gbc.gridx = 1;
        mainPanel.add(txtNama, gbc);

        // Email
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 5;
        mainPanel.add(lblEmail, gbc);

        txtEmail.setFont(fieldFont);
        gbc.gridx = 1;
        mainPanel.add(txtEmail, gbc);

        // No Telp
        JLabel lblNoTelp = new JLabel("No Telp:");
        lblNoTelp.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 6;
        mainPanel.add(lblNoTelp, gbc);

        txtNoTelp.setFont(fieldFont);
        gbc.gridx = 1;
        mainPanel.add(txtNoTelp, gbc);
    }

    private void addGenderSelection(JPanel mainPanel, GridBagConstraints gbc) {
        JLabel lblGender = new JLabel("Jenis Kelamin:");
        lblGender.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridx = 0;
        gbc.gridy = 7;

        mainPanel.add(lblGender, gbc);

        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        rbPria.setFont(new Font("Arial", Font.PLAIN, 12));
        rbWanita.setFont(new Font("Arial", Font.PLAIN, 12));

        // Membuat button group
        ButtonGroup bgGender = new ButtonGroup();
        bgGender.add(rbPria);
        bgGender.add(rbWanita);

        genderPanel.add(rbPria);
        genderPanel.add(Box.createHorizontalStrut(10)); // Memberikan jarak antara radio button
        genderPanel.add(rbWanita);

        gbc.gridx = 1;
        mainPanel.add(genderPanel, gbc);
    }

    private void addAddressField(JPanel mainPanel, GridBagConstraints gbc) {
        JLabel lblAlamat = new JLabel("Alamat:");
        lblAlamat.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridx = 0;
        gbc.gridy = 8;

        mainPanel.add(lblAlamat, gbc);

        txtAlamat.setFont(new Font("Arial", Font.PLAIN, 12));
        txtAlamat.setLineWrap(true);
        txtAlamat.setWrapStyleWord(true);

        JScrollPane scrollPane = new JScrollPane(txtAlamat);
        scrollPane.setPreferredSize(new Dimension(200, 60));

        gbc.gridx = 1;
        mainPanel.add(scrollPane, gbc);
    }

    private void createButtonPanel(JPanel mainPanel) {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        JButton btnDaftar = createButton("Daftar", _ -> handleRegistration());
        JButton btnLogin = createButton("Login", _ -> handleLogin());
        JButton btnBatal = createButton("Batal", _ -> clearForm());

        buttonPanel.add(btnDaftar);
        buttonPanel.add(btnLogin);
        buttonPanel.add(btnBatal);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 9;

        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(buttonPanel, gbc);
    }

    private JButton createButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.addActionListener(listener);
        return button;
    }

    private void handleRegistration() {
        if (validateInput()) {
            registerUser();
        }
    }

    private void handleLogin() {
        dispose();
        new Login().setVisible(true);
    }

    // Validasi input form
    private boolean validateInput() {
        if (!validateBasicFields())
            return false;
        if (!validateEmail())
            return false;
        if (!validateGender())
            return false;
        return validateAddress();
    }

    private boolean validateBasicFields() {
        if (txtUsername.getText().trim().isEmpty()) {
            showError("Username tidak boleh kosong!");
            return false;
        }

        String password = new String(txtPassword.getPassword());
        String confirmPassword = new String(txtConfirmPassword.getPassword());

        if (password.isEmpty()) {
            showError("Password tidak boleh kosong!");
            return false;
        }

        if (!password.equals(confirmPassword)) {
            showError("Password dan Konfirmasi Password tidak sama!");
            return false;
        }

        if (txtNama.getText().trim().isEmpty()) {
            showError("Nama tidak boleh kosong!");
            return false;
        }

        return true;
    }

    private boolean validateEmail() {
        String email = txtEmail.getText().trim();
        if (email.isEmpty()) {
            showError("Email tidak boleh kosong!");
            return false;
        }
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            showError("Format email tidak valid!");
            return false;
        }
        return true;
    }

    private boolean validateGender() {
        if (!rbPria.isSelected() && !rbWanita.isSelected()) {
            showError("Pilih jenis kelamin!");
            return false;
        }
        return true;
    }

    private boolean validateAddress() {
        if (txtAlamat.getText().trim().isEmpty()) {
            showError("Alamat tidak boleh kosong!");
            return false;
        }
        return true;
    }

    private void registerUser() {

        try {
            // Validasi input
            if (!validateInput()) {
                return;
            }

            String username = txtUsername.getText().trim();
            String password = new String(txtPassword.getPassword());
            String nama = txtNama.getText().trim();
            String email = txtEmail.getText().trim();
            String alamat = txtAlamat.getText().trim();
            String jenisKelamin = rbPria.isSelected() ? "Pria" : "Wanita";
            String noTelp = txtNoTelp.getText().trim();

            // Generate OTP
            String otp = util.OtpGenerator.generateOTP();

            // Tampilkan OTP (dalam aplikasi nyata, ini akan dikirim ke email)

            // User user = new User();
            // user.setUsername(username);
            // user.setName(nama);
            // user.setEmail(email);
            // user.setPassword(password);
            // user.setNoTelp(noTelp);
            // user.setAlamat(alamat);
            // user.setJenisKelamin(jenisKelamin);

            // if (user.save()) {

            JOptionPane.showMessageDialog(this,
                    "Kode OTP telah dikirim ke email: " + email + "\n" +
                            "Kode OTP: " + otp + "\n\n" +
                            "(Dalam aplikasi nyata, kode ini akan dikirim via email)",
                    "Verifikasi Email",
                    JOptionPane.INFORMATION_MESSAGE);

            // Tampilkan dialog verifikasi OTP
            OtpDialog otpDialog = new OtpDialog(this, otp);
            otpDialog.setVisible(true);

            // Proses setelah verifikasi OTP
            if (otpDialog.isVerified()) {
                User userOtp = new User();
                userOtp.setUsername(username);
                userOtp.setPassword(password);
                userOtp.setName(nama);
                userOtp.setEmail(email);
                userOtp.setAlamat(alamat);
                userOtp.setNoTelp(noTelp);
                userOtp.setJenisKelamin(jenisKelamin);

                if (userOtp.save()) {
                    JOptionPane.showMessageDialog(this,
                            "Registrasi berhasil!\nSilakan login dengan akun Anda.",
                            "Sukses",
                            JOptionPane.INFORMATION_MESSAGE);
                    this.dispose();
                    new Login().setVisible(true);
                } else {
                    showError("Gagal melakukan registrasi! Username atau email mungkin sudah terdaftar.");
                }
            } else {
                showError("Verifikasi email dibatalkan atau gagal. Silakan coba lagi.");
            }
            // }
        } catch (Exception e) {
            e.printStackTrace();
            showError("Terjadi kesalahan: " + e.getMessage());
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(this,
                message,
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    private void clearForm() {
        txtUsername.setText("");
        txtPassword.setText("");
        txtConfirmPassword.setText("");
        txtNama.setText("");
        txtEmail.setText("");
        txtAlamat.setText("");
        txtNoTelp.setText("");
        ButtonGroup bg = new ButtonGroup();
        bg.add(rbPria);
        bg.add(rbWanita);
        bg.clearSelection();
    }

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException
                | UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            new Registrasi().setVisible(true);
        });
    }
}
