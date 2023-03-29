package com.example.demo.pieces;

import com.example.demo.ChessBoard;

public class King extends Piece {
	public King(String name, boolean isWhite, int  x, int y){
		super(name, isWhite, x, y);
	}
	
	@Override
	 public String toString() {
		if(isWhite)
			return "wK";
		return "bK";
	}

	@Override
	public boolean canMoveTo(int x_destination, int y_destination, ChessBoard chessBoard) {
		if((Math.abs(x - x_destination) == 1 && y - y_destination == 0) || 
				(Math.abs(y - y_destination) == 1 && x - x_destination == 0) || 
				(Math.abs(x - x_destination) == 1 && Math.abs(y - y_destination) == 1)) {
			return true;
		}
		return false;
	}
}
