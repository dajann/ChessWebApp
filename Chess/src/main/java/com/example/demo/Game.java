package com.example.demo;

import com.example.demo.pieces.Bishop;
import com.example.demo.pieces.King;
import com.example.demo.pieces.Knight;
import com.example.demo.pieces.Pawn;
import com.example.demo.pieces.Piece;
import com.example.demo.pieces.Player;
import com.example.demo.pieces.Queen;
import com.example.demo.pieces.Rook;

public class Game {
	
	String result = "NO";
	String winner;
	public ChessBoard chessBoard;
	
	Piece[] whitePieces = new Piece[16];
	Piece[] blackPieces = new Piece[16];
	
	public Game() {
	
		for(int i = 0; i < 8; i++) {
			whitePieces[i] = new Pawn("wP", true, 6, i);
			blackPieces[i] = new Pawn("bP" , false, 1, i);
		}
		
		whitePieces[8] = new Rook("wR", true, 7, 0);
		whitePieces[9] = new Knight("wH", true, 7, 1);
		whitePieces[10] = new Bishop("wB", true, 7, 2);
		whitePieces[11] = new Queen("wQ", true, 7, 3);
		whitePieces[12] = new King("wK", true, 7, 4);
		whitePieces[13] = new Bishop("wB", true, 7, 5);
		whitePieces[14] = new Knight("wH", true, 7, 6);
		whitePieces[15] = new Rook("wR", true, 7, 7);
		
		blackPieces[8] = new Rook("bR", false, 0, 0);
		blackPieces[9] = new Knight("bH", false, 0, 1);
		blackPieces[10] = new Bishop("bB", false, 0, 2);
		blackPieces[11] = new Queen("bQ", false, 0, 3);
		blackPieces[12] = new King("bK", false, 0, 4);
		blackPieces[13] = new Bishop("bB", false, 0, 5);
		blackPieces[14] = new Knight("bH", false, 0, 6);
		blackPieces[15] = new Rook("bR", false, 0, 7);
			
		chessBoard = new ChessBoard(new Player(true, whitePieces), new Player(false, blackPieces));
	}	 
	
}

