package proyecto_hash_table;

import txt.TxtReader;
import classes.HashTable;
import interfaces.Menu;
import textList.TextList;

public class proyecto_hash_table {

    public static void main(String[] args) {
        HashTable hs = new HashTable(10007);
        TextList tl = new TextList();
//        hs.hashFunction("Hola");
//        hs.hashFunction("Hola");
//        hs.hashFunction("Como");
//        hs.hashFunction("Estas");
//        hs.hashFunction("Hello");
//        hs.hashFunction("Jose");
//        hs.hashFunction("Maria");
//        hs.hashFunction("Test");
//        hs.hashFunction("Hola");
//        hs.hashFunction("Como");
//        hs.printTable();
        new Menu(hs, tl).setVisible(true);
        
    }
    
}
