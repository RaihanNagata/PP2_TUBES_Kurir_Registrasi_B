package view.dashboard;

import model.User;
import dao.UserDao;
import java.awt.*;
import view.auth.Login;
import view.auth.ChangePassword; // Impor halaman ubah password
import java.awt.event.*;
import java.io.File;
import java.util.List;
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
    private String pathKk, pathKtp;

    public FrameAccount(FramePrimary parent) {
        super.panelContent = new JPanel();
        panelContent.setLayout(new BorderLayout());

        this.parent = parent;
        this.userDao = new UserDao();
        this.data = userDao.find(super.user.getId());

        JLabel lJudul = new JLabel("Account", JLabel.CENTER);
        JTable table = new JTable();
        tableModel = new UserTableModel(data);
        JScrollPane scrollableTable = new JScrollPane(table);
        table.setModel(tableModel);
        JPanel panelBtn = new JPanel();
        panelBtn.setLayout(new FlowLayout());
        JButton btnDelAcc = new JButton("Hapus Akun");
        JButton btnEditAcc = new JButton("Edit Profil");
        JButton btnAddPhoto = new JButton("Lihat KTP & KK");
        JButton btnChangePassword = new JButton("Ubah Password"); // Tambahkan tombol ubah password
        JButton btnLogout = new JButton("Logout");

        panelContent.add(lJudul, BorderLayout.NORTH);
        panelContent.add(scrollableTable, BorderLayout.CENTER);
        panelContent.add(panelBtn, BorderLayout.SOUTH);
        
        panelBtn.add(btnDelAcc);
        panelBtn.add(btnEditAcc);
        panelBtn.add(btnAddPhoto);
        panelBtn.add(btnChangePassword); // Tambahkan tombol ubah password
        panelBtn.add(btnLogout);

        btnDelAcc.addActionListener(_ -> delAcc());
        btnEditAcc.addActionListener(_ -> editAcc());
        btnAddPhoto.addActionListener(_ -> addPhoto());
        btnChangePassword.addActionListener(_ -> handleChangePassword()); // Tambahkan listener untuk tombol ubah password
        btnLogout.addActionListener(_ -> logout());
    }

    private void delAcc() {
        userDao.delete(super.user);

        JOptionPane.showMessageDialog(null,
            "Akun milik " + user.getName() + " berhasil di hapus",
            "Delete Berhasil",
            JOptionPane.INFORMATION_MESSAGE);

        parent.dispose();
        new Login().setVisible(true);
    }
    
    public void editAcc() {
        GridBagConstraints gbc = new GridBagConstraints();
        
        JFrame frameProfil = new JFrame("Profil " + super.user.getName());
        frameProfil.setLayout(new GridBagLayout());
        frameProfil.setSize(400, 500);
        JTextField txtUsername = new JTextField(20);
        JTextField txtNama = new JTextField(20);
        JTextField txtEmail = new JTextField(20);
        JTextField txtNoTelp = new JTextField(20);
        JTextArea txtAlamat = new JTextArea(3, 20);
        JRadioButton rbPria = new JRadioButton("Pria");
        JRadioButton rbWanita = new JRadioButton("Wanita");
        JButton btnEdit = new JButton("Perbarui Profil");
        
        // Mengatur ukuran font yang konsisten
        Font labelFont = new Font("Arial", Font.PLAIN, 12);
        Font fieldFont = new Font("Arial", Font.PLAIN, 12);
        
        // Username
        JLabel lblUsername = new JLabel("Username:");
        lblUsername.setFont(labelFont);
        gbc.gridy = 1;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.WEST;
        frameProfil.add(lblUsername, gbc);

        txtUsername.setFont(fieldFont);
        txtUsername.setText(user.getUsername());
        gbc.gridx = 1;
        frameProfil.add(txtUsername, gbc);

        // Nama Lengkap
        JLabel lblNama = new JLabel("Nama Lengkap:");
        lblNama.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 4;
        frameProfil.add(lblNama, gbc);

        txtNama.setFont(fieldFont);
        txtNama.setText(user.getName());
        gbc.gridx = 1;
        frameProfil.add(txtNama, gbc);

        // Email
        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 5;
        frameProfil.add(lblEmail, gbc);

        txtEmail.setFont(fieldFont);
        txtEmail.setText(user.getEmail());
        gbc.gridx = 1;
        frameProfil.add(txtEmail, gbc);
        
        // No Telp
        JLabel lblNoTelp = new JLabel("No Telp:");
        lblNoTelp.setFont(labelFont);
        gbc.gridx = 0;
        gbc.gridy = 6;
        frameProfil.add(lblNoTelp, gbc);

        txtNoTelp.setFont(fieldFont);
        txtNoTelp.setText(user.getNoTelp());
        gbc.gridx = 1;
        frameProfil.add(txtNoTelp, gbc);
        
        // Gender
        JLabel lblGender = new JLabel("Jenis Kelamin:");
        lblGender.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridx = 0;
        gbc.gridy = 7;

        frameProfil.add(lblGender, gbc);

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
        
        if (user.getJenisKelamin().equalsIgnoreCase("pria")) {
            rbPria.setSelected(true);
        } else if (user.getJenisKelamin().equalsIgnoreCase("wanita")) {
            rbWanita.setSelected(true);
        } else {
            bgGender.clearSelection();
        }

        gbc.gridx = 1;
        frameProfil.add(genderPanel, gbc);
        
        // Alamat
        JLabel lblAlamat = new JLabel("Alamat:");
        lblAlamat.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridx = 0;
        gbc.gridy = 8;

        frameProfil.add(lblAlamat, gbc);

        txtAlamat.setFont(new Font("Arial", Font.PLAIN, 12));
        txtAlamat.setLineWrap(true);
        txtAlamat.setWrapStyleWord(true);
        txtAlamat.setText(user.getAlamat());
        
        JScrollPane scrollPane = new JScrollPane(txtAlamat);
        scrollPane.setPreferredSize(new Dimension(200, 60));
        
        gbc.gridx = 1;
        frameProfil.add(scrollPane, gbc);
        
        gbc.gridy = 9;
        frameProfil.add(btnEdit, gbc);
        
        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //User user = new User();
                user.setUsername(txtUsername.getText().trim());
                user.setName(txtNama.getText().trim());
                user.setEmail(txtEmail.getText().trim());
                user.setNoTelp(txtNoTelp.getText().trim());
                user.setAlamat(txtAlamat.getText().trim());
                user.setJenisKelamin(rbPria.isSelected() ? "Pria" : "Wanita");
                
                userDao.update(user);
            }
        });
        
        frameProfil.setVisible(true);
    }

    public void addPhoto() {
        User user = data.get(data.size() - 1);
        JFrame framePhoto = new JFrame("Foto KTP & KK");
        framePhoto.setLayout(new GridLayout(1, 2));
        framePhoto.setSize(600, 600);
        JPanel panelKk = new JPanel();
        panelKk.setLayout(new BorderLayout());
        JPanel panelKtp = new JPanel();
        panelKtp.setLayout(new BorderLayout());
        JLabel lKk = new JLabel();
        JLabel lKtp = new JLabel();
        JButton btnAmbilKk = new JButton("Tambahkan File KK");
        JButton btnAmbilKtp = new JButton("Tambahkan File Ktp");
        
        btnAmbilKk.addActionListener(_ -> getPathKk(framePhoto, lKk, btnAmbilKk, user));
        btnAmbilKtp.addActionListener(_ -> getPathKtp(framePhoto, lKtp, btnAmbilKtp, user));
        
        /*
        if (!user.getKk().isEmpty()) {
            ImageIcon imgKk = new ImageIcon(user.getKk());
            btnAmbilKk.setText("Ganti File KK");
        }
        */
        framePhoto.add(panelKk);
        framePhoto.add(panelKtp);
        panelKk.add(lKk, BorderLayout.CENTER);
        panelKk.add(btnAmbilKk, BorderLayout.SOUTH);
        panelKtp.add(lKtp, BorderLayout.CENTER);
        panelKtp.add(btnAmbilKtp, BorderLayout.SOUTH);
        
        framePhoto.setVisible(true);
    }
    
    private void getPathKk(JFrame framePhoto, JLabel imgPhoto, JButton btn, User user) {
        JFileChooser fcKk = new JFileChooser();
        int result = fcKk.showOpenDialog(framePhoto);

        // Check if the user selected a file
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fcKk.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();  // Get the absolute path of the selected file
            user.updateFoto(user.getId(), "kk", filePath);
            ImageIcon imgKk = new ImageIcon(filePath);
            Image image = imgKk.getImage(); // Get the image from ImageIcon
            Image scaledImage = image.getScaledInstance(400, 400, Image.SCALE_SMOOTH); // Scale the image to fit the JFrame
            imgKk = new ImageIcon(scaledImage); // Update ImageIcon with the scaled image
            imgPhoto.setIcon(imgKk);
            btn.setText("Ganti File KK");
        }
    }
    
    private void getPathKtp(JFrame framePhoto, JLabel imgPhoto, JButton btn, User user) {
        JFileChooser fcKtp = new JFileChooser();
        int result = fcKtp.showOpenDialog(framePhoto);

        // Check if the user selected a file
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fcKtp.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();  // Get the absolute path of the selected file
            user.updateFoto(user.getId(), "ktp", filePath);
            ImageIcon imgKtp = new ImageIcon(filePath);
            Image image = imgKtp.getImage(); // Get the image from ImageIcon
            Image scaledImage = image.getScaledInstance(400, 400, Image.SCALE_SMOOTH); // Scale the image to fit the JFrame
            imgKtp = new ImageIcon(scaledImage); // Update ImageIcon with the scaled image
            imgPhoto.setIcon(imgKtp);
            btn.setText("Ganti File Ktp");
        }
    }

    private void handleChangePassword() {
        // Alihkan ke halaman atau jendela ubah password
        new ChangePassword(super.user).setVisible(true);
    }

    public void logout() {
        parent.dispose(); // Dispose the frame
        new Login().setVisible(true); // Launch Login frame
    }
}
