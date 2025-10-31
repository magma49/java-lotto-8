package lotto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }
        checkDuplicateNumbers(numbers);
    }

    // TODO: 추가 기능 구현
    private void checkDuplicateNumbers(List<Integer> numbers) {
        Set<Integer> setNumbers = new HashSet<>();
        for (int num : numbers) {
            if (!setNumbers.add(num))
                throw new IllegalArgumentException("[ERROR] 당첨번호들은 모두 달라야 합니다.");
            checkNumber(num);
        }
    }

    public void checkNumber(int num) {
        if (num < 1 || num > 45)
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
    }

    public void search(int num) {
        for (int number : numbers) {
            if (num == number)
                throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨번호와 달라야 합니다.");

        }
    }

    public void print() {
        System.out.print("[");
        int i = 0;
        for (int num : numbers) {
            System.out.print(num);
            if (++i != 6)
                System.out.print(", ");
        }
        System.out.print("]");
    }

}
