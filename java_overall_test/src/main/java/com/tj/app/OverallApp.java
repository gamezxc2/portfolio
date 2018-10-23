package com.tj.app;

import org.junit.Assert;
import org.junit.Test;

import com.tj.suit.Overall;

public class OverallApp {
	private Overall o = new Overall();
	/*
		예시된 문자열을 뒤집어 출력하라.
	 */
	@Test
	public void printInversion() {	
		Assert.assertEquals("fedcba", o.printInversion("abcdef"));
		Assert.assertEquals("!AVAJ si sihT", o.printInversion("This is JAVA!"));
	}
	
	/*
	 	예시된 문자열에서 알파벳, 숫자, 공백의 개수를 구하시오.
	 */
	@Test
	public void countSth() {
		Assert.assertArrayEquals(new int[] {10, 0, 2}, o.countSth("This is JAVA!"));
		Assert.assertArrayEquals(new int[] {12, 1, 3}, o.countSth("There are 6 dogs"));
		Assert.assertArrayEquals(new int[] {23, 6, 6}, o.countSth("Top 10 Antivirus For Window in 2018"));
	}
	
	
	/*
	 	예시된 문자열이 회문(거꾸로 읽어도 제대로 읽는 것과 같은 문장이나 낱말)인지 확인하시오.
	 	
	 */
	@Test
	public void checkPalindrome() {
		Assert.assertEquals(true, o.checkPalindrome("madam"));
		Assert.assertEquals(true, o.checkPalindrome("A nut for a jar of tuna"));
		Assert.assertEquals(true, o.checkPalindrome("Murder for a jar of red rum"));
	}

	/*
		0, 1, 3, 6, 10, 15, 21, 28, ...
		0(0), 1(0+1), 3(0+1+2), 6(0+1+2+3)...
		패턴 : n번째의 값은 [0부터 n의 위치]까지 더한 값입니다.
	 */
	@Test
	public void sumOfN() {
		Assert.assertArrayEquals(new int[] {0, 1, 3, 6, 10, 15}, o.sumOfN(5));
		Assert.assertArrayEquals(new int[] {0, -1, -3, -6, -10, -15}, o.sumOfN(-5));
		Assert.assertArrayEquals(new int[] {0, 1, 3, 6, 10, 15, 21, 28}, o.sumOfN(7));
	}
	
	/*
	 	예시된 숫자가 암스트롱 수 인지 확인하시오.
	 	(암스트롱 수 :  세 자리의 정수 중에서 각 자리의 수를 세 제곱한 수의 합과 자신이 같은 수)
	 	ex) 153 == (1 * 1 * 1) + (5 * 5 * 5) + (3 * 3 * 3)
	 */
	@Test
	public void isArmStrongNum() {
		Assert.assertEquals(true, o.isArmStrongNum(153));
		Assert.assertEquals(true, o.isArmStrongNum(370));
		Assert.assertEquals(true, o.isArmStrongNum(407));
		Assert.assertEquals(false, o.isArmStrongNum(508));
	}
	
	/*
		예시된 문자열의 첫 글자와 마지막 글자를 제거하시오. (입력은 항상 3자 이상)
	 */
	@Test
	public void removeChar() {
		Assert.assertEquals("loquen", o.removeChar("eloquent"));
		Assert.assertEquals("ountr", o.removeChar("country"));
		Assert.assertEquals("erso", o.removeChar("person"));
		Assert.assertEquals("lac", o.removeChar("place"));
	}
	
	/*
		암호가 포함 된 SQL 연결 URL을 받는 함수가 있다. 
		예시된 URL에서 암호를 마스킹(*) 처리 하라.표로 암호를 변경해야합니다.
	전제 조건 :
		- 비어 있지 않은 유효한 URL
		- 암호는 항상 password= 오른쪽에 나타납니다.
		- 단순화를 위해 비밀번호에는 & 기호가 포함되지 않는다고 가정합니다.
		- 보다 실제화하기 위해 비 ASCII 문자를 사용합니다.
		- "password="및 "user="는 한 번만 나타납니다.
		- 빈 암호는 검증되지 않지만 최상의 솔루션은 빈 암호를 고려합니다.
	 */
	@Test
	public void maskingPw() {
		Assert.assertEquals(
				"jdbc:mysql://sdasdasdasd:szdasdasd:dfsdfsdfsdf/sdfsdfsdf?user=root&password=*****", 
				o.maskingPw("jdbc:mysql://sdasdasdasd:szdasdasd:dfsdfsdfsdf/sdfsdfsdf?user=root&password=12345")
				);
		Assert.assertEquals(
				"jdbc:mysql://sdasdasdasd:szdasdasd:dfsdfsdfsdf/sdfsdfsdf?password=*****&user=root", 
				o.maskingPw("jdbc:mysql://sdasdasdasd:szdasdasd:dfsdfsdfsdf/sdfsdfsdf?password=12345&user=root")
				);
	}
	
	/*
	 	두 정수(integer) a와 b는 양수 또는 음수 일 수 있다. a와 b 사이에 존재하는 모든 숫자의 합을 구하라.
	 	만약, 두 숫자가 동일하다면 a 또는 b의 값이 반환된다.
	 	(주, a와 b는 정렬된 숫자가 아니다.) 
		
	 	getSum(1, 0) == 1   // 1 + 0 = 1
		getSum(1, 2) == 3   // 1 + 2 = 3
		getSum(0, 1) == 1   // 0 + 1 = 1
		getSum(1, 1) == 1   // 1 Since both are same
		getSum(-1, 0) == -1 // -1 + 0 = -1
		getSum(-1, 2) == 2  // -1 + 0 + 1 + 2 = 2
	 */
	@Test
	public void getSum() {
		Assert.assertEquals(-1, o.getSum(0, -1));
		Assert.assertEquals(1, o.getSum(0, 1));
	}
	
	/*
		첫 번째와 세 번째 값은 숫자이다.
		두번째 값은 문자이다.
		만약 문자가 "+", "-", "*", "/"라면, 메서드는 입력된 문자에 해당하는 연산 결과를 반환한다.
		만약 정해지지 않은 문자가 입력된다면 메서드는 null을 반환한다. 
		
		calculate(2,"+", 4); //Should return 6
		calculate(6,"-", 1.5); //Should return 4.5
		calculate(-4,"*", 8); //Should return -32
		calculate(49,"/", -7); //Should return -7
		calculate(8,"m", 2); //Should return null
		calculate(4,"/",0) //should return null
	 */
	@Test
	public void calculate() {
		Assert.assertEquals("3.2 + 8 = 11.2", new Double(11.2), o.calculate(3.2,"+", 8));
		Assert.assertEquals("3.2 - 8 = -4.8", new Double(-4.8), o.calculate(3.2,"-", 8));
		Assert.assertEquals("3.2 / 8 = .4", new Double(0.4), o.calculate(3.2,"/", 8));
		Assert.assertEquals("3.2 * 8 = 25.6", new Double(25.6), o.calculate(3.2,"*", 8));
		Assert.assertEquals("-3 + 0 = -3", new Double(-3), o.calculate(-3,"+", 0));
		Assert.assertEquals("-3 - 0 = -3", new Double(-3), o.calculate(-3,"-", 0));
		Assert.assertEquals("-3 / 0 = null", null, o.calculate(-3,"/", 0));
		Assert.assertEquals("-2 / -2 = 1", new Double(1), o.calculate(-2, "/", -2));
		Assert.assertEquals("-3 * 0 = 0", new Double(0), o.calculate(-3,"*", 0));
		Assert.assertEquals("-3 w 0 = null", null, o.calculate(-3,"w", 0));
	}
	
}
