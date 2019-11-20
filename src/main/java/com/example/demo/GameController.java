package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class GameController {

    @GetMapping("/grid")
    public String gameBoard(HttpSession session) {
        GameLogic gameLogic = (GameLogic)session.getAttribute("gameLogicKey");
        if(gameLogic == null) {
            gameLogic = new GameLogic();
            gameLogic.createCards(Main.numOfPlayingCards);
            session.setAttribute("gameLogicKey", gameLogic);
        }

        return "gameGrid";
    }

}
