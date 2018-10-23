package com.tj.app;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Test;

import com.tj.suit.GermanyLotto;

public class GermanyLottoApp {
	
	/*
	독일에는 "LOTTO 6 aus 49" 복권이 있다. 49개의 번호 중 6개를 조합하여 당첨 여부를 가린다. 
	또한 "Superzahl"라는 보너스 번호가 있는데, 이를 이용해 당첨 확률을 높일 수 있다.

	이를 위해 GermanyLotto 클래스에 두 메서드를 작성해야 한다.
		public int[] numberGenerator()
		public int checkForWinningCategory(int[] checkCombination, int[] winningCombination)

	첫 번째 메서드는 복권 번호를 생성한다.
	The first method is for drawing the lottery numbers.
	배열에 총 7개의 숫자를 생성하고 6개의 숫자는 1 ~ 49의 숫자여야 한다. 당연히 6개의 숫자는 중복되지 않는다.
	7번째 숫자는 "Superzahl"라는 보너스 번호이고, 0 ~ 9 사이의 숫자여야 한다. 이 숫자는 앞 6개의 숫자와 개별적인 숫자이다.
	앞 6개의 숫자는 오름차순 정렬이어야 한다. 

	a result could be:
	4, 9, 17, 22, 25, 35, 0
	Or:
	4, 18, 22, 34, 41, 44, 4

	두번 째 메서드는 당첨 여부를 확인하는 메서드 이다. 당첨 조합은 아래와 같으며 메서드는 당첨된 경우 그 순위를 반환한다.
		1  : 6 numbers and Superzahl match
		2  : 6 numbers match
		3  : 5 numbers and Superzahl match
		4  : 5 numbers match
		5  : 4 numbers and Superzahl match
		6  : 4 numbers match
		7  : 3 numbers and Superzahl match
		8  : 3 numbers match
		9  : 2 numbers and Superzahl match
		-1 : 위 규칙에 벗어나는 경우
	 */
	@Test
	public void lottoTests() {
		int[] winningNumbers = GermanyLotto.numberGenerator();

		Assert.assertEquals("The method must return an array of 7 numbers.", 7, winningNumbers.length);
		Assert.assertTrue("The Superzahl must be between 0 and 9.", winningNumbers[6] >= 0 && winningNumbers[6] <= 9);

		Assert.assertNotEquals("The numbers have to be random!", Arrays.toString(winningNumbers), Arrays.toString(GermanyLotto.numberGenerator()));

		int[] winningNumbers6 = Arrays.copyOfRange(winningNumbers, 0 ,6);
		Assert.assertEquals("No doublettes allowed in the first 6 numbers.", 6, IntStream.of(winningNumbers6).distinct().toArray().length);

		int[] winningNumbers6ForSort = Arrays.copyOfRange(winningNumbers, 0 ,6);
		Arrays.sort(winningNumbers6ForSort);

		Assert.assertArrayEquals("The first 6 numbers have to be in ascending order.", winningNumbers6ForSort, winningNumbers6);
		for(int i=0;i<6;i++)
		{
			Assert.assertTrue("The numbers must be between 1 and 49.", winningNumbers[i] >= 1 && winningNumbers[i] <= 49);
		}

		Assert.assertEquals(1, GermanyLotto.checkForWinningCategory(new int[] { 1, 2, 3, 4, 5, 6, 7 }, new int[] { 1, 2, 3, 4, 5, 6, 7 }));
		Assert.assertEquals(2, GermanyLotto.checkForWinningCategory(new int[] { 1, 2, 3, 4, 5, 6, 0 }, new int[] { 1, 2, 3, 4, 5, 6, 7 }));
		Assert.assertEquals(8, GermanyLotto.checkForWinningCategory(new int[] { 1, 2, 3, 34, 35, 39, 1 }, new int[] { 1, 2, 3, 4, 5, 6, 7 }));
		Assert.assertEquals(9, GermanyLotto.checkForWinningCategory(new int[] { 1, 2, 25, 34, 35, 39, 1 }, new int[] { 1, 2, 3, 4, 5, 6, 1 }));
		Assert.assertEquals(-1, GermanyLotto.checkForWinningCategory(new int[] { 1, 2, 25, 34, 35, 39, 1 }, new int[] { 1, 2, 3, 4, 5, 6, 7 }));
	}
}
