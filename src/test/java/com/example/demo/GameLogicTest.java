package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.SQLException;
import java.util.List;

@SpringBootTest
class GameLogicTest {

	@Autowired
	userRepository repository;

	//static GameLogic sut;
	static GameLogic sut;

	@BeforeAll
	static void before() {
		sut = new GameLogic();
	}

	@Test
	void contextLoads(){
	}

	@Test
	public void numberOfPlayingCards(){
		List<MemoryCard> listOfCards = sut.createCards(16);
		Assertions.assertEquals(16, listOfCards.size());
	}

	@Test
	void testSQLServler() throws SQLException {
		Assertions.assertTrue(repository.testDB());
	}

//	@Test
//	public void testUniqueId(){
//		List<MemoryCard> listOfCards = sut.createCards(16);
//	}

}
