/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

/**
 *
 * @author 姜宜辰
 */
public class NoContentException extends Exception{
    public NoContentException(){
        super("No content inputed.");
    }
}
