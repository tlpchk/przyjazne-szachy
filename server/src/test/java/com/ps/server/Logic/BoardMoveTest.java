package com.ps.server.Logic;

import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class BoardMoveTest {

    boolean test(String test) {
        Pattern pattern = Pattern.compile("\\/\\/.*");
        List<String> testData = Arrays
                .stream(test.split("\n"))
                .map(e -> e.replaceAll("\\s+",""))
                .filter(pattern.asPredicate().negate())
                .filter(e -> !e.equals(""))
                .collect(Collectors.toList());
        List<String> boardData = testData.subList(0, 9);
        Board board = BoardFactory.createBoard(boardData);
        Color color = testData.get(9).equals("WHITE") ? Color.WHITE : Color.BLACK;
        Pattern positionPattern = Pattern.compile("\\((\\d),(\\d)\\)\\-\\>\\((\\d),(\\d)\\)");
        Matcher matcher = positionPattern.matcher(testData.get(10));
        Position loc, dest;
        if (matcher.find()) {
            loc = new Position(Integer.valueOf(matcher.group(1)), Integer.valueOf(matcher.group(2)));
            dest = new Position(Integer.valueOf(matcher.group(3)), Integer.valueOf(matcher.group(4)));
        } else {
            throw new RuntimeException("Wrong input");
        }
        boolean out = testData.get(11).equals("YES");
        board.updateGame(color);
        return out == (board.validatePlayersMove(loc, dest, color) != null);
    }

    @Test
    public void testBoardMoves() throws FileNotFoundException {
        File file = new File("src/test/java/com/ps/server/Logic/boardMovesTets");
        Scanner sc = new Scanner(file);
        sc.useDelimiter(";");
        int testN = 1;
        while(sc.hasNext()) {
            assertEquals("faild test no: " + testN, true, test(sc.next()));
            testN++;
        }
    }

}
