package com.ps.server.controller;

import com.ps.server.domain.Game;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board")
public class BoardController {

    private final String board= "{[{'id':1,'cell_color':'white','piece':{'type':'rook','color':'white'}},{'id':2,'cell_color':'black','piece':{'type':'knight','color':'white'}},{'id':3,'cell_color':'white','piece':{'type':'bishop','color':'white'}},{'id':4,'cell_color':'black','piece':{'type':'king','color':'white'}},{'id':5,'cell_color':'white','piece':{'type':'queen','color':'white'}},{'id':6,'cell_color':'black','piece':{'type':'bishop','color':'white'}},{'id':7,'cell_color':'white','piece':{'type':'knight','color':'white'}},{'id':8,'cell_color':'black','piece':{'type':'rook','color':'white'}},{'id':9,'cell_color':'black','piece':{'type':'pawn','color':'white'}},{'id':10,'cell_color':'white','piece':{'type':'pawn','color':'white'}},{'id':11,'cell_color':'black','piece':{'type':'pawn','color':'white'}},{'id':12,'cell_color':'white','piece':{'type':'pawn','color':'white'}},{'id':13,'cell_color':'black','piece':{'type':'pawn','color':'white'}},{'id':14,'cell_color':'white','piece':{'type':'pawn','color':'white'}},{'id':15,'cell_color':'black','piece':{'type':'pawn','color':'white'}},{'id':16,'cell_color':'white','piece':{'type':'pawn','color':'white'}},{'id':17,'cell_color':'white','piece':null},{'id':18,'cell_color':'black','piece':null},{'id':19,'cell_color':'white','piece':null},{'id':20,'cell_color':'black','piece':null},{'id':21,'cell_color':'white','piece':null},{'id':22,'cell_color':'black','piece':null},{'id':23,'cell_color':'white','piece':null},{'id':24,'cell_color':'black','piece':null},{'id':25,'cell_color':'black','piece':null},{'id':26,'cell_color':'white','piece':null},{'id':27,'cell_color':'black','piece':null},{'id':28,'cell_color':'white','piece':null},{'id':29,'cell_color':'black','piece':null},{'id':30,'cell_color':'white','piece':null},{'id':31,'cell_color':'black','piece':null},{'id':32,'cell_color':'white','piece':null},{'id':33,'cell_color':'white','piece':null},{'id':34,'cell_color':'black','piece':null},{'id':35,'cell_color':'white','piece':null},{'id':36,'cell_color':'black','piece':null},{'id':37,'cell_color':'white','piece':null},{'id':38,'cell_color':'black','piece':null},{'id':39,'cell_color':'white','piece':null},{'id':40,'cell_color':'black','piece':null},{'id':41,'cell_color':'black','piece':null},{'id':42,'cell_color':'white','piece':null},{'id':43,'cell_color':'black','piece':null},{'id':44,'cell_color':'white','piece':null},{'id':45,'cell_color':'black','piece':null},{'id':46,'cell_color':'white','piece':null},{'id':47,'cell_color':'black','piece':null},{'id':48,'cell_color':'white','piece':null},{'id':49,'cell_color':'white','piece':{'type':'pawn','color':'black'}},{'id':50,'cell_color':'black','piece':{'type':'pawn','color':'black'}},{'id':51,'cell_color':'white','piece':{'type':'pawn','color':'black'}},{'id':52,'cell_color':'black','piece':{'type':'pawn','color':'black'}},{'id':53,'cell_color':'white','piece':{'type':'pawn','color':'black'}},{'id':54,'cell_color':'black','piece':{'type':'pawn','color':'black'}},{'id':55,'cell_color':'white','piece':{'type':'pawn','color':'black'}},{'id':56,'cell_color':'black','piece':{'type':'pawn','color':'black'}},{'id':57,'cell_color':'black','piece':{'type':'rook','color':'black'}},{'id':58,'cell_color':'white','piece':{'type':'knight','color':'black'}},{'id':59,'cell_color':'black','piece':{'type':'bishop','color':'black'}},{'id':60,'cell_color':'white','piece':{'type':'king','color':'black'}},{'id':61,'cell_color':'black','piece':{'type':'queen','color':'black'}},{'id':62,'cell_color':'white','piece':{'type':'bishop','color':'black'}},{'id':63,'cell_color':'black','piece':{'type':'knight','color':'black'}},{'id':64,'cell_color':'white','piece':{'type':'rook','color':'black'}}}";


    @RequestMapping(value = "")
    public String getMockBoard() {
        return board;
    }
}