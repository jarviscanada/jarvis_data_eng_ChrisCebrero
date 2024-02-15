package ca.jrvs.apps.practice;

public class RegexExcImp implements RegexExc {
  public static void main(String[] args) {
    // Your Java program code goes here
    System.out.println("Hello, world!");
    String ip1 = "192.168.1.1";
    String ip2 = "256.0.0.0"; // Invalid IP address
    String ip3 = "2001:0db8:85a3:0000:0000:8a2e:0370:7334"; // IPv6, not matched by this regex

    System.out.println(ip1 + " is a valid IP address: " + matchIp(ip1));
    System.out.println(ip2 + " is a valid IP address: " + matchIp(ip2));
    System.out.println(ip3 + " is a valid IP address: " + matchIp(ip3));
  }

  public static boolean matchJpeg(String filename) {
    String regex = ".*\\.(jpeg|jpg)$";
    return filename.matches(regex);
  }

  public static boolean matchIp(String ip) {
    String regex = "^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[0-9]?[0-9][0-9]?)$";
    return ip.matches(regex);
  }

  public static boolean isEmptyLine(String line) {
    String regex = "^\\s*$";
    return line.matches(regex);
  }
}
