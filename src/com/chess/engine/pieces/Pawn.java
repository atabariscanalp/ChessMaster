package com.chess.engine.pieces;

import com.chess.engine.Alliance;
import com.chess.engine.board.Board;
import com.chess.engine.board.Move;
import com.chess.engine.board.Tile;
import com.google.common.collect.ImmutableList;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static com.chess.engine.board.BoardUtils.isValidTile;

public class Pawn extends Piece {

    private final static List<Point> CANDIDATE_MOVE_COORDINATES = new ArrayList<>();

    Pawn(Point piecePosition, Alliance pieceAlliance) {
        super(piecePosition, pieceAlliance);

        if(pieceAlliance.equals(Alliance.WHITE)) {
            CANDIDATE_MOVE_COORDINATES.add(new Point(piecePosition.x + 1, piecePosition.y));
            CANDIDATE_MOVE_COORDINATES.add(new Point(piecePosition.x + 1, piecePosition.y + 1));
            CANDIDATE_MOVE_COORDINATES.add(new Point(piecePosition.x + 1, piecePosition.y - 1));
            if(piecePosition.x == 1) {
                CANDIDATE_MOVE_COORDINATES.add(new Point(piecePosition.x + 2, piecePosition.y));
            }
        } else {
            CANDIDATE_MOVE_COORDINATES.add(new Point(piecePosition.x - 1, piecePosition.y));
            CANDIDATE_MOVE_COORDINATES.add(new Point(piecePosition.x - 1, piecePosition.y + 1));
            CANDIDATE_MOVE_COORDINATES.add(new Point(piecePosition.x - 1, piecePosition.y - 1));
            if(piecePosition.x == 6) {
                CANDIDATE_MOVE_COORDINATES.add(new Point(piecePosition.x - 2, piecePosition.y));
            }
        }
    }

    @Override
    public Collection<Move> calculateLegalMoves(Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for(final Point destinationCoordinate: CANDIDATE_MOVE_COORDINATES) {

            if (isValidTile(destinationCoordinate)) {

                final Tile destinationTile = Board.getTile(destinationCoordinate);

                assert destinationTile != null;
                if (!destinationTile.isTileOccupied()) {
                    legalMoves.add(new Move.MajorMove(board, this, destinationCoordinate));
                } else {
                    final Piece pieceAtDesiredCoordinate = destinationTile.getPiece();
                    final Alliance pieceAlliance = pieceAtDesiredCoordinate.getPieceAlliance();

                    if (this.pieceAlliance != pieceAlliance && destinationCoordinate.y != piecePosition.y)
                        legalMoves.add(new Move.AttackMove(board, this, pieceAtDesiredCoordinate, destinationCoordinate));
                }
            }
        }

        return ImmutableList.copyOf(legalMoves);
    }
}
