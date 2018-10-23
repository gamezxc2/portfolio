package com.tj.suit;

//import java.util.Arrays;

public class GermanyLotto {
	/*
	독일에는 "LOTTO 6 aus 49" 복권이 있다. 49개의 번호 중 6개를 조합하여 당첨 여부를 가린다. 
	또한 "Superzahl"라는 보너스 번호가 있는데, 이를 이용해 당첨 확률을 높일 수 있다.
	이를 위해 두 가지 메서드를 작성해야 한다.
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
	
	public static int[] numberGenerator(){
		int[] result = new int[7];
		int check = 0;
		while(true) {
			boolean overlap = true;
			int tmp = (int)(Math.random()*49 +1);
			if(check != 0) {
				for(int i=0; i<check; i++) {
					if(result[i] == tmp) overlap = false;
				}
			}
			if(check == 0 || overlap) {
				result[check] = tmp; 
				check++;
			}
			if(check == result.length-1) {
				break;
			}
		}
		boolean swapped = true;
		while(swapped) {
			swapped = false;
			for(int i=0; i<result.length-2; i++) {
				if(result[i] > result[i+1]) {
					int tp = result[i];
					result[i] = result[i+1];
					result[i+1] = tp;
					swapped = true;
				}
			}
		}
		int zahl = (int)(Math.random()*10);
		result[6] = zahl;
		return result;
	}

	public static int checkForWinningCategory(int[] checkCombination, int[] winningCombination) {
		int match = 0;
		boolean superMatch = false;
		for(int i=0; i<6; i++) {
			for(int j=0; j<6; j++) {
				if(checkCombination[i] == winningCombination[j]) {
					match++;
				}
			}
		}
		if(checkCombination[6] == winningCombination[6]) {
			superMatch = true;
		}
		if(match == 6 && superMatch) return 1;
		else if(match == 6 ) return 2;
		else if(match == 5 && superMatch) return 3;
		else if(match == 5 ) return 4;
		else if(match == 4 && superMatch) return 5;
		else if(match == 4 ) return 6;
		else if(match == 3 && superMatch) return 7;
		else if(match == 3 ) return 8;
		else if(match == 2 && superMatch) return 9;
		else return -1;
	}
}
