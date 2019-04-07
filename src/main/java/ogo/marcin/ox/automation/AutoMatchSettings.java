package ogo.marcin.ox.automation;

import ogo.marcin.ox.board.BoardAPI;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Marcin Ogorzalek
 */
public class AutoMatchSettings {
    private final BoardAPI boardAPI;
    private final boolean isAutomated;
    private final Map<String, List<Integer>> winPatterns;
    private List<Integer> listOfMovesXAi;
    private List<Integer> listOfMovesOAi;

    AutoMatchSettings(BoardAPI boardAPI, boolean isAutomated, Map<String, List<Integer>> winPatterns) {
        this.boardAPI = boardAPI;
        this.isAutomated = isAutomated;
        this.winPatterns = winPatterns;
    }

    public AutoMatchSettings(BoardAPI boardAPI, boolean isAutomated) {
        this.boardAPI = boardAPI;
        this.isAutomated = isAutomated;
        this.winPatterns = new HashMap<>();
    }

    public void setXAiMoves(int i) {
        List<String> listOfKeys = new ArrayList<>(this.winPatterns.keySet());
        this.listOfMovesXAi = this.winPatterns.get(listOfKeys.get(i));
        setOAiMoves();
    }

    private void setOAiMoves() {
        this.listOfMovesOAi = new Random().ints(1,
                boardAPI.getBoardDimension()*boardAPI.getBoardDimension() + 1)
                .limit(boardAPI.getBoardDimension()*boardAPI.getBoardDimension() + 1)
                .distinct()
                .filter(s -> !listOfMovesXAi.contains(s))
                .boxed()
                .collect(Collectors.toList());
    }

    public boolean isAutomated() {
        return isAutomated;
    }

    public List<Integer> getListOfMovesXAi() {
        return listOfMovesXAi;
    }

    public List<Integer> getListOfMovesOAi() {
        return listOfMovesOAi;
    }

    public String getPatternName(int i) {
        List<String> listOfKeys = new ArrayList<>(this.winPatterns.keySet());
        return listOfKeys.get(i);
    }
}
