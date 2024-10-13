package dev.lpa;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {

  public static void main(String[] args) {

    useFile("testFile.txt");  // File Class IO package

    usePath("pathFile.txt");

  }

  private static void usePath(String fileName) {

    Path path = Path.of(fileName);  // JDK 11
    boolean fileExists = Files.exists(path);

    System.out.printf("File '%s' %s%n", fileName, fileExists ? "exists." : "doesn't exist.");

    if (fileExists) {
      System.out.println("Deleting File: " + fileName);
      try {
        Files.delete(path);
        fileExists = false;
      } catch (IOException e) {
        e.printStackTrace();
      }
    }

    try {
      if (!fileExists) {
        Files.createFile(path);
        System.out.println("Created File: " + fileName);
        if (Files.isWritable(path)) {  // File method has no way to write by itself
//          System.out.println("Would write to file here.");
          Files.writeString(path, """
            Here is some data,
            For my file,
            just to prove,
            Using the Files class and path are better!
            """);
        } else {
          System.out.println("File is not writable.");
        }
        if (Files.isReadable(path)) {
          System.out.println("----------------------------------");
          System.out.println("Reading File: ");
          Files.readAllLines(path).forEach(System.out::println);
        } else {
          System.out.println("File is not readable.");
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }


  }

  private static void useFile(String fileName) {

    File file = new File(fileName);
    boolean fileExists = file.exists();

    System.out.printf("File '%s' %s%n", fileName, fileExists ? "exists." : "doesn't exist.");

    if (fileExists) {
      System.out.println("Deleting File: " + fileName);
      fileExists = !file.delete();
    }

    if (!fileExists) {
      try {
        file.createNewFile();
      } catch (IOException e) {
        System.out.println("Something went wrong.");
      }
      System.out.println("Created File: " + fileName);
      if (file.canWrite()) {  // File method has no way to write by itself
        System.out.println("Would write to file here.");
      } else {
        System.out.println("File is not writable.");
      }
    }

  }
}
