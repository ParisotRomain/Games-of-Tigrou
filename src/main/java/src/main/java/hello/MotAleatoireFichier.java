package src.main.java.hello;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Pattern;

public class MotAleatoireFichier {
	public static String getFrom(String fichier) {		
		
		String monMot = "null";
		String delimiter = "\n";

		try {
			// On ouvre le fichier contenant la liste de mots
//			URL url = Game.class.getResource(fichier);
//			File reader = new File(url.getPath());
			InputStream stream = Game.class.getResourceAsStream(fichier); 
			BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
			Scanner sc = new Scanner(reader);
			sc.useDelimiter(delimiter);

			// On parcours tout le fichier pour compter le nombre de mots
			int nbrMots = 0;
			while(sc.hasNext()) {
				sc.next();
				nbrMots++;
			}
			sc.close();

			// Si on n'a pas trouvé de mot dans le fichier le mot par défaut est "Hello"
			if (nbrMots == 0)
				return "Hello";
			
			// On choisit un indice aléatoire
			Random rand = new Random();
			int index = rand.nextInt(nbrMots);

			// On va récupèrer le mot à l'indice choisi
			stream = Game.class.getResourceAsStream(fichier);
			reader = new BufferedReader(new InputStreamReader(stream));
			sc = new Scanner(reader);
			sc.useDelimiter(delimiter);
			for (int i = 0; i < index; i++) {
				sc.next();
			}
			monMot = sc.next();
			sc.close();

			// On affiche le mot récupéré
			return (monMot);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;

	}
}