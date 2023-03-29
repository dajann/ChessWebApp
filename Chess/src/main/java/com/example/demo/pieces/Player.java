package com.example.demo.pieces;

import com.example.demo.ChessBoard;
 
public class Player {
	private String id;
	private boolean isWhite;
	private Piece[] pieces;
	
		public Player(boolean is_White, Piece[] pieces){
		this.isWhite = is_White;
		this.pieces = pieces;
	}
	public boolean move (ChessBoard chessBoard, int[] array) {
		int x = array[0];
		int y = array[1];
		int x_destination = array[2];
		int y_destination = array[3];
			if(chessBoard.board[x][y] != null && 
					this.isWhite == chessBoard.board[x][y].isWhite && 
					(chessBoard.board[x_destination][y_destination] == null || 
					chessBoard.board[x_destination][y_destination].isWhite() != this.isWhite)) {
				
				if(chessBoard.board[x][y].canMoveTo(x_destination, y_destination, chessBoard) &&
						!chessBoard.isCheck(chessBoard.board[x][y].isWhite,x, y, x_destination, y_destination)) {
					if(chessBoard.board[x_destination][y_destination] != null) {
						chessBoard.board[x_destination][y_destination].setX(-1);
						chessBoard.board[x_destination][y_destination].setY(-1);
					}
					chessBoard.board[x_destination][y_destination] = chessBoard.board[x][y];
					chessBoard.board[x_destination][y_destination].setX(x_destination);
					chessBoard.board[x_destination][y_destination].setY(y_destination);
					chessBoard.board[x][y] = null;
					return true;
				}
			}
		return false;
	}
	
	
	@Override
	 public String toString() {
		if(isWhite)
			return "Player1";
		return "Player2";
	}

	public boolean is_White() {
		return isWhite;
	}
	public Piece[] getPieces() {
		return pieces;
	}
	public void setPieces(Piece[] pieces) {
		this.pieces = pieces;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
}
