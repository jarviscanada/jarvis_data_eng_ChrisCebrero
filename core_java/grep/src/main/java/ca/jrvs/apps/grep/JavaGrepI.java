package ca.jrvs.apps.grep;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JavaGrepI implements JavaGrep {

  final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

  private String regex;
  private String rootPath;
  private String outFile;

  public static void main(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException("weird");
    }

    JavaGrepI javaGrepImp = new JavaGrepI();
    javaGrepImp.setRegex(args[0]);
    javaGrepImp.setRootPath(args[1]);
    javaGrepImp.setOutFile(args[2]);

    try {
      javaGrepImp.process();
    } catch (Exception ex) {
      javaGrepImp.logger.error("Error: Unable to process", ex);
    }


  }

  @Override
  public void process() throws IOException {
    List<String> matchedLines = new ArrayList<>();
    List<File> fileList = listFiles(getRootPath());
    for (File file : fileList) {
      for (String line : readLines(file)) {
        if (containsPatterns(line)) {
          matchedLines.add(line);
        }
      }
    }
    writeToFile(matchedLines);
  }

  @Override
  public List<File> listFiles(String rootDir) {
    File rootDirPath = new File(rootDir);
    return listFiles(rootDirPath);
  }

  public static List<File> listFiles(File rootDir) {
    List<File> fileList = new ArrayList<>();
    if (rootDir.isDirectory()) {
      File[] files = rootDir.listFiles();
      if (files != null) {
        for (File file : files) {
          if (file.isFile()) {
            fileList.add(file);
          } else if (file.isDirectory()) {
            fileList.addAll(listFiles(file));
          }
        }
      }
    }
    return fileList;
  }

  @Override
  public List<String> readLines(File inputFile) throws FileNotFoundException {
    List<String> lineList = new ArrayList<>();
    try {
      FileReader input = new FileReader(inputFile);
      BufferedReader reader = new BufferedReader(input);
      String line;
      while ((line = reader.readLine()) != null) {
        lineList.add(line);
      }
      input.close();
    } catch (Exception ex) {
      logger.error("Error: Unable to process", ex);
    }
    return lineList;
  }

  @Override
  public boolean containsPatterns(String line) {
    return line.matches(getRegex());
  }

  @Override
  public void writeToFile(List<String> lines) throws IOException {
    try (BufferedWriter writer = new BufferedWriter((new FileWriter(getOutFile())))) {
      for (String line : lines) {
        writer.write(line);
        writer.newLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public String getRootPath() {
    return rootPath;
  }

  @Override
  public void setRootPath(String rootPath) {
    this.rootPath = rootPath;
  }

  @Override
  public String getRegex() {
    return regex;
  }

  @Override
  public void setRegex(String regex) {
    this.regex = regex;
  }

  @Override
  public String getOutFile() {
    return outFile;
  }

  @Override
  public void setOutFile(String outFile) {
    this.outFile = outFile;
  }
}
