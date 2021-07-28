package com.chess.engine.board;

import com.chess.engine.pieces.Piece;
import com.google.common.collect.ImmutableMap;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public abstract class Tile {

    protected final Point tileCoordinate;

    private static final Map<Point, EmptyTile> EMPTY_TILES = createAllPossibleEmptyTiles();

    // Creates all empty tiles and caches, so there won't be any empty tile construction again
    private static Map<Point, EmptyTile> createAllPossibleEmptyTiles() {

        final Map<Point, EmptyTile> emptyTileMap = new HashMap<>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                emptyTileMap.put(new Point(i,j), new EmptyTile(new Point(i,j)));
            }
        }


        return ImmutableMap.copyOf(emptyTileMap);
    }

    public static Tile createTile(final Point tileCoordinate, final Piece piece) {
        return piece != null ? new OccupiedTile(tileCoordinate, piece) : EMPTY_TILES.get(tileCoordinate);
    }

    private Tile(Point tileCoordinate) {
        this.tileCoordinate = tileCoordinate;
    }

    public abstract boolean isTileOccupied();

    public abstract Piece getPiece();

    public static final class EmptyTile extends Tile {

        private EmptyTile(final Point coordinate) {
            super(coordinate);
        }

        @Override
        public boolean isTileOccupied() {
            return false;
        }

        @Override
        public Piece getPiece() {
            return null;
        }
    }

    public static final class OccupiedTile extends Tile {

        private final Piece pieceOnTile;

        private OccupiedTile(Point coordinate, Piece pieceOnTile) {
            super(coordinate);
            this.pieceOnTile = pieceOnTile;
        }

        @Override
        public boolean isTileOccupied() {
            return true;
        }

        @Override
        public Piece getPiece() {
            return pieceOnTile;
        }
    }

}
