package calculator;

import static camp.nextstep.edu.missionutils.Console.readLine;

public class Application {
    public static void main(String[] args) {
        System.out.println("덧셈할 문자열을 입력해 주세요.");
        String input = readLine();
        int result = add(normalizeInput(input));
        System.out.println("결과 : " + result);
    }
    private static int[] normalizeInput(String input) {
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

    private static int add(int[] input) {
        int sum = 0;
        for (int i = 0; i < input.length; i++) {
            sum += input[i];
        }
        return sum;
    }
}
