package testing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Test {
	
	static ArrayList<Integer> playerPos = new ArrayList<Integer>();
	static ArrayList<Integer> cpuPos = new ArrayList<Integer>();
	
	public static void main(String[] args) {
		char[][] board = {{(' '), ('|'), (' '), ('|'), (' ')},
						{('-'), ('+'), ('-'), ('+'), ('-')},
						{(' '), ('|'), (' '), ('|'), (' ')},
						{('-'), ('+'), ('-'), ('+'), ('-')},
						{(' '), ('|'), (' '), ('|'), (' ')}};
		showBoard(board);
		
		Scanner sc = new Scanner(System.in);

		char symbolCpu = 'O';
		
		char symbolPlayer = 'X';
				
		while(true) {
			System.out.print("Enter the your position");
			int posPlayer = sc.nextInt();
			System.out.println("You chose position: " + posPlayer);
			while(playerPos.contains(posPlayer) || cpuPos.contains(posPlayer)) {
				System.out.println("Sorry Position Taken, Enter valid position");
				posPlayer = sc.nextInt();
			}
			place(board, posPlayer, symbolPlayer);
			playerPos.add(posPlayer);
			
			showBoard(board);
			
			System.out.println();
			System.out.println();
			
			boolean result = checkWinner(board);
			if(!result)
				break;
			
			Random rand = new Random();
			int posCpu= rand.nextInt(9) + 1;
			
			while(playerPos.contains(posCpu) || cpuPos.contains(posCpu)) {
				posCpu= rand.nextInt(9) + 1;
			}
			
			place(board, posCpu, symbolCpu);
			cpuPos.add(posCpu);
			
			showBoard(board);
			
			System.out.println();
			System.out.println();
			
			result = checkWinner(board);
			if(!result)
				break;
		}
		
		System.out.println("Game Over");
		
	}
	public static void showBoard(char[][] board){
		for(int i = 0; i < 5; i++) {
			for(int j = 0; j < 5; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
	
	public static void place(char[][] board, int posPlayer, char symbol) {
		
		switch (posPlayer){
			case 1:
				board[0][0] = symbol;
				break;
			case 2:
				board[0][2] = symbol;
				break;
			case 3:
				board[0][4] = symbol;
				break;
			case 4:
				board[2][0] = symbol;
				break;
			case 5:
				board[2][2] = symbol;
				break;
			case 6:
				board[2][4] = symbol;
				break;
			case 7:
				board[4][0] = symbol;
				break;
			case 8:
				board[4][2] = symbol;
				break;
			case 9:
				board[4][4] = symbol;
				break;
			default:
				System.out.println("Enter valid position");
				break;
		}
	}

	public static boolean checkWinner(char[][] board) {
		List<Integer> topRow = Arrays.asList(1, 2, 3);
		List<Integer> midRow = Arrays.asList(4, 5, 6);
		List<Integer> botRow = Arrays.asList(7, 8, 9);
		
		List<Integer> rightCol = Arrays.asList(1, 4, 7);
		List<Integer> midCol = Arrays.asList(2, 5, 8);
		List<Integer> leftCol = Arrays.asList(3, 6, 9);
		
		List<Integer> diag1 = Arrays.asList(1, 5, 9);
		List<Integer> diag2 = Arrays.asList(3, 5, 7);
		
		List<List> winning = new ArrayList<List>();
		winning.add(topRow);
		winning.add(midRow);
		winning.add(botRow);
		winning.add(rightCol);
		winning.add(midCol);
		winning.add(leftCol);
		winning.add(diag1);
		winning.add(diag2);
		
		for(List l : winning) {
			if(playerPos.containsAll(l)) {
				System.out.print("You WON!!");
				return false;
			}
			if(cpuPos.containsAll(l)) {
				System.out.print("Sorry you lose :(");
				return false;
			}
			if(cpuPos.size() + playerPos.size() == 9) {
				System.out.print("Match TIE :) ");
				return false;
			}
		}
		return true;
	}
}