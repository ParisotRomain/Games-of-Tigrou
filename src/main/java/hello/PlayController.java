package hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PlayController {

	@GetMapping("/play")
	public String greeting(@RequestParam(name="letter" , required=false, defaultValue=" "   ) String letter,
			@RequestParam(name="newGame", required=false, defaultValue="false") boolean newGame,
			Model model) {

		if (newGame)
			Game.initNewGame();

		String inputOk = Game.playLetter(letter);
		model.addAttribute("drawing", Game.getDrawing());
		model.addAttribute("wordToFind", Game.getMotAAfficher());

		switch (Game.getStatus()) {
		case "Ongoing":
			if (inputOk != "OK") {
				model.addAttribute("letter", inputOk);
			} else {
				model.addAttribute("letter", letter.charAt(0));
			}
//			model.addAttribute("drawing", Game.getDrawing());
//			model.addAttribute("wordToFind", Game.getMotAAfficher());
			return "play";
		case "Lost":
			model.addAttribute("gameWon", false);
			return "end";
		case "Won":
			model.addAttribute("gameWon", true);
			return "end";
			
		default :
			return "play";
		}
	}

}
