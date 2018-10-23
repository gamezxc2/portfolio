package com.tj.suit;

public class Overall {
	/*
		입력되는 문자열을 뒤집어 출력하라.
	 */
	public String printInversion(String str) {
		String result = "";
		for(int i=0; i<str.length(); i++) {
			result = str.charAt(i) + result;
		}
		return result;
	}

	/*
 		입력 문자열에서 알파벳, 숫자, 공백의 개수를 구하시오.
	 */
	public int[] countSth(String str) {
		int alpha=0, num=0, space=0;
		for(int i=0; i<str.length(); i++) {
			if((str.charAt(i)>='a' && str.charAt(i)<='z') || (str.charAt(i)>='A' && str.charAt(i)<='Z')) {
				alpha++;
			}
			else if(str.charAt(i)>='0' && str.charAt(i)<='9') {
				num++;
			}
			else if(str.charAt(i) == ' ') {
				space++;
			}
		}
		return new int[] {alpha, num, space};
	}

	/*
	 	입력된 문자열이 회문(거꾸로 읽어도 제대로 읽는 것과 같은 문장이나 낱말)인지 확인하시오.
	 */
	public boolean checkPalindrome(String original) {
		String reverse = "";
		String origin2 = ""; 
		for(int i=0; i<original.length(); i++) {
			if(original.charAt(i) != ' ') {
				if(original.charAt(i)>='A' && original.charAt(i) <='Z') {
					origin2 = origin2 + (char)(original.charAt(i) - 'A' + 'a');
				}
				else origin2 = origin2 + original.charAt(i);
			}
		}
		for(int i=0; i<origin2.length(); i++) {
			reverse = origin2.charAt(i) + reverse;
		}
		original = origin2;
		return original.equals(reverse);
	}

	/*
		0, 1, 3, 6, 10, 15, 21, 28, ...
		0(0), 1(0+1), 3(0+1+2), 6(0+1+2+3)...
		패턴 : n번째의 값은 [0부터 값의 위치]까지 더한 값입니다.
	 */
	public int[] sumOfN(int n) {
		int tmp = n;
		if(tmp < 0) {
			tmp = -tmp;
		}
		int[] sum = new int[tmp+1];
		int value = 0;
		if(n>0) {
			for(int i=0; i<=n; i++) {
				for(int j=0; j<=i; j++) {
					value = value + j;
				}
				sum[i] = value;
				value = 0;
			}
		}
		else if(n<0) {
			for(int i=0; i>=n; i--) {
				for(int j=0; j>=i; j--) {
					value = value + j;
				}
				sum[-i] = value;
				value = 0;
			}
		}
		else return null;
		return sum;
	}

	/*
	 	입력된 숫자가 암스트롱 수 인지 확인하시오.
	 	(암스트롱 수 :  세 자리의 정수 중에서 각 자리의 수를 세 제곱한 수의 합과 자신이 같은 수)
	 	ex) 153 == (1 * 1 * 1) + (5 * 5 * 5) + (3 * 3 * 3)
	 */
	public boolean isArmStrongNum(int num) {
		int tmp = 0, tmp2 = num, sum = 0;
		for(int i=0; i<3; i++) {
			tmp = tmp2%10;
			tmp2 = tmp2/10;
			sum += tmp*tmp*tmp;
		}
		return num == sum;   
	}  

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

	/*
		예시된 문자열의 첫 글자와 마지막 글자를 제거하시오. (입력은 항상 3자 이상)
	 */
	public String removeChar(String str) {
		String tmp = "";
		for(int i =0; i<str.length(); i++) {
			if(i!=0 && i!=str.length()-1) {
				tmp = tmp + str.charAt(i);
			}
		}
		return tmp;
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
	public String maskingPw(String url) {
		String copy = "";
		boolean swapped = false;
		boolean end = false;
		for(int i=0; i<url.length(); i++) {
			if(url.charAt(i)=='&' && swapped) {
				end = true;
			}
			if(swapped && !end) {
				copy = copy + "*";
			}
			else {
				copy = copy + url.charAt(i);
			}
			if(i>0) {
				if(url.charAt(i-1) == 'd') {
					if(url.charAt(i) == '=' && !swapped) {
						swapped = true;
					}
				}
			}
		}
		return copy;
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
	public int getSum(int a, int b) {
		int sum = 0;
		if(a>b) {
			for(int i = b; i<=a; i++) {
				sum += i;
			}
		}
		else if(b>a) {
			for(int i = a; i<=b; i++) {
				sum += i;
			}
		}
		else System.out.println("Since both are same");
		return sum;
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
	public Double calculate(final double numberOne, final String operation, final double numberTwo) {
		Double result = null;
		switch(operation) {
		case "+" :
			result = numberOne + numberTwo;
			break;
		case "-" :
			result = numberOne - numberTwo;
			break;
		case "*" :
			result = numberOne * numberTwo;
			if(result == -0) result = -result;
			break;
		case "/" :
			if(numberTwo != 0) result = numberOne / numberTwo;
			break;
		}
		return result;
	}


}
