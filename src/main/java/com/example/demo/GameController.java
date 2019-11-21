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

        if(gameLogic == null) {
            gameLogic = new GameLogic();
            gameLogic.createCards(Main.numOfPlayingCards);
            //gameLogic.shuffleCards();
            gameLogic.splitListOfCards();
            session.setAttribute("gameLogicKey", gameLogic);
        }

        gameLogic.turnCard(cardId);
        //gameLogic.ifCardsNotEqual();

        MemoryCard card = gameLogic.findCardByCardId(cardId);

        if(cardId != 0){
            gameLogic.matchCards(card);
        }
//

//        if(cardId != 0){
//            gameLogic.matchCards2(cardId);
//        }

        return "gameGrid";
    }

}
