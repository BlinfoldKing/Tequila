package Tequila.entity;

public class Buku {
    public String no_buku;
    public String penerbit;
    public String judul;
    public String tahun_terbit;
    
    public Boolean no_buku_mask = true;
    public Boolean penerbit_mask = true;
    public Boolean judul_mask = true;
    public Boolean tahun_terbit_mask = true;

    public Buku(
        String no_buku,
        String penerbit,
        String judul,
        String tahun_terbit
    ) {
        this.no_buku = no_buku;
        this.penerbit = penerbit;
        this.judul = judul;
        this.tahun_terbit = tahun_terbit;
    }
}