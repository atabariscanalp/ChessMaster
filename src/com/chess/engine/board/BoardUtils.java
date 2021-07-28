package com.chess.engine.board;

import java.awt.*;

public class BoardUtils {

    public static boolean isValidTile(Point coordinate) {
        return coordinate.x >= 0 && coordinate.y >= 0 && coordinate.x <= 7 && coordinate.y <= 7;
    }
}
