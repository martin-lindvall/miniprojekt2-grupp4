package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {

	public static void main(String[] args) {
		SpringApplication.run(Main.class, args);

		int numOfPlayingCards = 16;
		GameLogic gameLogic = new GameLogic();

		gameLogic.createCards(numOfPlayingCards);

	}

}
