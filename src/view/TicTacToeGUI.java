

//                     package view;

// import controller.GameController;
// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.*;

// public class TicTacToeGUI extends JFrame implements ActionListener {

//     private JButton[][] buttons = new JButton[3][3];
//     private GameController controller;
//     private JLabel statusLabel;
//     private boolean vsAI;

//     private static final Color X_COLOR = new Color(30, 100, 200);
//     private static final Color O_COLOR = new Color(200, 50, 50);

//     public TicTacToeGUI() {
//         int choice = JOptionPane.showOptionDialog(
//             null,
//             "Select Game Mode",
//             "Tic Tac Toe",
//             JOptionPane.DEFAULT_OPTION,
//             JOptionPane.QUESTION_MESSAGE,
//             null,
//             new String[]{"vs AI", "2 Players"},
//             "vs AI"
//         );
//         vsAI = (choice == 0);
//         controller = new GameController(vsAI);

//         setTitle("Tic Tac Toe");
//         setSize(400, 450);
//         setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         setLayout(new BorderLayout());

//         statusLabel = new JLabel("Player 1 (X) Turn", JLabel.CENTER);
//         statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
//         statusLabel.setForeground(X_COLOR);
//         add(statusLabel, BorderLayout.NORTH);

//         JPanel boardPanel = new JPanel();
//         boardPanel.setLayout(new GridLayout(3, 3));
//         boardPanel.setBackground(Color.DARK_GRAY);
//         boardPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 5));

//         for (int i = 0; i < 3; i++) {
//             for (int j = 0; j < 3; j++) {
//                 buttons[i][j] = new JButton("");
//                 buttons[i][j].setFont(new Font("Arial", Font.BOLD, 60));
//                 buttons[i][j].setBackground(Color.WHITE);
//                 buttons[i][j].setFocusPainted(false);
//                 buttons[i][j].addActionListener(this);
//                 boardPanel.add(buttons[i][j]);
//             }
//         }

// // for (int i = 0; i < 3; i++) {
// //     for (int j = 0; j < 3; j++) {
// //         buttons[i][j] = new JButton("");
// //         buttons[i][j].setFont(new Font("Arial", Font.BOLD, 60));
// //         buttons[i][j].setBackground(Color.WHITE);
// //         buttons[i][j].setFocusPainted(false);
// //         buttons[i][j].setOpaque(true);
// //         buttons[i][j].setBorderPainted(false);
// //         buttons[i][j].addActionListener(this);
// //         boardPanel.add(buttons[i][j]);
// //     }
// // }

//         add(boardPanel, BorderLayout.CENTER);

//         JButton resetButton = new JButton("Reset");
//         resetButton.setFont(new Font("Arial", Font.BOLD, 16));
//         resetButton.setBackground(new Color(50, 150, 50));
//         resetButton.setForeground(Color.WHITE);
//         resetButton.setFocusPainted(false);
//         resetButton.addActionListener(e -> resetGame());
//         add(resetButton, BorderLayout.SOUTH);

//         setVisible(true);
//     }

//     @Override
//     public void actionPerformed(ActionEvent e) {
//         if (controller.isGameOver()) return;

//         JButton clicked = (JButton) e.getSource();

//         for (int i = 0; i < 3; i++) {
//             for (int j = 0; j < 3; j++) {
//                 if (buttons[i][j] == clicked && buttons[i][j].getText().equals("")) {

//                     // current player move
//                     char mark = controller.getCurrentPlayer().getMark();
//                     controller.makeMove(i, j);
//                     buttons[i][j].setText(String.valueOf(mark));
//                     buttons[i][j].setForeground(mark == 'X' ? X_COLOR : O_COLOR);

//                     // win check
//                     if (controller.checkWin()) {
//                         statusLabel.setText(controller.getCurrentPlayer().getName() + " Wins!");
//                         statusLabel.setForeground(mark == 'X' ? X_COLOR : O_COLOR);
//                         controller.setGameOver(true);
//                         disableAllButtons();
//                         return;
//                     }

//                     // draw check
//                     if (controller.checkDraw()) {
//                         statusLabel.setText("Draw!");
//                         statusLabel.setForeground(Color.GRAY);
//                         controller.setGameOver(true);
//                         disableAllButtons();
//                         return;
//                     }

//                     controller.switchPlayer();

//                     if (vsAI) {
//                         // AI delay 700ms
//                         disableAllButtons();
//                         statusLabel.setText("Computer thinking...");
//                         statusLabel.setForeground(Color.GRAY);

//                         Timer timer = new Timer(700, ae -> {
//                             int[] aiMove = controller.getAIMove();
//                             if (aiMove != null) {
//                                 controller.makeMove(aiMove[0], aiMove[1]);
//                                 buttons[aiMove[0]][aiMove[1]].setText("O");
//                                 buttons[aiMove[0]][aiMove[1]].setForeground(O_COLOR);

//                                 if (controller.checkWin()) {
//                                     statusLabel.setText("Computer Wins!");
                                   
//                                     statusLabel.setForeground(O_COLOR);
//                                     controller.setGameOver(true);
//                                     return;
//                                 }

//                                 if (controller.checkDraw()) {
//                                     statusLabel.setText("Draw!");
//                                     statusLabel.setForeground(Color.GRAY);
//                                     controller.setGameOver(true);
//                                     return;
//                                 }

//                                 controller.switchPlayer();
//                                 statusLabel.setText("Player 1 (X) Turn");
//                                 statusLabel.setForeground(X_COLOR);
//                                 enableAllButtons();
//                             }
//                         });
//                         timer.setRepeats(false);
//                         timer.start();

//                     } else {
//                         // 2 player — next turn
//                         char nextMark = controller.getCurrentPlayer().getMark();
//                         String nextName = controller.getCurrentPlayer().getName();
//                         statusLabel.setText(nextName + " (" + nextMark + ") Turn");
//                         statusLabel.setForeground(nextMark == 'X' ? X_COLOR : O_COLOR);
//                     }
//                     return;
//                 }
//             }
//         }
//     }

//     private void disableAllButtons() {
//         for (int i = 0; i < 3; i++)
//             for (int j = 0; j < 3; j++)
//                 buttons[i][j].setEnabled(false);
//     }

//     private void enableAllButtons() {
//         for (int i = 0; i < 3; i++)
//             for (int j = 0; j < 3; j++)
//                 if (buttons[i][j].getText().equals(""))
//                     buttons[i][j].setEnabled(true);
//     }

//     private void resetGame() {
//         controller.resetGame();
//         for (int i = 0; i < 3; i++)
//             for (int j = 0; j < 3; j++) {
//                 buttons[i][j].setText("");
//                 buttons[i][j].setBackground(Color.WHITE);
//                 buttons[i][j].setEnabled(true);
//             }
//         statusLabel.setText("Player 1 (X) Turn");
//         statusLabel.setForeground(X_COLOR);
//     }

//     public static void main(String[] args) {
//         new TicTacToeGUI();
//     }
// }





package view;

import controller.GameController;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TicTacToeGUI extends JFrame implements ActionListener {

    private JButton[][] buttons = new JButton[3][3];
    private GameController controller;
    private JLabel statusLabel;
    private boolean vsAI;

    private static final Color X_COLOR = new Color(30, 100, 200);
    private static final Color O_COLOR = new Color(200, 50, 50);

    public TicTacToeGUI() {
        int choice = JOptionPane.showOptionDialog(
            null,
            "Select Game Mode",
            "Tic Tac Toe",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            new String[]{"vs AI", "2 Players"},
            "vs AI"
        );
        vsAI = (choice == 0);
        controller = new GameController(vsAI);

        setTitle("Tic Tac Toe");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        statusLabel = new JLabel(vsAI ? "Your Turn (X)" : "Player 1 (X) Turn", JLabel.CENTER);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 16));
        statusLabel.setForeground(X_COLOR);
        add(statusLabel, BorderLayout.NORTH);

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3));
        boardPanel.setBackground(Color.DARK_GRAY);
        boardPanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 5));

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton("");
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 60));
                buttons[i][j].setBackground(Color.WHITE);
                buttons[i][j].setFocusPainted(false);
                buttons[i][j].setOpaque(true);
                buttons[i][j].addActionListener(this);
                boardPanel.add(buttons[i][j]);
            }
        }
        add(boardPanel, BorderLayout.CENTER);

        JButton resetButton = new JButton("Reset");
        resetButton.setFont(new Font("Arial", Font.BOLD, 16));
        resetButton.setBackground(new Color(50, 150, 50));
        resetButton.setForeground(Color.WHITE);
        resetButton.setFocusPainted(false);
        resetButton.setOpaque(true);
        resetButton.addActionListener(e -> resetGame());
        add(resetButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (controller.isGameOver()) return;

        JButton clicked = (JButton) e.getSource();

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j] == clicked && buttons[i][j].getText().equals("")) {

                    char mark = controller.getCurrentPlayer().getMark();
                    controller.makeMove(i, j);
                    buttons[i][j].setText(String.valueOf(mark));
                    buttons[i][j].setForeground(mark == 'X' ? X_COLOR : O_COLOR);
                    buttons[i][j].setBackground(mark == 'X' ? new Color(230, 240, 255) : new Color(255, 230, 230));

                    if (controller.checkWin()) {
                        statusLabel.setText(vsAI ? "You Win!" : controller.getCurrentPlayer().getName() + " Wins!");
                        statusLabel.setForeground(mark == 'X' ? X_COLOR : O_COLOR);
                        controller.setGameOver(true);
                        disableAllButtons();
                        return;
                    }

                    if (controller.checkDraw()) {
                        statusLabel.setText("Draw!");
                        statusLabel.setForeground(Color.GRAY);
                        controller.setGameOver(true);
                        disableAllButtons();
                        return;
                    }

                    controller.switchPlayer();

                    if (vsAI) {
                        disableAllButtons();
                        statusLabel.setText("Computer thinking...");
                        statusLabel.setForeground(Color.GRAY);

                        Timer timer = new Timer(700, ae -> {
                            int[] aiMove = controller.getAIMove();
                            if (aiMove != null) {
                                controller.makeMove(aiMove[0], aiMove[1]);
                                buttons[aiMove[0]][aiMove[1]].setText("O");
                                buttons[aiMove[0]][aiMove[1]].setForeground(O_COLOR);
                                buttons[aiMove[0]][aiMove[1]].setBackground(new Color(255, 230, 230));

                                if (controller.checkWin()) {
                                    statusLabel.setText("You Lose!");
                                    statusLabel.setForeground(O_COLOR);
                                    controller.setGameOver(true);
                                    return;
                                }

                                if (controller.checkDraw()) {
                                    statusLabel.setText("Draw!");
                                    statusLabel.setForeground(Color.GRAY);
                                    controller.setGameOver(true);
                                    return;
                                }

                                controller.switchPlayer();
                                statusLabel.setText("Your Turn (X)");
                                statusLabel.setForeground(X_COLOR);
                                enableAllButtons();
                            }
                        });
                        timer.setRepeats(false);
                        timer.start();

                    } else {
                        char nextMark = controller.getCurrentPlayer().getMark();
                        String nextName = controller.getCurrentPlayer().getName();
                        statusLabel.setText(nextName + " (" + nextMark + ") Turn");
                        statusLabel.setForeground(nextMark == 'X' ? X_COLOR : O_COLOR);
                    }
                    return;
                }
            }
        }
    }

    private void disableAllButtons() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                buttons[i][j].setEnabled(false);
    }

    private void enableAllButtons() {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (buttons[i][j].getText().equals(""))
                    buttons[i][j].setEnabled(true);
    }

    private void resetGame() {
        controller.resetGame();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                buttons[i][j].setBackground(Color.WHITE);
                buttons[i][j].setEnabled(true);
            }
        statusLabel.setText(vsAI ? "Your Turn (X)" : "Player 1 (X) Turn");
        statusLabel.setForeground(X_COLOR);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        new TicTacToeGUI();
    }
}