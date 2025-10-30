package lotto;

import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        String input;

        System.out.println("구입금액을 입력해 주세요.");
        input = Console.readLine();
        int purchase = Integer.parseInt(input) / 1000;

        System.out.println(purchase + "개를 구매했습니다.");

        System.out.println("당첨 번호를 입력해 주세요.");
        input = Console.readLine();
        int[] win = new int[6];
        int i = 0;
        String[] winStrs = input.split(",");
        for (String winStr : winStrs) {
            win[i++] = Integer.parseInt(winStr);
        }

        System.out.println("보너스 번호를 입력해 주세요.");
        input = Console.readLine();
        int bonus = Integer.parseInt(input);

    }
}
