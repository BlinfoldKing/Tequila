package Tequila.entity;

public class Penulis {
    public String kode_penulis;
    public String nama;
    public String tahun_lahir;
    public String alamat;
    public String no_hp;
    public String email;
    
    public Boolean kode_penulis_mask = true;
    public Boolean nama_mask = true;
    public Boolean tahun_lahir_mask = true;
    public Boolean alamat_mask = true;
    public Boolean no_hp_mask = true;
    public Boolean email_mask = true;
    
    public Penulis(
        String kode_penulis,
        String nama,
        String tahun_lahir,
        String alamat,
        String no_hp,
        String email
    ) {
        this.kode_penulis = kode_penulis;
        this.nama = nama;
        this.tahun_lahir = tahun_lahir;
        this.alamat = alamat;
        this.no_hp = no_hp;
        this.email = email;
    }
}