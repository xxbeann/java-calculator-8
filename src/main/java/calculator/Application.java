package calculator;

import java.util.regex.Pattern;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class Application {
    private static final String DEFAULT_SEPARATOR = "[,:]";

    public static void main(String[] args) {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = readLine();
        validateAllowedCharacters(input);
        int result = sum(parseNumbers(input));
        System.out.println("결과 : " + result);
    }

    private static void validateAllowedCharacters(String input) {
        if(input.startsWith("//")){
            String customSeparator = extractCustomSeparator(input);
            String numberParts = input.split("\\\\n")[1];

            // 커스텀 구분자와 숫자만 허용
            String escapedSeparator = Pattern.quote(customSeparator);
            if(!numberParts.matches("[0-9" + escapedSeparator + "]*")) {
                throw new IllegalArgumentException("허용되지 않은 문자가 포함되어 있습니다.");
            }
        } else {
            // 기본 구분자만 사용
            if (!input.matches("[0-9,:]*")) {
                throw new IllegalArgumentException("허용되지 않은 문자가 포함되어 있습니다.");
            }
        }
    }

    private static int[] parseNumbers(String input) {
        if(input.startsWith("//")) {
            String customSeparator = extractCustomSeparator(input);
            String numberParts = input.split("\\\\n")[1];
            return extractNumbers(numberParts, Pattern.quote(customSeparator));
        }
        return extractNumbers(input, DEFAULT_SEPARATOR);
    }

    private static int[] extractNumbers(String input, String separator) {
        String[] afterSplit = input.split(separator);
        int[] numbers = new int[afterSplit.length];
        for (int i = 0; i < afterSplit.length; i++) {
            if (afterSplit[i].isEmpty()) {
                afterSplit[i] = "0";
            }
            numbers[i] = Integer.parseInt(afterSplit[i]);
        }
        return numbers;
    }

    private static String extractCustomSeparator(String input) {
        String customSeparator = "";
        if(input.startsWith("//")) {
            customSeparator = input.substring(2).split("\\\\n")[0];
        }
        return customSeparator;
    }

    private static int sum(int[] input) {
        int sum = 0;
        for (int i = 0; i < input.length; i++) {
            sum += input[i];
        }
        return sum;
    }
}
