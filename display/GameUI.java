package display;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GameUI extends JFrame implements ActionListener {
	ArrayList<BoxButton> tempList = new ArrayList<BoxButton>();
	
	public GameUI() {
		super();
		this.setVisible(true);
		this.setTitle("Memory Test Game");
		this.setSize(700, 700);
		this.setResizable(false);
		this.setLocation(500, 100);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		this.setLayout(new BorderLayout());
		
		Button start = new Button("Start Game");
				
		this.add(start, BorderLayout.NORTH);
		
		JPanel boxMatrix = new JPanel();
		boxMatrix.setSize(500, 500);
		
		this.add(boxMatrix, BorderLayout.CENTER);
		boxMatrix.setLayout(new GridLayout(5,5));
		
		
		
		BoxButton temp;
		for(int i=0;i<5;i++)
			for(int j=0;j<5;j++) {				
				temp = new BoxButton("",i,j);
				temp.addActionListener(this);
				
				boxMatrix.add(temp);
			}
	}
	
	public static void main(String[] args) {
		GameUI test = new GameUI();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if(arg0.getSource() instanceof BoxButton) {
			BoxButton source = (BoxButton)arg0.getSource();
			Boolean isExisted = false;
			
			for(BoxButton element : tempList) {
				if(source.equals(element)) {
					isExisted = true;
					break;
				}
			}
			if(isExisted == false) {
				tempList.add(source);
				source.setBackground(Color.BLUE);
				System.out.println("Location: "+source.getX()+"-"+source.getY());
			}
			
			if(tempList.size() == 5) {
				JOptionPane.showMessageDialog(this, "Put the result here", "Result", JOptionPane.INFORMATION_MESSAGE);
				for(BoxButton element : tempList) {
					element.setBackground(new BoxButton().getBackground());
				}
				tempList.clear();
			}
		}		
			
	}
	
	
	
}
