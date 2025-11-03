package lotto;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        int purchase = getValidPurchase();

        System.out.print("\n");
        System.out.println(purchase + "개를 구매했습니다.");

        Lotto[] lottos = makeLotto(purchase);
        System.out.print("\n");

        Lotto win = getValidWin();
        System.out.print("\n");

        int bonus = getValidBonus(win);
        System.out.print("\n");

        int[] winning = new int[5];
        for (int i = 0; i < 5; ++i) {
            winning[i] = 0;
        }

        System.out.println("당첨 통계");
        int match;
        for (Lotto lotto : lottos) {
            match = lotto.match(win, bonus);
            if (match > 2)
                ++winning[match - 3];
        }

        printWinning(winning, purchase);
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

    public static Lotto[] makeLotto(int purchase) {
        Lotto[] lottos = new Lotto[purchase];
        for (int i = 0; i < purchase; ++i) {
            lottos[i] = new Lotto(Randoms.pickUniqueNumbersInRange(1, 45, 6));
            lottos[i].print();
            System.out.print("\n");
        }
        return lottos;
    }

    public static void printWinning(int[] winning, int purchase) {
        int prize = 0;
        System.out.print("3개 일치 (5,000원) - " + winning[0] + "개\n");
        prize += winning[0] * Rank.THREE.prize();
        System.out.print("4개 일치 (50,000원) - " + winning[1] + "개\n");
        prize += winning[1] * Rank.FOUR.prize();
        System.out.print("5개 일치 (1,500,000원) - " + winning[2] + "개\n");
        prize += winning[2] * Rank.FIVE.prize();
        System.out.print("5개 일치, 보너스 볼 일치 (30,000,000원) - " + winning[3] + "개\n");
        prize += winning[3] * Rank.BONUS.prize();
        System.out.print("6개 일치 (2,000,000,000원) - " + winning[4] + "개\n");
        prize += winning[4] * Rank.SIX.prize();
        System.out.print("총 수익률은 " + String.format("%.1f", (double) prize * 100 / purchase) + "%입니다.\n");
    }

    public enum Rank {
        THREE(5),
        FOUR(50),
        FIVE(1500),
        BONUS(30000),
        SIX(2000000),
        ;

        private final int prize;

        Rank(int prize) {
            this.prize = prize;
        }

        public int prize() {
            return prize;
        }
    }
}
