package sprintPackage;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import junit.framework.Assert;

public class SOSTests {
    private SOSGamemodes game;

    @BeforeEach
    public void initialize() {
        // set up the game with a 3x3 board
        game = new SOSGamemodes(3);
    }

    // tests blue's victory in a simple game by forming "SOS" across the top row
    @Test
    public void testBlueWinsInSimpleGame() {
        game.makeMove(0, 0, 0); // S
        game.makeMove(0, 1, 1); // O
        game.makeMove(0, 2, 0); // S
        Assertions.assertEquals(SOSGamemodes.GameState.BLUE_WON, game.getGameState());
    }

    // tests red's victory in a simple game by completing "SOS" with red as the winning character
    @Test
    public void testRedWinsInSimpleGame() {
        game.makeMove(2, 2, 0); // S
        game.makeMove(0, 0, 0); // S
        game.makeMove(0, 1, 1); // O
        game.makeMove(0, 2, 0); // S
        Assertions.assertEquals(SOSGamemodes.GameState.RED_WON, game.getGameState());
    }

    // tests for a draw in a simple game where the board is fully occupied without a winner
    @Test
    public void testDrawInSimpleGame() {
        game.makeMove(0, 0, 0); // S
        game.makeMove(0, 1, 0); // S
        game.makeMove(0, 2, 0); // S
        game.makeMove(1, 0, 0); // S
        game.makeMove(1, 1, 0); // S
        game.makeMove(1, 2, 0); // S
        game.makeMove(2, 0, 0); // S
        game.makeMove(2, 1, 0); // S
        game.makeMove(2, 2, 0); // S
        Assertions.assertEquals(SOSGamemodes.GameState.DRAW, game.getGameState());
    }

    // tests blue's victory in a general game by completing an "SOS" on the top row
    @Test
    public void testBlueWinsInGeneralGame() {
        game.setCurrentGamemode(SOSGamemodes.GameType.General);
        game.makeMove(0, 0, 0); // S
        game.makeMove(0, 1, 1); // O
        game.makeMove(0, 2, 0); // S
        game.makeMove(1, 0, 0); // S
        game.makeMove(1, 1, 0); // S
        game.makeMove(1, 2, 0); // S
        game.makeMove(2, 0, 0); // S
        game.makeMove(2, 1, 0); // S
        game.makeMove(2, 2, 0); // S
        Assertions.assertEquals(SOSGamemodes.GameState.BLUE_WON, game.getGameState());
    }

    // tests red's victory in a general game by completing an "SOS" across the board
    @Test
    public void testRedWinsInGeneralGame() {
        game.setCurrentGamemode(SOSGamemodes.GameType.General);
        game.makeMove(2, 2, 0); // S
        game.makeMove(0, 0, 0); // S
        game.makeMove(0, 1, 1); // O
        game.makeMove(0, 2, 0); // S
        game.makeMove(1, 0, 0); // S
        game.makeMove(1, 1, 0); // S
        game.makeMove(1, 2, 0); // S
        game.makeMove(2, 0, 0); // S
        game.makeMove(2, 1, 0); // S
        Assertions.assertEquals(SOSGamemodes.GameState.RED_WON, game.getGameState());
    }
    
  // tests if a the blue computer made a move since blue starts first, and if the turn was red's after the first computer move
  	@Test
  	public void checkComputerMoveBlue() {
  		game.makeRandomMove();
  		Assert.assertEquals(game.getTurn(), 'R');
  	}
  	
  	// tests if a the red computer made a move since blue starts first, then red moves, returning the turn to blue showing that the 
  	@Test
  	public void checkComputerMoveRed() {
  		game.makeRandomMove();
  		game.makeRandomMove();
  		Assert.assertEquals(game.getTurn(), 'B');
  	}
  	
  	// tests if a the computer can complete a game versus another computer in a general game
  	@Test
  	public void checkComputerCompletedGeneralGame() {
  		game.setCurrentGamemode(SOSGamemodes.GameType.General);
  		while(game.getGameState() == game.currentGameState.PLAYING) {
  			game.makeRandomMove();
  		}
  		Assert.assertEquals(game.getNumberOfEmptyCells(), 0);
  	}
  	
  	//tests if a the computer can complete a game versus another computer in a simple game
  	@Test
  	public void checkComputerCompletedSimpleGame() {
  		
  		while(game.getGameState() == game.currentGameState.PLAYING) {
  			game.makeRandomMove();
  		}
  		
  		Assert.assertTrue(game.getGameState() == game.currentGameState.DRAW 
  				|| game.getGameState() == game.currentGameState.RED_WON 
  				|| game.getGameState() == game.currentGameState.BLUE_WON);
  	}
  	
  }
