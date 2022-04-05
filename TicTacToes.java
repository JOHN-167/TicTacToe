/**
 * Author: Quoc Duy Nguyen
 * NetID: qnguy12
 * Assignment: Project 3
 */

import java.awt.Graphics; import java.awt.Font; import java.awt.Dimension;
import java.awt.BorderLayout; import java.awt.GridLayout;
import java.awt.event.ActionListener; import java.awt.event.ActionEvent;
import javax.swing.JFrame; import javax.swing.JPanel; import javax.swing.Timer;
import javax.swing.Box; import javax.swing.border.EmptyBorder;
import javax.swing.JLabel; import javax.swing.JButton; import javax.swing.BoxLayout;

public class TicTacToes extends JFrame {
    int[] scores = new int[3]; //P1 wins, P2 wins, draws
    JLabel[] scoreBoardLabel = new JLabel[3]; //P1 wins, P2 wins, draws
    JLabel winnerLabel = new JLabel("In Progress");
    String nextMove = "X";
    int filled = 0;
    boolean inProgress = true;
    TicTacToes() {
        setMinimumSize(new Dimension(480,580));
        setSize(480,580);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFocusable(true);
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        JPanel buttonCanvas = new JPanel(){
            @Override public void paintComponent(Graphics g) {
                g.drawRect(-1, -1, getWidth()+1, getHeight()-1);
            }
        };
        JButton newGameButton = new JButton("New Game");
        newGameButton.setOpaque(false);
        newGameButton.setContentAreaFilled(false);
        newGameButton.setBorderPainted(false);
        JPanel newGamePanel = new JPanel(){
            @Override public void paintComponent(Graphics g) {
                g.drawRoundRect(10, 10, getWidth()-20, getHeight()-20, 10, 10);
            }
        };
        newGamePanel.add(newGameButton);
        newGameButton.setPreferredSize(new Dimension(getWidth()/3,(int)(getHeight()/6.5)));
        JPanel winnerPanel = new JPanel(){
            @Override public void paintComponent(Graphics g) {
                g.drawRoundRect(10, 10, getWidth()-20, getHeight()-20, 10, 10);
            }
        };
        winnerPanel.setLayout(new GridLayout(3,1));
        winnerPanel.add(new JLabel(" "));
        winnerPanel.add(winnerLabel);
        winnerLabel.setHorizontalAlignment(JLabel.CENTER);
        winnerPanel.add(new JLabel(" "));
        JPanel scoreBoardPanel = new JPanel(){
            @Override public void paintComponent(Graphics g) {
                g.drawRoundRect(10, 10, getWidth()-20, getHeight()-20, 10, 10);
            }
        };
        scoreBoardPanel.setLayout(new GridLayout(5,1));
        scoreBoardLabel[0] = new JLabel("P1 wins: " + scores[0]);
        scoreBoardLabel[1] = new JLabel("P2 wins: " + scores[1]);
        scoreBoardLabel[2] = new JLabel("Draws: " + scores[2]);
        for (JLabel score : scoreBoardLabel)
            score.setHorizontalAlignment(JLabel.CENTER);
        scoreBoardPanel.add(new JLabel(" "));
        for (JLabel label : scoreBoardLabel)
            scoreBoardPanel.add(label);
        scoreBoardPanel.add(new JLabel(" "));
        GameCanvas gameCanvas = new GameCanvas();
        newGameButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                gameCanvas.reset();
                winnerLabel.setText("In Progress");
                filled = 0;
                nextMove = "X";
                inProgress = true;
            }
        });
        buttonCanvas.setLayout(new GridLayout(1,3));
        buttonCanvas.add(newGamePanel);
        buttonCanvas.add(winnerPanel);
        buttonCanvas.add(scoreBoardPanel);
        buttonCanvas.setPreferredSize(new Dimension(getWidth(),100));
        JPanel gamePanel = new JPanel();
        gamePanel.setLayout(new BorderLayout());
        gamePanel.add(Box.createRigidArea(new Dimension(480,90)),BorderLayout.NORTH);
        gamePanel.add(Box.createRigidArea(new Dimension(90,100)),BorderLayout.EAST);
        gamePanel.add(Box.createRigidArea(new Dimension(90,100)),BorderLayout.WEST);
        gamePanel.add(Box.createRigidArea(new Dimension(480,90)),BorderLayout.SOUTH);
        gamePanel.add(gameCanvas,BorderLayout.CENTER);
        panel.add(buttonCanvas);
        panel.add(gamePanel);
        add(panel);
    }
    public static void main(String[] args){
        TicTacToes game = new TicTacToes();
        game.setVisible(true);
    }
    private class GameCanvas extends JPanel {
        JButton[] labels = new JButton[9];
        GameCanvas(){
            setLayout(new GridLayout(3,3));
            for (int i = 0; i < 9; i++){
                labels[i] = new JButton(""){
                    @Override public void paintComponent(Graphics g) {
                        g.setFont(new Font("TimesRoman", Font.PLAIN, (int)(12.0/480.0*TicTacToes.this.getWidth())));
                        g.drawString(getText(),getWidth()/2-(int)(5.0/480.0*TicTacToes.this.getWidth()),
                        getHeight()/2+(int)(5.0/480.0*TicTacToes.this.getWidth()));
                    }
                };
                /* Timer
                Timer timer = new Timer(1000,new ActionListener() {
                    @Override public void actionPerformed(ActionEvent e) {
                        GameCanvas.this.reset();
                        winnerLabel.setText("In Progress");
                        filled = 0;
                        ((Timer)e.getSource()).stop();
                    }
                }); */
                labels[i].addActionListener(new ActionListener() {
                    @Override public void actionPerformed(ActionEvent e){
                        JButton current = (JButton)e.getSource();
                        if (current.getText().isEmpty()&&inProgress == true) {
                            current.setText(nextMove);
                            filled++;
                            if (checkWin(nextMove)){
                                if (nextMove.equals("X")) {
                                    scores[0]++;
                                    winnerLabel.setText("P1 wins");
                                }
                                else {
                                    scores[1]++;
                                    winnerLabel.setText("P2 wins");
                                }
                                inProgress = false;
                                // timer.start();
                            }
                            else {
                                if (filled == 9) {
                                    filled = 0;
                                    scores[2]++;
                                    winnerLabel.setText("Draws");
                                    inProgress = false;
                                    // timer.start();
                                }
                            }
                            scoreBoardLabel[0].setText("P1 wins: " + scores[0]);
                            scoreBoardLabel[1].setText("P2 wins: " + scores[1]);
                            scoreBoardLabel[2].setText("Draws: " + scores[2]);
                            nextMove = (nextMove.equals("X"))? "O" : "X";
                        }
                    }
                });
                labels[i].setHorizontalAlignment(JLabel.CENTER);
                add(labels[i]);
            }
        }
        @Override public void paintComponent(Graphics g) {
            int width = getWidth();
            int height = getHeight();
            g.drawLine(0,(int)(((double)height)/3),width,
            (int)(((double)height)/3));
            g.drawLine(0,(int)(((double)height)/3*2),width,
            (int)(((double)height)/3*2));
            g.drawLine((int)(((double)width)/3),0,
            (int)(((double)width)/3),height);
            g.drawLine((int)(((double)width)/3*2),0,
            (int)(((double)width)/3*2),height);
        }
        public void reset() {
            for (JButton label : labels)
                label.setText("");
        }
        public boolean checkWin(String n) {
            for (int i = 0; i < 3; i++) {
                if (labels[i].getText().equals(n) && 
                    labels[i+3].getText().equals(n) && labels[i+6].getText().equals(n)) {
                    return true;
                }
                if (labels[i*3].getText().equals(n) && 
                    labels[i*3+1].getText().equals(n) && labels[i*3+2].getText().equals(n)) {
                    return true;
                }
            }
            if (labels[0].getText().equals(n) && 
                labels[4].getText().equals(n) && labels[8].getText().equals(n)) {
                return true;
            }
            if (labels[2].getText().equals(n) && 
                labels[4].getText().equals(n) && labels[6].getText().equals(n)) {
                return true;
            }
            return false;
        }
    }
}