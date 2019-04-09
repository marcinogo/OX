package ogo.marcin.ox.automation;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Marcin Ogorzalek
 */
class WinConditionGenerator {

  final HashMap<String, List<Integer>> winPatterns;
  private final int boardEdge;
  private final int winCondition;

  WinConditionGenerator(int boardEdge, int winCondition) {
    this.boardEdge = boardEdge;
    this.winCondition = winCondition;
    this.winPatterns = new LinkedHashMap<>();
  }

  public WinConditionGenerator generateWinPatternRows() {
    int rowCount = 0;
    for (int i = 1; i <= boardEdge * boardEdge; i += boardEdge) {
      rowCount++;
      int version = 0;
      for (int j = 0; j <= boardEdge - winCondition; j++) {
        List<Integer> winPattern = new LinkedList<>();
        for (int z = 0; z < winCondition; z++) {
          winPattern.add(z + j + i);
        }
        winPatterns.putIfAbsent(String.format("Row %d - version %d",
            rowCount, ++version), winPattern);
      }
    }
    return this;
  }

  public WinConditionGenerator generateWinPatternColumns() {
    int columnCount = 0;
    for (int i = 1; i <= boardEdge; i++) {
      columnCount++;
      int version = 0;
      for (int j = 0; j <= boardEdge * boardEdge - winCondition * boardEdge; j += boardEdge) {
        List<Integer> winPattern = new LinkedList<>();
        for (int z = 0; z < winCondition * boardEdge; z += boardEdge) {
          winPattern.add(z + j + i);
        }
        winPatterns.putIfAbsent(String.format("Column %d - version %d",
            columnCount, ++version), winPattern);
      }
    }
    return this;
  }

  public WinConditionGenerator generateWinPatternDiagonal() {
    int diagonalCount = 0;
    for (int i = 0; i <= boardEdge - winCondition; i++) {
      diagonalCount++;
      int version = 0;
      for (int j = 0; j <= (boardEdge * boardEdge - i / winCondition) - winCondition * boardEdge;
          j += boardEdge) {
        List<Integer> winPattern = new LinkedList<>();
        for (int z = 0; z < winCondition; z++) {
          winPattern.add(i + 1 + z * boardEdge + z + j);
        }
        winPatterns.putIfAbsent(String.format("Diagonal %d - version %d",
            diagonalCount, ++version), winPattern);
      }
    }
    return this;
  }

  public void generateWinPatternAntidiagonal() {
    int diagonalCount = 0;
    for (int i = boardEdge; i >= winCondition; i--) {
      diagonalCount++;
      int version = 0;
      for (int j = 0; j <= (boardEdge * boardEdge + i / winCondition) - winCondition * boardEdge;
          j += boardEdge) {
        List<Integer> winPattern = new LinkedList<>();
        for (int z = 0; z < winCondition * (boardEdge - 1); z += boardEdge - 1) {
          winPattern.add(i + z + j);
        }
        winPatterns.putIfAbsent(String.format("Antidiagonal %d - version %d",
            diagonalCount, ++version), winPattern);
      }
    }
  }
}
