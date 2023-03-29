package com.example.demo.pieces;

import com.example.demo.ChessBoard;

public class Queen extends Piece {
	public Queen(String name, boolean isWhite, int  x, int y){
		super(name, isWhite, x, y);
	}
	
	@Override
	public boolean canMoveTo(int x_destination, int y_destination, ChessBoard chessBoard) {
		// algoritam za bishop-a
		if(Math.abs(x - x_destination) == Math.abs(y - y_destination)) {
			if(x > x_destination && y > y_destination) {
				for(int i = 1 ; i < x - x_destination; i++)
					if(chessBoard.board[x-i][y - i] != null)
						return false;
				return true;
			}
			else if(x > x_destination && y < y_destination) {
				for(int i = 1 ; i < x - x_destination; i++)
					if(chessBoard.board[x - i][y + i] != null)
						return false;
				return true;
			}
			else if(x < x_destination && y > y_destination) {
				for(int i = 1 ; i < x_destination - x; i++)
					if(chessBoard.board[x + i][y - i] != null)
						return false;
				return true;
			}
			else if(x < x_destination && y < y_destination) {
				for(int i = 1 ; i < x_destination - x; i++)
					if(chessBoard.board[x + i][y + i] != null)
						return false;
				return true;
			}
		}
		// algoritam za rook-a
		else if(x_destination == x && y_destination != y){
			if(y_destination > y) {
				for(int i = y + 1; i < y_destination; i++) {
					if(chessBoard.board[x_destination][i]!= null)
						return false;
					}
				return true;
				}
			else if(y_destination < y) {
				for(int i = y - 1; i > y_destination; i--) {
					if(chessBoard.board[x_destination][i] != null)
						return false;
					}
				return true;
				}
			}
		
		else if(x_destination != x && y_destination == y){
			if(x_destination > x) {
				for(int i = x + 1; i < x_destination; i++) {
					if(chessBoard.board[i][y_destination] != null)
						return false;
					}
				return true;
				}
			else if(x_destination < x) {
				for(int i = x - 1; i > x_destination; i--) {
					if(chessBoard.board[i][y_destination] != null)
						return false;
					}
				return true;
				}

		}
		return false;
	}
}

