package com.tj.suit;

public class Pyramid {
	
	public static String watchPyramidFromTheSide(String characters) {
		String result = "";
		boolean check = true;
		if(characters != null) {
			for(int n=0; n<characters.length(); n++) {
				if(characters.charAt(n) == ' ') {
					check = false;
				}
				else if(n>1) {
					if(characters.charAt(n) == characters.charAt(n-1)) {
						check = false;
					}
				}
			}
		}
		else check = false;
		if(check) {
			int count = characters.length()-1;
			for(int i=0; i<characters.length(); i++) {
				for(int j=0; j<(characters.length()*2)-1; j++) {
					if(j>=characters.length()-1-i && j<=characters.length()-1+i) {
						result += characters.charAt(count);
					}
					else {
						result += " ";
					}
				}
				if(i != characters.length()-1) result += "\n";
				count--;
			}
		}
		if(characters == null) result = characters;
		return result;
	}

	public static String watchPyramidFromAbove(String characters) {
		String result = "";
		boolean check = true;
		int count_i, count_j;
		if(characters != null) {
			for(int n=0; n<characters.length(); n++) {
				if(characters.charAt(n) == ' ' ) {
					check = false;
				}
				else if(n>1) {
					if(characters.charAt(n) == characters.charAt(n-1)) {
						check = false;
					}
				}
			}
		}
		else check = false;
		if(check) {
			for(int i=0; i<characters.length()*2-1; i++) {
				for(int j=0; j<characters.length()*2-1; j++) {
					if(i>=characters.length()-1) {
						count_i = i - (characters.length()-1);
					}
					else count_i = characters.length()-1 -i;
					if(j>=characters.length()-1) {
						count_j = j - (characters.length()-1);
					}
					else count_j = (characters.length()-1) -j;
					if(count_i >= count_j) {
						result += characters.charAt(characters.length()-1-count_i);
					}
					else result += characters.charAt(characters.length()-1-count_j);
				}
				if(i != (characters.length()*2)-2) result += "\n";
			}
		}
		if(characters == null) result = characters;
		return result;
	}

	public static int countVisibleCharactersOfThePyramid(String characters) {
		boolean check = true;
		if(characters != null && characters != "") {
			for(int n=0; n<characters.length(); n++) {
				if(characters.charAt(n) == ' ' ) {
					check = false;
				}
			}
		}
		else check = false;
		if(check) {
			return (characters.length()*2 -1)*(characters.length()*2 -1);
		}
		else return -1;
	}

	public static int countAllCharactersOfThePyramid(String characters) {
		boolean check = true;
		int sum = 0;
		if(characters != null && characters != "") {
			for(int n=0; n<characters.length(); n++) {
				if(characters.charAt(n) == ' ' ) {
					check = false;
				}
			}
		}
		else check = false;
		if(check) {
			for(int n=1; n<=characters.length(); n++) {
				sum += ((n*2)-1)*((n*2)-1);
			}
			return sum;
		}
		else return -1;
	}
}