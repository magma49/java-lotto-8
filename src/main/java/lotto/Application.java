package lotto;

import camp.nextstep.edu.missionutils.Console;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        int purchase = getValidPurchase();

        System.out.println(purchase + "개를 구매했습니다.");
        // 로또 출력

        Lotto win = getValidWin();

        int bonus = getValidBonus(win);

        // 당첨 통계

    }

    public static int getValidPurchase() {
        while (true) {
            try {
                System.out.println("구입금액을 입력해 주세요.");
                String input = Console.readLine();

                return validPurchase(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int validPurchase(String input) {
        int purchase;
        try {
            purchase = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 구입금액에 숫자만 입력해야 합니다.");
        }

        if (purchase == 0)
            throw new IllegalArgumentException("[ERROR] 로또를 사셔야 합니다.");
        if (purchase % 1000 != 0)
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 단위여야 합니다.");

        return purchase / 1000;
    }

    public static Lotto getValidWin() {
        while (true) {
            try {
                System.out.println("당첨 번호를 입력해 주세요.");
                String input = Console.readLine();
                List<Integer> listWin = validWin(input);

                return new Lotto(listWin);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static List<Integer> validWin(String input) {
        String[] winStrs = input.split(",");
        List<Integer> listWin = new ArrayList<>();
        for (String winStr : winStrs) {
            try {
                listWin.add(Integer.parseInt(winStr));
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("[ERROR] 당첨번호에 숫자를 입력해야 합니다.");
            }
        }
        return listWin;
    }

    public static int getValidBonus(Lotto win) {
        while (true) {
            try {
                System.out.println("보너스 번호를 입력해 주세요.");
                String input = Console.readLine();

                return validBonus(input, win);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static int validBonus(String input, Lotto win) {
        int bonus;
        try {
            bonus = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호에 숫자만 입력해야 합니다.");
        }
        win.checkNumber(bonus);
        win.search(bonus);

        return bonus;
    }

    public void checkBonus(int[] win, int bonus) {

    }
}
