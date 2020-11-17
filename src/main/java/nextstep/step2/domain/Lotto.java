package nextstep.step2.domain;

import java.util.Set;

public class Lotto {
	private static final String WRONG_LOTTO_NUMBERS = "로또 번호는 6개여야 합니다.";
	private static final String WRONG_LOTTO_RANGE = "로또는 1~45의 숫자여야 합니다.";
	private Set<Integer> numbers;

	public Lotto(Set<Integer> numbers) {
		this.numbers = numbers;
	}

	public Set<Integer> getNumbers() {
		return numbers;
	}

	public void validate() {
		if (numbers.size() != 6) {
			throw new IllegalArgumentException(WRONG_LOTTO_NUMBERS);
		}
		numbers.forEach(number -> {
			if (number <= 0 || number > 45) {
				throw new IllegalArgumentException(WRONG_LOTTO_RANGE);
			}
		});
	}
}