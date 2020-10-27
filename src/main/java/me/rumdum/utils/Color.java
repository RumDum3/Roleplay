package me.rumdum.utils;

public class Color {

    public static java.awt.Color getColor(String value) {
        switch (value) {
            case "BLUE":
                return java.awt.Color.BLUE;
            case "RED":
                return java.awt.Color.RED;
            case "YELLOW":
                return java.awt.Color.YELLOW;
            case "PINK":
                return java.awt.Color.PINK;
            case "PURPLE":
                return java.awt.Color.magenta;
            case "BLACK":
                return java.awt.Color.BLACK;
            case "GREEN":
                return java.awt.Color.GREEN;
            case "ORANGE":
                return java.awt.Color.ORANGE;
            case "WHITE":
                return java.awt.Color.WHITE;
        }
        return null;
    }

}
