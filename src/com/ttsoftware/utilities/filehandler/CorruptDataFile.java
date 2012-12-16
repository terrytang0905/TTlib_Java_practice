package com.ttsoftware.utilities.filehandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CorruptDataFile {

  public static char operationType;
  public static String sourceFileDir;
  public static String destinationFileDir;

  enum OperationType {
    CORRUPT_FILE,
    DELETE_FILE,
  }

  protected void corruptDataFileOrIndexFile(String source, String aim) throws IOException {
    FileInputStream in = new FileInputStream(source);
    FileOutputStream out = new FileOutputStream(aim);
    byte[] buffer = new byte[65536];
    int length;
    while ((length = in.read(buffer)) != -1) {
      out.write(buffer, 0, length);
    }
    out.flush();
    out.close();
    in.close();
  }

  protected boolean deleteDataFileOrIndexFile(String source) throws IOException {
    boolean deleteResult = false;
    File sourceFile = new File(source);
    sourceFile.delete();
    deleteResult = false;
    return deleteResult;
  }

  private static void parseCommand(String[] args) {
    int argsLength = args.length;
    if (argsLength < 1) {
      CommandUsage.usage();
      System.exit(1);
    } else {
      if (args[0].contains("-c")) {
        operationType = 'c';
        if (argsLength < 3) {
          CommandUsage.usage();
          System.exit(1);
        }
      } else if (args[0].contains("-r")) {
        operationType = 'r';
        if (argsLength < 2) {
          CommandUsage.usage();
          System.exit(1);
        }
      } else {
        CommandUsage.usage();
        System.exit(1);
      }
      for (int i = 1; i < argsLength; i++) {
        if (args[i] != null && i == 1) {
          sourceFileDir = args[1];
        }
        if (args[i] != null && i == 2) {
          destinationFileDir = args[2];
        }
      }
    }
  }

  public static void main(String[] args) {
    parseCommand(args);
    CorruptDataFile cd = new CorruptDataFile();
    try {
      switch (operationType) {
        case 'c':
          cd.corruptDataFileOrIndexFile(sourceFileDir, destinationFileDir);
          break;
        case 'r':
          cd.deleteDataFileOrIndexFile(sourceFileDir);
          break;
      }
      System.out.println("File Operation is successful!");
    } catch (IOException ioe) {
      ioe.printStackTrace();
      CommandUsage.usage();
    }

  }
}
