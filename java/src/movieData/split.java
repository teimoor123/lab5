package movieData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Movie {
    String name;
    String info;

    public Movie(String name, String info) {
        this.name = name;
        this.info = info;
    }
}

public class split {
    public static void main(String[] args) {  
        //split1("data-file1.txt");
        //split2("data-file2.txt");
        //split3("data-file3.txt");
        //split4("data-file4.txt");
    }

    public static List<Movie> split1(String filePath) {
        List<String> lines = readFile(filePath);
        
        List<Movie> movies = new ArrayList<>();
        String movieName = "";
        String movieInfo = "";

        for (String line : lines) {
            if (line.contains("*")) {
                if (movieName == "") {
                    movieName = line.replaceAll("\\*+$", "");
                }
                else {
                    movies.add(new Movie(movieName.trim(), movieInfo.toString().trim()));
                    movieName = line.replaceAll("\\*+$", "");
                    movieInfo = "";
                }
            }
            else if (line == "\n"){
            }
            else {
                if (!movieName.isEmpty()) {
                    movieInfo += line; 
                }
            }
        }
        movies.add(new Movie(movieName.trim(), movieInfo.toString().trim()));
        return movies;
    }

    public static List<Movie> split2(String filePath) {
        List<String> lines = readFile(filePath);
        
        List<Movie> movies = new ArrayList<>();
        String movieName = "";
        String movieInfo = "";

        String yearString = "\\(\\d{4}\\)";
        Pattern year = Pattern.compile(yearString);

        for (String line : lines) {
            Matcher matcher = year.matcher(line);
            if (matcher.find()) {
                if (movieName == "") {
                    movieName = line.replaceAll("\\s*\\(\\d{4}\\)$", "");
                }
                else {
                    movies.add(new Movie(movieName.trim(), movieInfo.toString().trim()));
                    movieName = line.replaceAll("\\s*\\(\\d{4}\\)$", "");
                    movieInfo = "";
                }
            }
            else if (line == "\n"){
            }
            else {
                if (!movieName.isEmpty()) {
                    if (line.contains("Directed By:")) {
                        movieInfo += line; 
                    }
                    else {
                        movieInfo += line;
                        movieInfo += ", ";
                    }
                }
            }
        }
        movies.add(new Movie(movieName.trim(), movieInfo.toString().trim()));
        return movies;
    }

    public static List<Movie> split3(String filePath) {
        List<String> lines = readFile(filePath);
        
        List<Movie> movies = new ArrayList<>();
        String movieName = "";
        String movieInfo = "";

        for (String line : lines) {
            if (line.startsWith("Film: ")) {
                if (movieName == "") {
                    movieName = line.replaceAll("Film: ", "");
                }
                else {
                    movies.add(new Movie(movieName.trim(), movieInfo.toString().trim()));
                    movieName = line.replaceAll("Film: ", "");
                    movieInfo = "";
                }
            }
            else if (line == "\n"){
            }
            else if (line.startsWith("Nominations:") || line.startsWith("Sypnosis:") || line.startsWith("Based on") || line.startsWith("Partially") || line.startsWith("Where to watch:")) {
                if (!movieName.isEmpty()) {
                    movieInfo += line; 
                    movieInfo += " ";
                }
            }
        }
        movies.add(new Movie(movieName.trim(), movieInfo.toString().trim()));
        return movies;
    }

    public static List<Movie> split4(String filePath) {
        List<String> lines = readFile(filePath);
        
        List<Movie> movies = new ArrayList<>();
        String movieName = "";
        String movieInfo = "";

        for (String line : lines) {
            if (!line.isEmpty() && Character.isDigit(line.charAt(0))) {
                if (movieName == "") {
                    movieName = line.replaceFirst("^\\d+\\.\\s*", "");
                }
                else {
                    movies.add(new Movie(movieName.trim(), movieInfo.toString().trim()));
                    movieName = line.replaceAll("\\*+$", "");
                    movieInfo = "";
                }
            }
            else if (line == "\n"){
            }
            else {
                if (!movieName.isEmpty()) {
                    movieInfo += line;
                    movieInfo += " "; 
                }
            }
        }
        movies.add(new Movie(movieName.trim(), movieInfo.toString().trim()));
        return movies;
    }

    public static List<String> readFile(String filePath) {
        List<String> lines = new ArrayList<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(filePath));
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return lines;
    }
}
