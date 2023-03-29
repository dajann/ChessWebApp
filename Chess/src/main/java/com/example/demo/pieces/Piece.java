 package com.example.demo.pieces;

import com.example.demo.ChessBoard;

public abstract class Piece{
	public String name;
	protected boolean isWhite;
	protected int x;
	protected int y;
	
	Piece(String name, boolean isWhite, int x, int y){
		this.name = name;
		this.isWhite = isWhite;
		this.x = x;
		this.y = y;
	}
	
	public boolean isWhite() {
		return this.isWhite;
	}
	
	public abstract boolean canMoveTo(int x_destination, int y_destination, ChessBoard chessBoard);
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
}
