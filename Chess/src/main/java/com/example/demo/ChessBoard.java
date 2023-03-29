package com.example.demo;

import java.util.List;

import com.example.demo.pieces.Piece;
import com.example.demo.pieces.Player;

public class ChessBoard {
	public Piece[][] board = new Piece[8][8];
	public Player player1;
	public Player player2;
	int moveCounter = 0;
	
	ChessBoard(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
		
		for(int i = 0; i < 8; i++){
			board[6][i] = player1.getPieces()[i];
			board[1][i] = player2.getPieces()[i];
		}
		for(int i = 8; i <16; i++) {
			board[7][i-8] = player1.getPieces()[i];
			board[0][i-8] = player2.getPieces()[i];
		}
	}
	
	 public boolean isCheck(boolean isWhite, int x , int y, int destination_x, int destination_y) {
	    	Piece temp = board[x][y];
			Piece temp_dest = board[destination_x][destination_y];
			temp.setX(destination_x);
			temp.setY(destination_y);
			board[destination_x][destination_y] = temp;
			board[x][y] = null;
			if(temp_dest != null) {
				temp_dest.setX(-1);
				temp_dest.setY(-1);
			}
	    	if(isWhite) {
	    		Piece piece = player1.getPieces()[12];
	    		for(int i = 0; i < player2.getPieces().length; i++) {
	    			if(player2.getPieces()[i].getX() != -1) 
	    				if(player2.getPieces()[i].canMoveTo(piece.getX(), piece.getY(), this)) {
	    					temp.setX(x);
	    					temp.setY(y);
	    					if(temp_dest != null) {
	    						temp_dest.setX(destination_x);
	        					temp_dest.setY(destination_y);
	    					}
	    					board[x][y] = temp;
	    					board[destination_x][destination_y] = temp_dest;
	    					return true;
	    				}
	    		}
	    	}
	    	else {
	    		Piece piece = player2.getPieces()[12];
	    		for(int i = 0; i < player1.getPieces().length; i++) {
	    			if(player1.getPieces()[i].getX() != -1) 
	    				if(player1.getPieces()[i].canMoveTo(piece.getX(), piece.getY(), this)){
	    					temp.setX(x);
	    					temp.setY(y);
	    					if(temp_dest != null) {
	    						temp_dest.setX(destination_x);
	        					temp_dest.setY(destination_y);
	    					}
	    					board[x][y] = temp;
	    					board[destination_x][destination_y] = temp_dest;
	    					return true;
	    				}
	    		}
	    	}
	    	temp.setX(x);
			temp.setY(y);
			board[x][y] = temp;
			board[destination_x][destination_y] = temp_dest;
			
			if(temp_dest != null) {
				temp_dest.setX(destination_x);
				temp_dest.setY(destination_y);
			}
	    	return false;
	    }
	 public String isGameOver(boolean isWhite) {
	    	if(isWhite) {
			for(int i = 0; i < player2.getPieces().length; i++) {
					for(int j = 0; j < 8; j++)
						for(int z = 0; z < 8; z++) 
							if(player2.getPieces()[i].getX() != -1 && player2.getPieces()[i].canMoveTo(j, z, this) && 
							(board[j][z] == null || board[j][z].isWhite()) && 
									!isCheck(!isWhite, player2.getPieces()[i].getX(),
										player2.getPieces()[i].getY(), j, z)) 
								return "NO";
					}
			Piece piece = player2.getPieces()[12];
			for(int i = 0; i < player1.getPieces().length; i++) {
				if(player1.getPieces()[i].getX() != -1) 
					if(player1.getPieces()[i].canMoveTo(piece.getX(), piece.getY(), this)) 
						return "CHECKMATE";
				}
			}
	    	else {
	    		for(int i = 0; i < player1.getPieces().length; i++) {
					for(int j = 0; j < 8; j++)
						for(int z = 0; z < 8; z++)
							if(player1.getPieces()[i].getX() != -1 && player1.getPieces()[i].canMoveTo(j, z, this) && (board[j][z] == null || 
									!board[j][z].isWhite()) &&
									!isCheck(!isWhite, player1.getPieces()[i].getX(),
										player1.getPieces()[i].getY(), j, z)) 
								return "NO";
							}
	    		Piece piece =  player1.getPieces()[12];
	    		for(int i = 0; i < player2.getPieces().length; i++) {
	    			if(player2.getPieces()[i].getX() != -1)
	    				if(player2.getPieces()[i].canMoveTo(piece.getX(), piece.getY(), this)) 
	    					return "CHECKMATE";
	    			}
	    		}
	    	return "STALEMATE";
	    	}
	 
		public Piece[][] restart() {
	    	for(int i = 0; i < 8; i++) {
	    		player1.getPieces()[i].setX(6);
	    	    player1.getPieces()[i].setY(i);
	    		player2.getPieces()[i].setX(1);
	    		player2.getPieces()[i].setY(i);
				
			}
	    	for(int i = 8; i < 16; i++) {
	    		player1.getPieces()[i].setX(7);
	    		player1.getPieces()[i].setY(i-8);
	    		player2.getPieces()[i].setX(0);
	    		player2.getPieces()[i].setY(i-8);
	    		
	    	}
	    	for(int i = 0; i < 8; i++){
				board[6][i] = player1.getPieces()[i];
				board[1][i] = player2.getPieces()[i];
			}
			for(int i = 8; i <16; i++) {
				board[7][i-8] = player1.getPieces()[i];
				board[0][i-8] = player2.getPieces()[i];
			}
			for(int i = 2; i < 6; i++)
				for(int j = 0; j < 8; j++)
					board[i][j] = null;
			moveCounter = 0;
			return board;
		}
		
		public String end() {
	    	if(moveCounter%2 == 0)
	    		return isGameOver(false);
	    	else return isGameOver(true);
		}
		public Piece[][] update(int pos1, int pos2) {
	        int[] array = new int[4];
	        array[0] = pos1 / 8;
	        array[1] = pos1 % 8;
	        array[2] = pos2 / 8;
	        array[3] = pos2 % 8;

	        if (moveCounter % 2 == 0) {
	            while (!player1.move(this, array))
	                return board;
	        } else {
	            while (!player2.move(this, array))
	                return board;
	        }
	        moveCounter++;
	        return board;
	    }

		public Player getCurrentPlayer() {
			if(moveCounter%2 == 0) return player1;
			else return player2;
		}
}
