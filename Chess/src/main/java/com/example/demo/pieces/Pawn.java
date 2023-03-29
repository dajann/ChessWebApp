package com.example.demo.pieces;

import com.example.demo.ChessBoard;

public class Pawn extends Piece {
	
	public Pawn(String name, boolean isWhite, int  x, int y){
		super(name, isWhite, x, y);
	}

	@Override
	public boolean canMoveTo(int x_destination, int y_destination, ChessBoard chessBoard) {
		if(isWhite && x_destination == x - 1 && y_destination == y && chessBoard.board[x_destination][y_destination]  ==  null)
			return true;
		else if(isWhite && x_destination == x - 1 && ((y_destination == y + 1) || y_destination == y - 1) &&
				chessBoard.board[x_destination][y_destination] != null)
			return true;
		else if(!isWhite && x_destination == x + 1 && y_destination == y && chessBoard.board[x_destination][y_destination]  ==  null)
			return true;
		else if(!isWhite && x_destination == x + 1 && ((y_destination == y + 1) || y_destination == y - 1) &&
				chessBoard.board[x_destination][y_destination] != null)
			return true;
		return false;
	}
}
