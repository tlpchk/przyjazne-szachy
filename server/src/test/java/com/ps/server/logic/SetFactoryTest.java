package com.ps.server.Logic;

import org.junit.Test;

public class SetFactoryTest {

    @Test
    public void test() {
        Set whiteSet = new SetFactory.WhiteSetFactory().createSet();
        Set blackSet = new SetFactory.BlackSetFactory().createSet();
        Board board = new Board(whiteSet, blackSet);
        System.out.print(board.toString());
    }

}