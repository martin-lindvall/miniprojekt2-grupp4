package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
public class GameController {

    @Autowired
    GameLogic gl;

    @GetMapping("/grid")
    public String gameBoard(HttpSession session, @RequestParam(required = false, defaultValue = "0") Integer cardId, @RequestParam(required = false, defaultValue = "0") Boolean resetGame) {
        GameLogic gameLogic = (GameLogic)session.getAttribute("gameLogicKey");
        UserInfo user = (UserInfo) session.getAttribute("userkey");

        if (user != null && user.getLoggedIn()) {


            if(gameLogic == null) {
                gameLogic = gl;
                gameLogic.createCards(Main.numOfPlayingCards);
                //gameLogic.shuffleCards();
                gameLogic.splitListOfCards();
                gameLogic.setCountZero();
                gameLogic.generatePlayerHighScore(user);


                session.setAttribute("gameLogicKey", gameLogic);
            }
            gameLogic.setCount();
            gameLogic.turnCard(cardId);
            gameLogic.setCardsEqual(false);
            gameLogic.setCardsNotEqual(false);
            if (gameLogic.getMatchList().size() % 2 == 0) {
                gameLogic.ifCardsNotEqual();
            }
            if(cardId != 0){
                gameLogic.matchCards(cardId);
            }

            Boolean win = gameLogic.checkWinCondition(user);
            //if(win){
                //int score = getCount();
          //  }



//            if (gameLogic.getGameFinish()) {
//                System.out.println("win");
//                user.setLowScore(gameLogic.getCount());
//
//            }


            if(resetGame){
                gameLogic = null;
                session.setAttribute("gameLogicKey", gameLogic);
                System.out.println("Reset game");
                return "redirect:/grid";
            }

            return "gameGrid2";


        }
        else {

            return "login";
        }
    }
}
