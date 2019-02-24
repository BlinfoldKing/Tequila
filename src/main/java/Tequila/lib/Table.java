package Tequila.lib;

import java.util.List;
import java.util.ArrayList;
import Tequila.entity.Buku;
import Tequila.entity.Penulis;

public class Table {
    public List<Buku> bookRecords;
    public List<Penulis> penulisRecord;

    public List<String> bookDisplayedAttribute = 
        new ArrayList<String>();
    public List<String> penulisDisplayedAtrribute =
        new ArrayList<String>();
}