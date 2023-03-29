package com.example.demo.pieces;

import com.example.demo.ChessBoard;

public class Knight extends Piece {
	public Knight(String name, boolean isWhite, int  x, int y){
		super(name, isWhite, x, y);
	}
	
	@Override
	public boolean canMoveTo(int x_destination, int y_destination, ChessBoard chessBoard) {
		if(Math.abs(x - x_destination) == 2 && Math.abs(y - y_destination) == 1)
			return true;
		else if(Math.abs(y - y_destination) == 2 && Math.abs(x - x_destination) == 1)
			return true;
		return false;
	}
}
