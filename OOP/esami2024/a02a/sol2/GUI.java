package a02a.sol2;

import javax.swing.*;
import java.util.*;
import java.util.List;
import java.awt.*;
import java.awt.event.ActionListener;

public class GUI extends JFrame {
    
    private final Map<JButton, Position> cells = new HashMap<>();
    private List<Position> path;
    private final Logic logic;
    private int advancing = 3;
    
    public GUI(int size) {
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(70*size, 70*size);
        this.logic = new LogicImpl(size);
        this.path = this.logic.path();

        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(panel);
        
        ActionListener al = e -> {
            this.logic.advance(advancing);
            advancing = 7 - advancing;
            updateMarks();
            if (this.logic.isOver()){
                System.exit(0);
            }            
        };

        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
            	final JButton jb = new JButton();
                var pos = new Position(j,i);
                if (!this.path.contains(pos)){
                    jb.setEnabled(false);
                }
                this.cells.put(jb, pos);
                jb.addActionListener(al);
                panel.add(jb);
            }
        }
        this.updateMarks();
        this.setVisible(true);
    }

    private void updateMarks(){
        for (var e: this.cells.entrySet()){
            if (this.path.contains(e.getValue())){
                e.getKey().setText(this.logic.mark().equals(e.getValue())?"*":" ");
            }
            if (this.logic.isPitfall(e.getValue())){
                e.getKey().setText("o");
            }
        }
    }
}
