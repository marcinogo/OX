package ogo.marcin.ox.automation;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Marcin Ogorzalek
 */
public class WinConditionGenerator {
    private final int boardEdge;
    private final int winCondition;
    HashMap<String, List<Integer>> winPatterns;

    public WinConditionGenerator(int boardEdge, int winCondition) {
        this.boardEdge = boardEdge;
        this.winCondition = winCondition;
        this.winPatterns = new LinkedHashMap<>();
    }

    public WinConditionGenerator generateWinPatternRows() {
        int rowCount = 0;
//      boar edge to wysokość
        for (int i = 1; i <= boardEdge*boardEdge; i += boardEdge) {
            rowCount++;
            int version = 0;
//          boar edge to szerokość
            for (int j = 0; j <= boardEdge - winCondition; j ++) {
                List<Integer> winPattern = new LinkedList<>();
                for(int z = 0; z < winCondition; z++) {
                    winPattern.add(z+j+i);
                }
                winPatterns.putIfAbsent(String.format("Row %d - version %d", rowCount, ++version), winPattern);
            }
        }
        return this;
    }

    public WinConditionGenerator generateWinPatternColumns() {
//        board edge to szerokość
        int columnCount = 0;
        for (int i = 1; i <= boardEdge; i++) {
            columnCount++;
//            boar edge to wysokość
            int version = 0;
            for (int j = 0; j <= boardEdge*boardEdge - winCondition*boardEdge; j+=boardEdge) {
                List<Integer> winPattern = new LinkedList<>();
                for(int z = 0; z < winCondition*boardEdge; z+=boardEdge) {
                    winPattern.add(z+j+i);
                }
                winPatterns.putIfAbsent(String.format("Column %d - version %d", columnCount, ++version), winPattern);
            }
        }
        return this;
    }
//    TODO this an atidiagonal
    public WinConditionGenerator generateWinPatternDiagonal() {
//        board edge to szerokość
        int diagonalCount = 0;
        for (int i = 1; i <= boardEdge; i++) {
            diagonalCount++;
//            boar edge to wysokość
            int version = 0;
            for (int j = 0; j <= boardEdge*boardEdge; j+=boardEdge + 1) {
                List<Integer> winPattern = new LinkedList<>();
                for(int z = 0; z < winCondition; z++) {
                    winPattern.add(z*boardEdge+j+i);
                }
                winPatterns.putIfAbsent(String.format("Diagonal %d - version %d", diagonalCount, ++version), winPattern);
            }
        }
        return this;
    }

}
