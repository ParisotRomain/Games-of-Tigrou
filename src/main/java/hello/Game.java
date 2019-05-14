package hello;

import java.io.File;
import java.io.InputStream;
import java.net.URL;

public class Game {
	// fichier avec la liste des mots :
	private static URL url = Game.class.getResource("mots.txt");

	// on crée une liste avec les lettres possible et une avec les lettres jouées
	private static String strAlphabet = "abcdefghijklmnopqrstuvwxyz";
	private static String strJouees   = "..........................";
	private static char[] alphabet = strAlphabet.toCharArray();
	private static char[] jouees   = strJouees  .toCharArray();
	// pour enlever les accents du mot à trouver
	private static char[] avecAccents = "àâéèêîïôùû".toCharArray();
	private static char[] sansAccents = "aaeeeiiouu".toCharArray();

	private static String motATrouver = "hello";
	private static String motAAfficher= ".....";

	private static Integer erreurs = 0;

	public static void initNewGame() {
		// on ouvre le fichier
		System.err.println("Initialisation du game");
		File fichier = new File(url.getPath());
		System.err.println("Fichier ouvert");

		// au début du jeu, on n'a joué aucune lettre
		strJouees   = "..........................";
		jouees   = strJouees  .toCharArray();

		// on récupère un mot aléatoire
		motATrouver = MotAleatoireFichier.getFrom(fichier).toLowerCase();

		// on elève les accents du mot à trouver
		for(int i=0; i<avecAccents.length; i++)
			motATrouver = motATrouver.replace(avecAccents[i], sansAccents[i]);

		// on génère le mot à afficher en remplacant les lettres non jouées par '.'
		motAAfficher = String.valueOf(motATrouver);
		for(int i=0; i<alphabet.length; i++)
			motAAfficher = motAAfficher.replace(alphabet[i], jouees[i]);

		// on raz le compteur d'erreurs
		erreurs = 0;
		
		gameLost = false;
	}


	public static String playLetter(String input) {

		char lettreJouee = input.charAt(0);

		if (strAlphabet.indexOf(lettreJouee) == -1) {	// la lettre ne fait pas partie de l'alphabet
			return("Lettre invalide !");
		} else if (strJouees.indexOf(lettreJouee) != -1) {	// la lettre a déjà été jouée
			return("Lettre déjà jouée");
		} else {	// la lettre est correcte

		}


		// on modifie la liste des lettres jouées pour ajouter la lettre jouée au bon index
		int indJoue = strAlphabet.indexOf(lettreJouee);
		strJouees = strJouees.substring(0, indJoue) + lettreJouee + strJouees.substring(indJoue+1);
		jouees = strJouees.toCharArray();


		if (motATrouver.indexOf(lettreJouee) != -1) { // le mot contient la lettre jouée
			// on met à jour le mot à afficher
			motAAfficher = String.valueOf(motATrouver);
			for(int i=0; i<alphabet.length; i++)
				motAAfficher = motAAfficher.replace(alphabet[i], jouees[i]);
		} else { // le mot ne contient pas la lettre jouée
			erreurs++;
		}

		return("OK");
	}


	public static String getDrawing() {
		switch (erreurs) {
		case 0 :
			return(" " + strJouees.substring( 0, 4) +   " |                          \n"
				 + " " + strJouees.substring( 4, 8) +   " |            v             \n"
				 + " " + strJouees.substring( 8,12) +   " |                   v  __  \n"
				 + " " + strJouees.substring(12,16) +   " |      V              ( ~) \n"
				 + " " + strJouees.substring(16,20) +   " |                    (_~__)\n"
				 + " " + strJouees.substring(20,24) +   " |                      ||  \n"
				 + " " + strJouees.substring(24,26) + "   | wwwwwwwwwwwwwwwwwwwwwwwww");

		case 1 :
			return(" " + strJouees.substring( 0, 4) +   " |                v         \n"
				 + " " + strJouees.substring( 4, 8) +   " |        v            .    \n"
				 + " " + strJouees.substring( 8,12) +   " |                      __  \n"
				 + " " + strJouees.substring(12,16) +   " |                     ( ~) \n"
				 + " " + strJouees.substring(16,20) +   " |                    (_~__)\n"
				 + " " + strJouees.substring(20,24) +   " |                      ||  \n"
				 + " " + strJouees.substring(24,26) + "   | w==========wwwwwwwwwwwwww");

		case 2 :
			return(" " + strJouees.substring( 0, 4) +   " |                          \n"
				 + " " + strJouees.substring( 4, 8) +   " |      ||       v          \n"
				 + " " + strJouees.substring( 8,12) +   " |      ||              __  \n"
				 + " " + strJouees.substring(12,16) +   " |      ||             ( ~) \n"
				 + " " + strJouees.substring(16,20) +   " |      ||            (_~__)\n"
				 + " " + strJouees.substring(20,24) +   " |      ||              ||  \n"
				 + " " + strJouees.substring(24,26) + "   | _==========_wwwwwwwwwwwww");

		case 3 :
			return(" " + strJouees.substring( 0, 4) +   " |                          \n"
				 + " " + strJouees.substring( 4, 8) +   " |      || /                \n"
				 + " " + strJouees.substring( 8,12) +   " |      ||/             __  \n"
				 + " " + strJouees.substring(12,16) +   " |      ||             (__) \n"
				 + " " + strJouees.substring(16,20) +   " |     /||             ~||/ \n"
				 + " " + strJouees.substring(20,24) +   " |    //||              ||  \n"
				 + " " + strJouees.substring(24,26) + "   | _==========____wwwwwwwwww");

		case 4 :
			return(" " + strJouees.substring( 0, 4) +   " |      ,===========        \n"
				 + " " + strJouees.substring( 4, 8) +   " |      || /                \n"
				 + " " + strJouees.substring( 8,12) +   " |      ||/                 \n"
				 + " " + strJouees.substring(12,16) +   " |      ||              ::  \n"
				 + " " + strJouees.substring(16,20) +   " |     /||             ~||/ \n"
				 + " " + strJouees.substring(20,24) +   " |    //||              ||  \n"
				 + " " + strJouees.substring(24,26) + "   | _==========______wwwwwwww");

		case 5 :
			return(" " + strJouees.substring( 0, 4) +   " |      ,========Y==        \n"
				 + " " + strJouees.substring( 4, 8) +   " |      || /     |          \n"
				 + " " + strJouees.substring( 8,12) +   " |      ||/                 \n"
				 + " " + strJouees.substring(12,16) +   " |      ||                  \n"
				 + " " + strJouees.substring(16,20) +   " |     /||                  \n"
				 + " " + strJouees.substring(20,24) +   " |    //||                  \n"
				 + " " + strJouees.substring(24,26) + "   | _==========_______wwwwwww");

		case 6 :
			return(" " + strJouees.substring( 0, 4) +   " |      ,========Y==        \n"
				 + " " + strJouees.substring( 4, 8) +   " |      || /     |          \n"
				 + " " + strJouees.substring( 8,12) +   " |      ||/      O          \n"
				 + " " + strJouees.substring(12,16) +   " |      ||       |          \n"
				 + " " + strJouees.substring(16,20) +   " |     /||                  \n"
				 + " " + strJouees.substring(20,24) +   " |    //||                  \n"
				 + " " + strJouees.substring(24,26) + "   | _==========__________wwww");

		case 7 :
			return(" " + strJouees.substring( 0, 4) +   " |      ,========Y==        \n"
				 + " " + strJouees.substring( 4, 8) +   " |      || /     |          \n"
				 + " " + strJouees.substring( 8,12) +   " |      ||/      O          \n"
				 + " " + strJouees.substring(12,16) +   " |      ||      /|\\         \n"
				 + " " + strJouees.substring(16,20) +   " |     /||                  \n"
				 + " " + strJouees.substring(20,24) +   " |    //||                  \n"
				 + " " + strJouees.substring(24,26) + "   | _==========______________");

		case 8 :
			return(" " + strJouees.substring( 0, 4) +   " |      ,========Y==        \n"
				 + " " + strJouees.substring( 4, 8) +   " |   ~  || /     |     ~    \n"
				 + " " + strJouees.substring( 8,12) +   " |      ||/      O        ~ \n"
				 + " " + strJouees.substring(12,16) +   " |      ||   ~  /|\\         \n"
				 + " " + strJouees.substring(16,20) +   " | ~   /||      /|    ~     \n"
				 + " " + strJouees.substring(20,24) +   " |    //||  ~            ~  \n"
				 + " " + strJouees.substring(24,26) + "   | _==========______________");

		default :
			return null;
		}
	}


	public static String getMotAAfficher() {
		if (erreurs < 8)
			return String.format("%"+(30-motAAfficher.length())/2+"s", " ") + "?  " + motAAfficher + "  ?";
		else
			return String.format("%"+(30-motAAfficher.length())/2+"s", " ") + "X  " + motATrouver + "  X";
	}


	public static Integer getErreurs() {
		return erreurs;
	}


	public static void setMotATrouver(String motATrouver) {
		Game.motATrouver = motATrouver;
	}
	
	public static String getStatus() {
		if (erreurs >= 8)
			return "Lost";
		else if (motAAfficher == motATrouver)
			return "Won";
		else 
			return "Ongoing";
	}
}
