package com.ttsoftware.utilities.filehandler;


public class CommandUsage {

  public static void usage() {
    System.out
        .println("Usage: java Corrupt Data File [options]");

    System.out.println("Options: ");
    System.out.println("  -c              = Corrupt the destination file using the source file");
    System.out.println("  -r              = Delete the source file");
    System.out.println("Corrupt File Sample: ");
    System.out.println("  -c sourceFilePath destinationFilePath ");
    System.out.println("Delete File Sample: ");
    System.out.println("  -r sourceFilePath ");
  }
}
