package com.ps.server.Logic;

import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {
    Board board = new Board();

    @Test
    public void generateTest() {
        System.out.print(board.toString());
    }
}