package dev.lpa;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

  public static void main(String[] args) {

    String helloWorld = "%s %s!".formatted("Hello", "World");
    String helloWorld2 = String.format("%s %s!", "Hello", "World");
    System.out.println("Using string's formatted method: " + helloWorld);
    System.out.println("Using String.format: " + helloWorld2);

    String helloWorld3 = Main.format("%s %s!", "Hello", "World");
    System.out.println("Using Main.format: " + helloWorld3);

    String testString = "Anyone can Learn abc's, 123's, and any regular expression";
    String replacement = "(-)";

    String[] patterns = {
      "abc",  // character literals, matches exactly what is in ""
      "123",
      "A",
      "[abc]", // character class, any letter in []
      "[123]",
      "[A]",
      "a|b|c",  // [.] . is a literal in [], using | Or, same as [abc]
      "ab|bc",  // ab or bc
      "[a-z]",   // any lower alphabet letter
      "[0-9]",   // any single digit number
      "[A-Z]",   // any upper alphabet letter
      "[a-zA-Z]",  // any alphabet char
      "[a-zA-Z]*",  // replaces "" + "Anyone" since empty string is included
      "[0-9]*", // only empty string at start
      "[A-Z]*", // "A" of Anyone, incl. empty string ""
      "[0-9]+",  // 123
      "[0-9]{2}",  // 12

      "[a-zA-Z]*$",  // boundary matcher, $ end of string
      "^[a-zA-Z]{3}", // ^ start of string
      "[aA]ny\\b", // \\b word boundary matcher, always escape with \ any \ like \b -> \\b
      "\\b[aA]ny"
    };

    for (String pattern : patterns) {
      String output = testString.replaceFirst(pattern, replacement);
      System.out.println("Pattern: " + pattern + " => " + output);
    }

    // Song of the Witches in MacBeth, a Play by Shakespeare
    String paragraph = """
                Double, double toil and trouble;
                Fire burn and caldron bubble.
                Fillet of a fenny snake,
                In the caldron boil and bake
                Eye of newt and toe of frog,
                Wool of bat and tongue of dog,
                Adder's fork and blind-worm's sting,
                Lizard's leg and howlet's wing,
                For a charm of powerful trouble,
                Like a hell-broth boil and bubble.
                """;

    String[] lines = paragraph.split("\\R");  // linebreak matcher, any unicode linebreak
    System.out.println("This paragraph has " + lines.length + " lines.");
    String[] words = paragraph.split("\\s");  // whitespace character, any space, tab, linebreak
    System.out.println("This paragraph has " + words.length + " words");
    System.out.println(paragraph.replaceAll("[a-zA-Z]+ble", "[GRUB]"));

    Scanner scanner = new Scanner(paragraph);
    System.out.println(scanner.delimiter());  // \p{javaWhitespace}+, returns instance of pattern class
    scanner.useDelimiter("\\R");

//    while (scanner.hasNext()) {
//      String element = scanner.next();
//      System.out.println(element);
//    }

    scanner.tokens()  // stream of tokens
      .flatMap(s -> Arrays.stream(s.split("\\s+")))
//      .filter(s -> s.matches("[a-zA-Z]+ble]"))
      .forEach(System.out::println);
    scanner.close();  // included in java doc samples, but not necessary



    /*
    // short excursion, don't close Scanner(System.in)
    Scanner scanner1 = new Scanner(System.in);
    scanner1.close(); // closes underlying global stream System.in !!!
    Scanner scanner2 = new Scanner(System.in);
    String test = scanner2.nextLine();  // throws NoSuchElementException
    */
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
