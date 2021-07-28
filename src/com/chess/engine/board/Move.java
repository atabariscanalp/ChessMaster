package com.chess.engine.board;

import com.chess.engine.pieces.Piece;

import java.awt.*;

public abstract class Move {

    final Board board;
    final Piece movedPiece;
    final Point destinationCoordinate;

    private Move(final Board board, final Piece movedPiece, final Point destCoordinate) {
        this.board = board;
        this.movedPiece = movedPiece;
        this.destinationCoordinate = destCoordinate;
    }

    public static final class MajorMove extends Move {

        public MajorMove(Board board, Piece movedPiece, Point destCoordinate) {
            super(board, movedPiece, destCoordinate);
        }
    }

    public static final class AttackMove extends Move {

        final Piece attackedPiece;

        public AttackMove(Board board, Piece movedPiece, Piece attackedPiece, Point destCoordinate) {
            super(board, movedPiece, destCoordinate);
            this.attackedPiece = attackedPiece;
        }
    }

}
