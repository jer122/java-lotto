package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LottosTest {

    @Test
    @DisplayName("Set 생성자 테스트")
    public void LottoSetParameterConstructorTest() {
        Set<Lotto> expect = generateIssuedLotto();
        Lottos lottos = new Lottos(expect);

        assertThat(lottos.size()).isEqualTo(expect.size());
    }

    @ParameterizedTest
    @CsvSource(value = {"0:0", "14000:14", "5300:5"}, delimiter = ':')
    @DisplayName("주어진 갯수만큼 로또 생성 테스트")
    public void lottoGenerateTest(int input, int expected) {
        Lottos lottos = new Lottos(input);

        assertThat(lottos.size()).isEqualTo(expected);
    }

    @Test
    @DisplayName("발행된 로또 값을 정상적으로 string 변환하는지 테스트")
    public void LottoToStringTest() {
        List<String> toStringExpect = Arrays.asList("[1, 2, 3, 4, 5, 6]","[2, 3, 4, 5, 6, 7]","[3, 4, 5, 6, 7, 8]");

        Lottos lottos = new Lottos(generateIssuedLotto());

        for (String expect : toStringExpect) {
            assertTrue(lottos.toString("[%s]\n", ", ").contains(expect));
        }
    }

    @Test
    @DisplayName("당첨 결과 생성 테스트")
    public void winningResultTset(){
        Set<Lotto> expect = generateLottoValueWithNotMatching();
        Lottos lottos = new Lottos(expect);

        List<Integer> winningNumber = Arrays.asList(1, 2, 3, 4, 5, 6);

        Map<Winning, Integer> winning = lottos.getResult(winningNumber);

        assertThat(winning.get(Winning.FIRST_PLACE)).isEqualTo(1);
        assertThat(winning.get(Winning.SECOND_PLACE)).isEqualTo(1);
        assertThat(winning.get(Winning.THIRD_PLACE)).isEqualTo(1);
        assertNull(winning.get(Winning.FOURTH_PLACE));
        assertThat(winning.get(Winning.LOSE)).isEqualTo(1);
    }

    @Test
    @DisplayName("수익률 계산 테스트")
    public void calculateYieldTest(){
        String result = Lotto.calculateYield(14000, 5000);

        assertThat(result).isEqualTo("0.36");
    }

    private Set<Lotto> generateIssuedLotto() {
        Set<Lotto> lottoNumbers = new HashSet<>();

        lottoNumbers.add(new Lotto(Arrays.asList(1, 2, 3, 4, 5, 6)));
        lottoNumbers.add(new Lotto(Arrays.asList(2, 3, 4, 5, 6, 7)));
        lottoNumbers.add(new Lotto(Arrays.asList(3, 4, 5, 6, 7, 8)));

        return lottoNumbers;
    }

    private Set<Lotto> generateLottoValueWithNotMatching() {
        Set<Lotto> lottoNumbers = generateIssuedLotto();

        lottoNumbers.add(new Lotto(Arrays.asList(9, 10, 11, 12, 13, 14)));

        return lottoNumbers;
    }
}