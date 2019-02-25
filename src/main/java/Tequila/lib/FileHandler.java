package Tequila.lib;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;

import Tequila.entity.Buku;
import Tequila.entity.Menulis;
import Tequila.entity.Penulis;

public class FileHandler{
    public List<Buku> readBuku(String file) {
        List<Buku> bList = new ArrayList<Buku>();
        
        try (
            Reader reader = Files.newBufferedReader(Paths.get(file));
            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
        ) {
            List<String[]> allData = csvReader.readAll();

            for (String[] row : allData) {
                for (String cell : row) {
                    String[] val = cell.split(",");
                    
                    Buku buku = new Buku(
                        val[0], //no_buku
                        val[1], //penerbit
                        val[2], //judul
                        val[3],  //tahun_terbit
                        val[4]
                    );

                    bList.add(buku);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bList;
    }

    public List<Penulis> readPenulis(String file) {
        List<Penulis> pList = new ArrayList<Penulis>();
        
        try (
            Reader reader = Files.newBufferedReader(Paths.get(file));
            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
        ) {
            List<String[]> allData = csvReader.readAll();

            for (String[] row : allData) {
                for (String cell : row) {
                    String[] val = cell.split(",");
                    
                    Penulis penulis = new Penulis(
                        val[0], //kode_penulis
                        val[1], //nama
                        val[2], //tahun_lahir
                        val[3], //alamat
                        val[4], //no_hp
                        val[5],  //email
                        val[6]
                    );

                    pList.add(penulis);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return pList;
    }

    public List<Menulis> readMenulis(String file) {
        List<Menulis> mList = new ArrayList<Menulis>();
        
        try (
            Reader reader = Files.newBufferedReader(Paths.get(file));
            CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build();
        ) {
            List<String[]> allData = csvReader.readAll();

            for (String[] row : allData) {
                for (String cell : row) {
                    String[] val = cell.split(",");
                    
                    Menulis menulis = new Menulis(
                        val[0], //kode_penulis
                        val[1] //no_buku
                    );

                    mList.add(menulis);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return mList;
    }
}