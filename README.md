# Tic-Tac-Toe Game in Java

## Project Overview
Welcome to the **Tic-Tac-Toe Game**, implemented in Java! This project is a simple implementation of the classic two-player game where a player competes against the computer in a 3x3 grid. The goal is to get three of your symbols ("X" or "O") in a row, either horizontally, vertically, or diagonally.

## Features
- **Player vs Computer**: The player competes as "X", and the computer plays as "O".
- **Turn-based Gameplay**: The game alternates between the player and the computer until there is a winner or all cells are filled.
- **Clear Instructions**: Instructions are provided at the beginning to help players understand the gameplay.
- **Interactive Input**: Players are prompted to enter cell coordinates to place their symbol.
- **Game End Conditions**: The game detects when a player wins, the game ends in a draw, or the game can still continue.

## How to Play
1. The game starts with the board, which is a 3x3 grid.
2. Players are prompted to enter coordinates (row and column) to place their symbol ("X").
3. After each player move, the computer automatically takes its turn.
4. The game continues until either the player or the computer wins, or all cells are filled (draw).
5. The board updates after every turn, and messages are displayed for wins, draws, or game continuation.

## Game Flow
- **Input Coordinates**: Enter the coordinates (row and column) from `0-2` separated by a space to place "X".
- **Win Condition**: The first player to place three of their symbols in a row, either vertically, horizontally, or diagonally, wins.
- **Draw**: If all cells are filled without a winner, the game ends in a draw.

## Instructions for Running the Code
1. Clone the repository:
   ```bash
   git clone https://github.com/username/tic-tac-toe-java.git

