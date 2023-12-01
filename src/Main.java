import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Main {
    private final static HashMap<String, String> WORDS_TO_NUMS = new HashMap<>() {{
        put("zero", "0");
        put("one", "1");
        put("two", "2");
        put("three", "3");
        put("four", "4");
        put("five", "5");
        put("six", "6");
        put("seven", "7");
        put("eight", "8");
        put("nine", "9");
    }};

    public static void main(String[] args) {
        test("kkdhmmvvmthreezxqzqmb4khprbldcr");

        String[] data = readFileToArray("D:\\projects\\advent-of-code\\src\\input.txt");
        System.out.print(Arrays.stream(data)
            .mapToInt((value) -> {
                String orig = value.toLowerCase(Locale.ROOT);
                value = test(value);


//                value = value.replaceAll("one", "1");
//                value = value.replaceAll("two", "2");
//                value = value.replaceAll("three", "3");
//                value = value.replaceAll("four", "4");
//                value = value.replaceAll("five", "5");
//                value = value.replaceAll("six", "6");
//                value = value.replaceAll("seven", "7");
//                value = value.replaceAll("eight", "8");
//                value = value.replaceAll("nine", "9");
                value = value.replaceAll("[^0-9]", "");

                final String firstNum = Character.toString(value.chars()
                        .findFirst()
                        .getAsInt());
                final String lastNum = value.chars()
                        .skip(value.chars().count() - 1)
                        .mapToObj(Character::toString)
                        .collect(Collectors.joining());
                String concat = firstNum.concat(lastNum); //4639

                System.out.printf("First: %s --- Last: %s --- Of: %s --- From: %s --- Equals: %d\n",
                        firstNum,
                        lastNum,
                        value,
                        orig,
                        Integer.parseInt(concat)
                );
                return Integer.parseInt(concat);
            })
            .sum()
        );
    }

    private static String test(String value) {
        final String regex = "(?=((one|two|three|four|five|six|seven|eight|nine|[0-9])))";

        final Pattern pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final Matcher matcher = pattern.matcher(value);
        final StringBuilder sb = new StringBuilder();
        String match;

        while (matcher.find()) {
//            System.out.println("Full match: " + matcher.group(0));

            for (int i = 1; i <= matcher.groupCount(); i++) {
//                System.out.println("Group " + i + ": " + matcher.group(i));
                match = matcher.group(i);
                switch (match) {
                    case "one":
                        matcher.appendReplacement(sb, "1");
                        break;

                    case "two":
                        matcher.appendReplacement(sb, "2");
                        break;

                    case "three":
                        matcher.appendReplacement(sb, "3");
                        break;

                    case "four":
                        matcher.appendReplacement(sb, "4");
                        break;

                    case "five":
                        matcher.appendReplacement(sb, "5");
                        break;

                    case "six":
                        matcher.appendReplacement(sb, "6");
                        break;

                    case "seven":
                        matcher.appendReplacement(sb, "7");
                        break;

                    case "eight":
                        matcher.appendReplacement(sb, "8");
                        break;

                    case "nine":
                        matcher.appendReplacement(sb, "9");
                        break;

                    default:
                        matcher.appendReplacement(sb, match);
                        break;
                }
//                matcher.appendTail(sb);
            }
        }

        return sb.toString().isEmpty() ? value : sb.toString();
    }

    private static String[] readFileToArray(String path) {
        ArrayList<String> file = new ArrayList<>();
        String currentLine;

        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            while ((currentLine = reader.readLine()) != null) {
                file.add(currentLine);
            }
        } catch (IOException ignored) {
        }

        return file.toArray(String[]::new);
    }
}
