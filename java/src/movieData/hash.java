package movieData;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

class ListNode {
    String key; // Movie name
    String info; // Movie info
    ListNode next;

    public ListNode(String key, String info) {
        this.key = key;
        this.info = info;
        this.next = null;
    }
}

class HashTable {
    ListNode[] table; // Hash-based array

    public HashTable(int size) {
        table = new ListNode[size];
    }

    private int hashFunction(String key) {
        // Hash movie name 'Key' to its ccorresponding index
        switch (key) {
            case "THE HOLDOVERS": return 0;
            case "AMERICAN FICTION": return 1;
            case "ANATOMY OF A FALL": return 2;
            case "MAESTRO": return 3;
            case "PAST LIVES": return 4;
            case "KILLERS OF THE FLOWER MOON": return 5;
            case "THE ZONE OF INTEREST": return 6;
            case "BARBIE": return 7;
            case "OPPENHEIMER": return 8;
            case "POOR THINGS": return 9;
            default: return -1;
        }
    }

    public void insert(String key, Movie movie) {
        //insert movie to the end of the linked list for key
        int index = hashFunction(key);
        if (index == -1) {
            System.out.println("Invalid movie name: " + key);
            return;
        }
    
        ListNode newNode = new ListNode(movie.name, movie.info);
    
        if (table[index] == null) {
            table[index] = newNode;
        } else {
            ListNode current = table[index];
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }
    

    public void printHashTable() {
        for (int i = 0; i < table.length; i++) {
            System.out.print("Index " + i);
            ListNode current = table[i];
            if (current == null) {
                System.out.println(" -> null");
            } else {
                while (current != null) {
                    System.out.print(" -> " + current.key);
                    current = current.next;
                }
                System.out.println(" -> null");
            }
        }
    }
}

public class hash {

    public static int countOccurrences(String string1, String string2) {
        //return the number of times string2 occurs in string1
        int count = 0;
        int index = 0;
        
        while ((index = string1.indexOf(string2, index)) != -1) {
            count++; 
            index += string2.length(); 
        }
        
        return count;
    }

    public static void insertMovie(HashTable hashTable, List<Movie> movies) {
        //fill the hash table with the given list of movie objects <movies>
        for (Movie headerMovie: movies) {
            for (Movie currentMovie: movies) {
                //add currentMovie to the linked list for Headermovie for each time Headermovie occurs in currentMovie
                int count = 0;
                count = countOccurrences(currentMovie.name.toLowerCase(), headerMovie.name.toLowerCase()); //add # of occurrences in currentMovie name
                count += countOccurrences(currentMovie.info.toLowerCase(), headerMovie.name.toLowerCase()); //add # of occurrences in currentMovie info
                for (int i = 0; i < count; i++) {
                    hashTable.insert(headerMovie.name, currentMovie);
                }
            }
        }
    }
    public static void main(String[] args) {

        HashTable hashTable = new HashTable(10); // Create a hash table with 10 elements
        
        List<Movie> movies = split.split1("data-file1.txt");

        insertMovie(hashTable, movies);

        hashTable.printHashTable();
    }
}
