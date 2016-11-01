package utils;

import java.util.Random; 

public class MathUtils {
	
	static Random random;

	static public int generateRandom(int min, int max){
		
		return (int)(Math.random() * (max - min) + min);
	}
	
}
