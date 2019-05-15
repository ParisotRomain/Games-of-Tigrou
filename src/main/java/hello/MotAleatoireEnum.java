package hello;

import java.util.Random;

public class MotAleatoireEnum {
	private static Random rand = new Random();
	
	public static String get() {
		Mots[] mots = Mots.values();
		int index = rand.nextInt(mots.length);
		
		String monMot = mots[index].name();
		
		return monMot;
		
	}
}
