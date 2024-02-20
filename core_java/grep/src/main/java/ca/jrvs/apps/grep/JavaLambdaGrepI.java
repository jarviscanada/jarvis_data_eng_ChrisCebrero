package ca.jrvs.apps.grep;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JavaLambdaGrepI extends JavaGrepI {
  final Logger logger = LoggerFactory.getLogger(JavaGrep.class);

  private String regex;
  private String rootPath;
  private String outFile;

  public static void main(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException("weird");
    }
    JavaLambdaGrepI javaLambdaGrepImp = new JavaLambdaGrepI();
    javaLambdaGrepImp.setRegex(args[0]);
    javaLambdaGrepImp.setRootPath(args[1]);
    javaLambdaGrepImp.setOutFile(args[2]);

    try {
      javaLambdaGrepImp.process();
    } catch (Exception ex) {
      javaLambdaGrepImp.logger.error("Error: Unable to process", ex);
    }
  }

  public List<File> listFiles(String rootDir) {
    File rootDirPath = new File(rootDir);
    return listFiles(rootDirPath);
  }
  public static List<File> listFiles(File rootDir) {
    List<File> fileList = new ArrayList<>();
    if (rootDir.isDirectory()) {
      Arrays.stream(rootDir.listFiles())
          .forEach(file -> {
            if (file.isFile()) {
              fileList.add(file);
            } else if (file.isDirectory()) {
              fileList.addAll(listFiles(file));
            }
          });
    }
    return fileList;
  }
  @Override
  public List<String> readLines(File inputFile) {
    List<String> lineList = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
      reader.lines().forEach(lineList::add);
    } catch (IOException ex) {
      logger.error("Error: Unable to process", ex);
    }
    return lineList;
  }

  @Override
  public void writeToFile(List<String> lines) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(getOutFile()))) {
      lines.stream().forEach(line -> {
        try {
          writer.write(line);
          writer.newLine();
        } catch (IOException e) {
          e.printStackTrace();
        }
      });
    }
  }
}
