package com.tj.suit;

public class Test01 {
	public static void main(String[] args) {
		String characters = "614KH";
		System.out.println(Pyramid.watchPyramidFromTheSide(characters));
		System.out.println();
		System.out.println(Pyramid.watchPyramidFromAbove(characters));
		System.out.println();
		System.out.println(Pyramid.countVisibleCharactersOfThePyramid(""));
		System.out.println(Pyramid.countAllCharactersOfThePyramid(""));
		System.out.println(Pyramid.countVisibleCharactersOfThePyramid(null));
		System.out.println(Pyramid.countAllCharactersOfThePyramid(null));
		System.out.println(Pyramid.watchPyramidFromTheSide(""));
		System.out.println(Pyramid.watchPyramidFromAbove(""));
	}
}
