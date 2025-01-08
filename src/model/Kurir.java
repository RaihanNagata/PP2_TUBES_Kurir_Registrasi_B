/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Haida
 */
public class Kurir {
    private String id;
    private String nama;
    private String email;
    private String noTelp;
    private String alamat;
    private String tglRegis;
    
    // Fungsi Konstruktor
    public Kurir() {
        
    }
    
    // Setter & Getter
    public void setId(String id) {
        this.id = id;
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setNoTelp(String noTelp) {
        this.noTelp = noTelp;
    }
    
    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }
    
    public void setTglRegis(String tglRegis) {
        this.tglRegis = tglRegis;
    }
    
    public String getId() {
        return this.id;
    }
    
    public String getNama() {
        return this.nama;
    }
    
    public String getEmail() {
        return this.email;
    }
    
    public String getNoTelp() {
        return this.noTelp;
    }
    
    public String getAlamat() {
        return this.alamat;
    }
    
    public String getTglRegis() {
        return this.tglRegis;
    }
}
