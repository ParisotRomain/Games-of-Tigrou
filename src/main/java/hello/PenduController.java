package hello;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Pendu.Game;

@Controller
public class PenduController {

	@GetMapping("/pendu")
	public String greeting(@RequestParam(name="letter" , required=false, defaultValue=" "   ) String letter,
			@RequestParam(name="newGame", required=false, defaultValue="false") boolean newGame,
			Model model,
			HttpSession session) {

		if (newGame) {
			session.setAttribute("Game", new Game());
//			Game.initNewGame();
		}
		
		Game game = (Game) session.getAttribute("Game");

		String messageToUser = game.playLetter(letter);
		model.addAttribute("drawing", game.getDrawing());
		model.addAttribute("wordToFind", game.getMotAAfficher());

		switch (game.getStatus()) {
		case "Ongoing":
			model.addAttribute("letter", messageToUser);
			return "pendu";
		case "Lost":
			model.addAttribute("gameWon", false);
			return "pendu-end";
		case "Won":
			model.addAttribute("gameWon", true);
			return "pendu-end";
			
		default :
			return "pendu";
		}
	}

}
