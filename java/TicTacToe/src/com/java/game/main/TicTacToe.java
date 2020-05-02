package com.java.game.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

	static List<Integer> markedPositions = new ArrayList<Integer>();
	static List<Integer> playerPostions = new ArrayList<Integer>();
	static List<Integer> cpuPositions = new ArrayList<Integer>();
	
	public static void main(String[] args) throws IOException {
		
		//character array for printing game board
		char board[][] = {
				{' ','|',' ','|',' ',},
				{'-','+','-','+','-'},
				{' ','|',' ','|',' ',},
				{'-','+','-','+','-'},
				{' ','|',' ','|',' ',}
		};
		
		//print the board initially
		printBoard(board);
		
		//Looping data till we get a winner
		while(true) {
			//Enter your position
			int position = scanInput();
			
			//check input is already marked or not
			while(!checkMarkedPosition(position))
			{
				System.out.println("Already marked position!! Please enter again");
				position = scanInput();
			}
			//Place your position on game board
			placeMarker(board, position,"player");
			
			
			//check winner
			if(checkWinner()) {
				printBoard(board);
				break;
			}
			
			
			//place Cpu position on game board
			Random random = new Random();
			int cpuPosition = random.nextInt(9)+1;
			
			while(!checkMarkedPosition(cpuPosition))
			{
				cpuPosition = random.nextInt(9)+1;
			}
			
			//cpu position marker
			placeMarker(board, cpuPosition,"cpu");
			
			//again check winner	
			if(checkWinner()) {
				printBoard(board);
				break;
			}
			
			//again print board
			printBoard(board);


		}		

	}

	private static boolean checkWinner() {
		
		List topRow = Arrays.asList(1,2,3);
		List midRow = Arrays.asList(4,5,6);
		List botRow = Arrays.asList(7,8,9);
		List leftCol = Arrays.asList(1,4,7);
		List rightCol = Arrays.asList(3,6,9);
		List midCol = Arrays.asList(2,5,8);
		List cross1 = Arrays.asList(1,5,9);
		List cross2 = Arrays.asList(7,5,3);
		
		List<List> winningPosition = new ArrayList<List>();
		winningPosition.add(topRow);
		winningPosition.add(midRow);
		winningPosition.add(botRow);
		winningPosition.add(leftCol);
		winningPosition.add(rightCol);
		winningPosition.add(midCol);
		winningPosition.add(cross1);
		winningPosition.add(cross2);
		
		for( List win: winningPosition) {
			
			if(cpuPositions.containsAll(win)) {
				System.out.println("You Lost !! Try Again !!");
				return true;
			}
			else if(playerPostions.containsAll(win)) {
				System.out.println("You Won !! WooHoo !!");
				return true;
			}
			else if (markedPositions.size()==9) {
				System.out.println("It's a Tie !!");
				return true;
			}
		}
		return false;
		
		
	}

	private static boolean checkMarkedPosition(int position) {
		if(markedPositions.contains(position)) {
			return false;
		}
		else
			return true;
	}

	private static int scanInput() {
		Scanner scan = new Scanner(System.in);
		System.out.println("Enter your position (1-9):");
		int position = scan.nextInt();
		return position;
	}

	private static void placeMarker(char[][] board, int position, String side) {
		char mark;
		if(side.equals("player")) {
			mark='X';
			playerPostions.add(position);
			markedPositions.add(position);
		}
		else {
			mark='O';
			cpuPositions.add(position);
			markedPositions.add(position);
		}
		switch(position) {
		case 1:
			board[0][0]=mark;
			break;
		case 2:
			board[0][2]=mark;
			break;
		case 3:
			board[0][4]=mark;
			break;
		case 4:
			board[2][0]=mark;
			break;
		case 5:
			board[2][2]=mark;
			break;
		case 6:
			board[2][4]=mark;
			break;
		case 7:
			board[4][0]=mark;
			break;
		case 8:
			board[4][2]=mark;
			break;
		case 9:
			board[4][4]=mark;
			break;
		
		}
	}
	
	
	

	private static void printBoard(char[][] board) {
		for(char[] row: board) {
			for(char c: row) {
			System.out.print(c);
			}
			System.out.println();
		}
	}

}
