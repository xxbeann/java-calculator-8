package calculator;

import java.util.regex.Pattern;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class Application {
    public static void main(String[] args) {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = readLine();
        int result = add(normalizeInput(input));
        System.out.println("결과 : " + result);
    }

    private static int[] normalizeInput(String input) {
        if(input.startsWith("//")) {
            String customSeparator = getCustomSeparator(input);
            String tokens = input.split("\\\\n")[1];
            return normalizeCustomInput(tokens,customSeparator);
        }
        String[] tokens = input.split("[,:]");
        int[] normalized = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].isEmpty()) {
                tokens[i] = "0";
            }
            normalized[i] = Integer.parseInt(tokens[i]);
        }
        return normalized;
    }

    private static int[] normalizeCustomInput(String input, String customSeparator) {
        String[] tokens = input.split(Pattern.quote(customSeparator));
        int[] normalized = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            if (tokens[i].isEmpty()) {
                tokens[i] = "0";
            }
            normalized[i] = Integer.parseInt(tokens[i]);
        }
        return normalized;
    }


    private static String getCustomSeparator(String input) {
        String customSeparator = "";
        if(input.startsWith("//")) {
            customSeparator = input.substring(2).split("\\\\n")[0];
        }
        return customSeparator;
    }

    private static int add(int[] input) {
        int sum = 0;
        for (int i = 0; i < input.length; i++) {
            sum += input[i];
        }
        return sum;
    }
}
