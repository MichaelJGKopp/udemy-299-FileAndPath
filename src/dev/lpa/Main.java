package dev.lpa;

import java.io.File;
import java.io.IOException;

public class Main {

  public static void main(String[] args) {

  userFile("testFile.txt");

  }

  private static void userFile(String fileName) {

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
      }
    }

  }
}
