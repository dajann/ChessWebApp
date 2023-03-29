package com.example.demo.pieces;

import com.example.demo.ChessBoard;

public class Bishop extends Piece {
	public Bishop(String name, boolean isWhite, int  x, int y){
		super(name, isWhite, x, y);
	}
	
	@Override
	public boolean canMoveTo(int x_destination, int y_destination, ChessBoard chessBoard) {
		if(Math.abs(x - x_destination) == Math.abs(y - y_destination)) {
			if(x > x_destination && y > y_destination) {
				for(int i = 1 ; i < x - x_destination; i++)
					if(chessBoard.board[x - i][y - i] != null)
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
				for(int i = 1 ; i <x_destination - x; i++)
					if(chessBoard.board[x + i][y + i] != null)
						return false;
				return true;
			}
		}
		return false;
	}
}
