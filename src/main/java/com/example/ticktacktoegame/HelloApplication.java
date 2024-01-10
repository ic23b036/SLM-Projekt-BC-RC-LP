package com.example.ticktacktoegame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class HelloApplication extends Application {
    private char[][] board = {
            {' ', ' ', ' '},
            {' ', ' ', ' '},
            {' ', ' ', ' '}
    };
    private char currentPlayer = 'X';

    public static void main(String[] args) {
        System.out.println("Spieler 1 beginnt mit dem Symbol X");
        System.out.println("Spieler 2 hat das Symbol o");
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Tic Tac Toe - Spiel");

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Button button = createButton(i, j);
                grid.add(button, j, i);
            }
        }

        Scene scene = new Scene(grid, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createButton(int row, int col) {
        Button button = new Button();
        button.setMinSize(150, 150);
        button.setOnAction(e -> handleButtonClick(button, row, col));
        return button;
    }

    private void handleButtonClick(Button button, int row, int col) {
        if (board[row][col] == ' ' && !gameOver()) {
            board[row][col] = currentPlayer;
            button.setText(String.valueOf(currentPlayer));
            if (checkWin(row, col)) {
                System.out.println("Spieler " + currentPlayer + " gewinnt!");
            } else if (boardFull()) {
                System.out.println("Unentschieden! Kein Spieler hat gewonnen!");
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }

    private boolean checkWin(int row, int col) {
        return (board[row][0] == currentPlayer && board[row][1] == currentPlayer && board[row][2] == currentPlayer) ||
                (board[0][col] == currentPlayer && board[1][col] == currentPlayer && board[2][col] == currentPlayer) ||
                (row == col && board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) ||
                (row + col == 2 && board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer);
    }

    private boolean boardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean gameOver() {
        return checkWin(0, 0) || checkWin(1, 1) || checkWin(2, 2) ||
                checkWin(0, 2) || checkWin(2, 0) || checkWin(0, 1) ||
                checkWin(1, 0) || checkWin(1, 2) || checkWin(2, 1);
    }
}
