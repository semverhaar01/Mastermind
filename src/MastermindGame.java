import java.util.*;
public class MastermindGame {
	private static final char[] letters = {'a', 'b', 'c', 'd', 'e', 'f'};
    private static final int codeLength = 4;
    
    private char[] code;
    private boolean gameWon;
    
    public void playGame() {
    	generateCode();
    	System.out.println("Welkom bij Mastermind!");
    	System.out.println("Probeer de code te kraken door deze in te voeren en op enter te klikken.");
    	System.out.println("De code bestaat uit vier letters (a, b, c, d, e, f), waarvan de letters er meerdere keren in kunnen zitten.");
    
    	Scanner scanner = new Scanner(System.in);
    	while(!gameWon) {
    		System.out.println("Voer een code in. Voer q in om het spel te stoppen.");
    		String input = scanner.nextLine().toLowerCase();
    		
    		if (input.equals("q")) {
    			System.out.println("Einde spel. Bedankt voor het spelen.");
    			return;
    		}
    		
    		
    		if (isValidCode(input)) {
    			evaluateGuess(input.toCharArray());
    		} else {
    			System.out.println("Ongeldige code. Probeer opnieuw een code van vier letters in te voeren die bestaat uit (a, b, c, d, e, f)");
    		}
    	}
    	scanner.close();
    }
    
    private void generateCode() {
    	Random random = new Random();
    	code = new char[codeLength];
    	for (int i = 0; i<codeLength; i++) {
    		int index = random.nextInt(letters.length);
    		code[i]=letters[index];
    	}
    }
    
    private boolean isValidCode(String input) {
    	if(input.length() != codeLength) {
    		return false;
    	}
    	for (char c: input.toCharArray()) {
    		if (!isValidLetter(c)) {
    			return false;
    		}
    	}
    	return true;
    }
    
    private boolean isValidLetter(char c) {
    	for (char letter : letters) {
    		if (letter == c) {
    			return true;
    		}
    	}
    	return false;
    }
    
    private void evaluateGuess(char[] guess) {
    	int correctPositions = 0;
    	int correctLetters = 0;
    	
    	for (int i = 0; i< codeLength; i++) {
    		if(guess[i]==code[i]) {
    			correctPositions++;
    		}else if (containsLetter(guess[i])) {
    			correctLetters++;
    		}
    	}
    	if (correctPositions == codeLength) {
    		gameWon = true;
    		System.out.println("Je hebt de code gekraakt. Gefeliciteerd!");
    	} else {
    		System.out.println("Correcte posities: "+ correctPositions);
    		System.out.println("Correcte letters: "+ correctLetters);
    	}
    }
    
    private boolean containsLetter(char letter) {
    	for (char c:code) {
    		if (c==letter) {
    			return true;
    		}
    	}
    	return false;
    }

	public static void main(String[] args) {
		MastermindGame game = new MastermindGame();
		game.playGame();

	}

}
