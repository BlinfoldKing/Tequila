package Tequila.entity;

public class Penulis {
    public String kode_penulis;
    public String nama;
    public String alamat;
    public String no_hp;
    public String email;
    public String tahun_lahir;

    Penulis(
        String kode_penulis,
        String nama,
        String alamat,
        String no_hp,
        String email,
        String tahun_lahir
    ) {
        this.kode_penulis = kode_penulis;
        this.nama = nama;
        this.alamat = alamat;
        this.no_hp = no_hp;
        this.email = email;
        this.tahun_lahir = tahun_lahir;
    }
}