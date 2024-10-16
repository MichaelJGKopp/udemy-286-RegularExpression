package dev.lpa;

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
