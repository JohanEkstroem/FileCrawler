import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileCrawler {
  public static void main(String[] args) {
    String startingPoint = "C:\\testmapp\\";
    System.out.print("Welcome to FileCrawler\nEnter search word:");
    Scanner scanner = new Scanner(System.in);
    String searchWord = scanner.next();
    scan(startingPoint, searchWord);
    scanner.close();
  }

  private static void scan(String path, String searchWord) {
    File dir = new File(path);
    File files[] = dir.listFiles();
    for (File file : files) {
      String data = file.getAbsolutePath();
      boolean canRead = file.canRead();
       if (!canRead) {
        System.err.println("Cannot read file: " + " " + data);         
        }
        if (file.isFile()) {
          searchFile(data, searchWord);
      }
      if (file.isDirectory()) {
        scan(file.getAbsolutePath(), searchWord);
      } 
    }
  }

  private static void searchFile(String data, String searchWord) {
    File file = new File(data);
    
    try (Scanner scanner = new Scanner(file)) {
      while (scanner.hasNext()) {
        String word = scanner.next();
       // System.out.println(word);
        if (word.equals(searchWord)) {
                    System.out.println("Found word in here: " + data);
        }
      } 
    } catch (FileNotFoundException e) {
      System.out.println("File not found");
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
  }

}