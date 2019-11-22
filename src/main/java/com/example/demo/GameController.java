package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class GameController {

    @GetMapping("/grid")
    public String gameBoard(HttpSession session, @RequestParam(required = false, defaultValue = "0") Integer cardId) {
        GameLogic gameLogic = (GameLogic)session.getAttribute("gameLogicKey");
        UserInfo user = (UserInfo) session.getAttribute("userkey");

        if (user != null && user.getLoggedIn()) {

            if(gameLogic == null) {
                gameLogic = new GameLogic();
                gameLogic.createCards(Main.numOfPlayingCards);
//                gameLogic.shuffleCards();
                gameLogic.splitListOfCards();
                session.setAttribute("gameLogicKey", gameLogic);
                gameLogic.getCount();
            }
            gameLogic.turnCard(cardId);
            if (gameLogic.getMatchList().size() % 2 == 0) {
                gameLogic.ifCardsNotEqual();
            }
            if(cardId != 0){
                gameLogic.matchCards(cardId);
            }
//            if (gameLogic.getGameFinish()) {
//                System.out.println("win");
//                user.setLowScore(gameLogic.getCount());
//
//            }


            return "gameGrid";


        }
        else {

            return "login";
        }
    }
}
