package dev.lpa;

public class Main {

  public static void main(String[] args) {

    String helloWorld = "%s %s!".formatted("Hello", "World");
    String helloWorld2 = String.format("%s %s!", "Hello", "World");
    System.out.println("Using string's formatted method: " + helloWorld);
    System.out.println("Using String.format: " + helloWorld2);

    String helloWorld3 = Main.format("%s %s!", "Hello", "World");
    System.out.println("Using Main.format: " + helloWorld3);

  }

  private static String format(String regexp, String... args) {

//    for (var a : args) {
//      regexp = regexp.replaceFirst("%(.{1})", a);
//    }
    int index = 0;
//    while (regexp.contains("%s")) {
    while (regexp.matches(".*%s.*")) {  // . is any char except newline, * is 0 or more times
      regexp = regexp.replaceFirst("%s", args[index++]);
    }
    return regexp;
  }
}
