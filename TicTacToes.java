import java.awt.Graphics; import java.awt.Color; import java.awt.Component;
import java.awt.Font; import java.awt.Dimension;
import java.awt.BorderLayout; import java.awt.GridLayout;
import java.awt.event.MouseListener; import java.awt.event.MouseEvent;
import java.awt.event.ActionListener; import java.awt.event.ActionEvent;
import javax.swing.JFrame; import javax.swing.JPanel; import javax.swing.Box;
import javax.swing.JLabel; import javax.swing.JButton;

public class TicTacToes extends JFrame {
    int[] scores = new int[3]; //P1 wins, P2 wins, draws
    JLabel[] scoreBoardLabel = new JLabel[3]; //P1 wins, P2 wins, draws
    TicTacToes() {
        setSize(480,480);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setFocusable(true);
        setLayout(new BorderLayout());
        JPanel buttonCanvas = new JPanel();
        JButton newGameButton = new JButton("New Game");
        JLabel winnerLabel = new JLabel("In Progress");
        winnerLabel.setHorizontalAlignment(JLabel.CENTER);;
        JPanel scoreBoardPanel = new JPanel();
        scoreBoardPanel.setLayout(new GridLayout(3,1));
        scoreBoardLabel[0] = new JLabel("P1 wins: " + scores[0]);
        scoreBoardLabel[1] = new JLabel("P2 wins: " + scores[1]);
        scoreBoardLabel[2] = new JLabel("Draws: " + scores[2]);
        for (JLabel score : scoreBoardLabel)
            score.setHorizontalAlignment(JLabel.CENTER);
        for (JLabel label : scoreBoardLabel)
            scoreBoardPanel.add(label);
        GameCanvas gameCanvas = new GameCanvas();
        newGameButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                gameCanvas.reset();
            }
        });
        buttonCanvas.setLayout(new GridLayout(2,3));
        buttonCanvas.add(newGameButton);
        buttonCanvas.add(winnerLabel);
        buttonCanvas.add(scoreBoardPanel);
        buttonCanvas.add(Box.createRigidArea(new Dimension(getWidth()/3,60)));
        buttonCanvas.add(Box.createRigidArea(new Dimension(getWidth()/3,60)));
        buttonCanvas.add(Box.createRigidArea(new Dimension(getWidth()/3,60)));
        add(Box.createRigidArea(new Dimension(90,420)),BorderLayout.EAST);
        add(Box.createRigidArea(new Dimension(90,420)),BorderLayout.WEST);
        add(Box.createRigidArea(new Dimension(480,60)),BorderLayout.SOUTH);
        add(buttonCanvas,BorderLayout.NORTH);
        add(gameCanvas,BorderLayout.CENTER);
    }
    public static void main(String[] args){
        TicTacToes game = new TicTacToes();
        game.setVisible(true);
    }
    private class GameCanvas extends JPanel {
        JLabel[] labels = new JLabel[9];
        GameCanvas(){
            setLayout(new GridLayout(3,3));
            for (int i = 0; i < 9; i++){
                labels[i] = new JLabel("label");
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
            for (JLabel label : labels){
                label.setText("X");
            }
            System.out.println(labels[0]== null);
        }
        public void setLabel(int index, String text) {
            labels[index].setText(text);
        }
    }
}

