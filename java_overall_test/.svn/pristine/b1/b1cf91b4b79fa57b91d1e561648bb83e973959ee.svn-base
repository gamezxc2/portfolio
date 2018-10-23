package com.tj.app;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.tj.suit.Pyramid;

public class PyramidApp {
	/*

	주어진 문자열을 이용해 피라미드를 건설하고자 합니다.

	아래 메서드의 구현부를 완성하면 됩니다.
	
	public static String watchPyramidFromTheSide(String characters)
		이 메서드는 피라미드를 옆에서 보았을때 모습을 출력하면 됩니다.
		The first method ("FromTheSide") shows the pyramid as you would see from the side.
	public static String watchPyramidFromAbove(String characters)
		이 메서드는 피라미드를 위에서 내려보았을때의 모습을 출력하면 됩니다.
	public static int countVisibleCharactersOfThePyramid(String characters)
		이 메서드는 위에서 내려보았을때 보이는 블럭(문자)의 수를 계산하면 됩니다.
	public static int countAllCharactersOfThePyramid(String characters)
		이 메서드는 피라미드가 완성되었을때 구성하고 있는 모든 블럭(문자)의 수를 계산하면 됩니다.
	
	주어진 문자열에서 각 문자는 피라미드의 한 층(floor)을 구성하게 됩니다. 따라서 주어진 문자열의 길이가 피라미드의 총 층수(floor)가 됩니다.
	각 층은 주어진 문자열에 맞게끔 지어져야 합니다. 피라미드의 각 층은 정사각형으로 구성됩니다. 
	
	Example
	 - Given string: "abc"
	
	피라미드를 옆에서 보았을 때:
	  c
	 bbb
	aaaaa
	
	피라미드를 위에서 내려다 보았을 때:	
	aaaaa
	abbba
	abcba
	abbba
	aaaaa
	
	위에서 내려다 보았을때 보이는 블럭(문자)의 수 : 25
	피라미드를 구성하는 모든 블럭(문자)의 수 : 35
	
	주어진 문자열은 연속된 문자/숫자가 아니고 문자열의 길이 제한은 없습니다. 또한 공백일 수 없습니다. [aaaaa(x) abcde(o), 11111(x) 12345(o)]
	
	피라미드를 옆에서 보았을 때의 모습을 출력할 땐, 직 사각형안에 블럭(문자)가 출력되어야 합니다. 그러므로 줄 끝의 공백을 잘 생각해야 합니다.
	만약 문자열이 null이거나 비어있다면 watch-메서드는 빈 문자열을 count-메서드는 -1을 반환합니다.
	 */
	
	@Test
    public void nullEmptyTests() {
        assertEquals(null, Pyramid.watchPyramidFromTheSide(null));
        assertEquals(null, Pyramid.watchPyramidFromAbove(null));
        assertEquals(-1, Pyramid.countVisibleCharactersOfThePyramid(null));
        assertEquals(-1, Pyramid.countAllCharactersOfThePyramid(null));
        assertEquals("", Pyramid.watchPyramidFromTheSide(""));
        assertEquals("", Pyramid.watchPyramidFromAbove(""));
        assertEquals(-1, Pyramid.countVisibleCharactersOfThePyramid(""));
        assertEquals(-1, Pyramid.countAllCharactersOfThePyramid(""));
    }
	
	@Test
    public void pyramidTest1() {
		System.out.println("***************** pyramidTest1 *****************");
        String characters = "*#";
        String expectedWatchFromSide = 
        " # \n" +
        "***";    
        String expectedWatchFromAbove = 
        "***\n" + 
        "*#*\n" + 
        "***";
        String actualWatchFromSide = Pyramid.watchPyramidFromTheSide(characters);
        String actualWatchFromAbove = Pyramid.watchPyramidFromAbove(characters);
        visualisation(expectedWatchFromSide, expectedWatchFromAbove, actualWatchFromSide, actualWatchFromAbove);
        assertEquals(9, Pyramid.countVisibleCharactersOfThePyramid(characters));
        assertEquals(10, Pyramid.countAllCharactersOfThePyramid(characters));        
    }
    
    @Test
    public void pyramidTest2() {
    	System.out.println("***************** pyramidTest2 *****************");
        String characters = "abc";
        String expectedWatchFromSide = 
        "  c  \n" +
        " bbb \n" +
        "aaaaa";
        String expectedWatchFromAbove = 
        "aaaaa\n" +
        "abbba\n" + 
        "abcba\n" + 
        "abbba\n" + 
        "aaaaa";
        String actualWatchFromSide = Pyramid.watchPyramidFromTheSide(characters);
        String actualWatchFromAbove = Pyramid.watchPyramidFromAbove(characters);
        visualisation(expectedWatchFromSide, expectedWatchFromAbove, actualWatchFromSide, actualWatchFromAbove);
        assertEquals(25, Pyramid.countVisibleCharactersOfThePyramid(characters));
        assertEquals(35, Pyramid.countAllCharactersOfThePyramid(characters));
    }
    
    @Test
    public void pyramidTest3() {
    	System.out.println("***************** pyramidTest3 *****************");
        String characters = "abcde";
        String expectedWatchFromSide = 
        "    e    \n" +
        "   ddd   \n" +  
        "  ccccc  \n" +
        " bbbbbbb \n" +
        "aaaaaaaaa";    
        String expectedWatchFromAbove = 
         "aaaaaaaaa\n"
       + "abbbbbbba\n"
       + "abcccccba\n"
       + "abcdddcba\n"
       + "abcdedcba\n"
       + "abcdddcba\n"
       + "abcccccba\n"
       + "abbbbbbba\n"
       + "aaaaaaaaa";
        String actualWatchFromSide = Pyramid.watchPyramidFromTheSide(characters);
        String actualWatchFromAbove = Pyramid.watchPyramidFromAbove(characters);
        visualisation(expectedWatchFromSide, expectedWatchFromAbove, actualWatchFromSide, actualWatchFromAbove);
        assertEquals(81, Pyramid.countVisibleCharactersOfThePyramid(characters));
        assertEquals(165, Pyramid.countAllCharactersOfThePyramid(characters));        
    }
    
    @Test
    public void basicTest5DescendingOrderedCharacters() {
    	System.out.println("***************** basicTest5DescendingOrderedCharacters *****************");
        String characters = "54321";
        String expectedWatchFromSide = 
        "    1    \n" +
        "   222   \n" +
        "  33333  \n" +
        " 4444444 \n" +
        "555555555";
        String expectedWatchFromAbove = 
        "555555555\n" +
        "544444445\n" +
        "543333345\n" +
        "543222345\n" +
        "543212345\n" +
        "543222345\n" +
        "543333345\n" +
        "544444445\n" +
        "555555555";
        String actualWatchFromSide = Pyramid.watchPyramidFromTheSide(characters);
        String actualWatchFromAbove = Pyramid.watchPyramidFromAbove(characters);
        visualisation(expectedWatchFromSide, expectedWatchFromAbove, actualWatchFromSide, actualWatchFromAbove);
        assertEquals(81, Pyramid.countVisibleCharactersOfThePyramid(characters));
        assertEquals(165, Pyramid.countAllCharactersOfThePyramid(characters));
    }
    
    private void visualisation(String expectedWatchFromSide, String expectedWatchFromAbove, String actualWatchFromSide, String actualWatchFromAbove) {
    	System.out.println("From side correct:\n" + expectedWatchFromSide);
        System.out.println("From side yours:\n" + actualWatchFromSide);
        System.out.println("-------------------------------------------------");
        System.out.println("From above correct:\n" + expectedWatchFromAbove);
        System.out.println("From above yours:\n" + actualWatchFromAbove);
        assertEquals(expectedWatchFromSide, actualWatchFromSide);
        assertEquals(expectedWatchFromAbove, actualWatchFromAbove);
    }
}
