import java.awt.Graphics; import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.MouseListener; import java.awt.event.MouseEvent;
import javax.swing.JFrame; import javax.swing.JPanel; 
import javax.swing.JLabel; import javax.swing.JButton;
import java.awt.event.ActionListener; import java.awt.event.ActionEvent;
import java.awt.BorderLayout; import javax.swing.BoxLayout;
import java.awt.GridLayout;

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
        JPanel scoreBoardPanel = new JPanel();
        scoreBoardPanel.setLayout(new GridLayout(3,1));
        scoreBoardLabel[0] = new JLabel("P1 wins: " + scores[0]);
        scoreBoardLabel[1] = new JLabel("P2 wins: " + scores[1]);
        scoreBoardLabel[2] = new JLabel("Draws: " + scores[2]);
        for (JLabel label : scoreBoardLabel)
            scoreBoardPanel.add(label);
        GameCanvas gameCanvas = new GameCanvas();
        newGameButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                gameCanvas.reset();
                scoreBoardPanel.setText("P1 wins: " + scores[0] +
                    "\nP2 wins: " + scores[1] + "\nDraws: " + scores[2]);
            }
        });
        buttonCanvas.setLayout(new GridLayout(1,3));
        buttonCanvas.add(newGameButton);
        buttonCanvas.add(winnerLabel);
        buttonCanvas.add(scoreBoardPanel);
        // add(fillerV,BorderLayout.EAST);
        // add(fillerV,BorderLayout.WEST);
        // add(fillerH,BorderLayout.SOUTH);
        add(buttonCanvas,BorderLayout.NORTH);
        add(gameCanvas,BorderLayout.CENTER);
    }
    public static void main(String[] args){
        TicTacToes game = new TicTacToes();
        game.setVisible(true);
    }
    private class GameCanvas extends JPanel {
        protected JLabel[] labels = new JLabel[9];
        GameCanvas(){
            setBackground(Color.PINK);
            setLayout(new GridLayout(3,3));
            for (JLabel label : labels){
                label = new JLabel("label");
                label.setAlignmentX(Component.CENTER_ALIGNMENT);;
                add(label);
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
            for (JLabel label : labels)
                label.setText("");
        }
        public void setLabel(int index, String text) {
            labels[index].setText(text);
        }
    }
}

