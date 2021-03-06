package display;

//updated
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import data.Box;
import data.BoxQueue;
import data.Driver;

@SuppressWarnings("serial")
public class GameUI extends JFrame implements ActionListener {

	// Hold the grid of box buttons
	private BoxButton[][] boxes = new BoxButton[5][5];

	private Button start = new Button("Start Game");

	// Manipulate the sequence of box Object
	private Driver driver = new Driver();

	// Hold the sequence of box objects selected by player
	private BoxQueue inputQueue = new BoxQueue();

	// Hold the sequence of BoxButton objects selected by player
	ArrayList<BoxButton> inputList = new ArrayList<BoxButton>();

	JLabel levelGUI = new JLabel("Level: " + driver.difficulty.getLevel());

	JPanel control = new JPanel();

	JPanel boxMatrix = new JPanel();

	public GameUI() {
		super();
		this.setVisible(true);
		this.setTitle("Memory Test Game");
		this.setSize(700, 700);
		this.setResizable(false);
		this.setLocation(500, 100);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		addComponents();
		editPanels();
	}

	
	
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		GameUI test = new GameUI();
	}
	
	
	
	
	
	public void setLevelDisplay(int level) {
		boxMatrix.setBorder(new TitledBorder(null, "Level: " + level, TitledBorder.LEADING, TitledBorder.BELOW_TOP,
				null, Color.WHITE));
	}
	
	public void addComponents() {
		control.setLayout(new FlowLayout());
		start.addActionListener(this);
		control.add(start);
		this.add(control, BorderLayout.NORTH);

		boxMatrix.setLayout(new GridLayout(5, 5));
		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 5; j++) {
				boxes[i][j] = new BoxButton("", i, j);
				boxes[i][j].addActionListener(this);
				boxes[i][j].setEnabled(false);
				boxes[i][j].setBackground(Color.BLACK);
				boxMatrix.add(boxes[i][j]);
			}
		this.add(boxMatrix, BorderLayout.CENTER);

		control.add(start);
		this.add(control, BorderLayout.NORTH);
		this.add(boxMatrix, BorderLayout.CENTER);
	}

	public void editPanels() {
		start.setPreferredSize(new Dimension(200, 40));
		start.setFont(new Font(null, Font.PLAIN, 20));
		start.setBackground(new Color(51, 51, 51));
		start.setForeground(Color.WHITE);

		control.setBackground(new Color(105, 105, 105));

		setLevelDisplay(driver.difficulty.getLevel());
		boxMatrix.setBackground(new Color(51, 51, 51));
	}
	
	public void displayPattern() {
		BoxQueue tempQueue;

		tempQueue = driver.generateCollection();
		ArrayList<BoxButton> tempBoxes = new ArrayList<>();

		for (Box temp : tempQueue.getArray()) {
			tempBoxes.add(boxes[temp.getX()][temp.getY()]);
		}

		for (BoxButton button : tempBoxes) {
			button.setBackground(Color.BLUE);

			Thread.currentThread();
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		for (BoxButton button : tempBoxes) {
			button.setBackground(Color.BLACK);
		}

		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 5; j++)
				boxes[i][j].setEnabled(true);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {

		// When the "Start Game" button is clicked
		// The program display the sequence of boxes
		if (arg0.getSource().equals(start)) {
			displayPattern();
			start.setEnabled(false);
		}
		// Receive the input of player and return the result
		else if (arg0.getSource() instanceof BoxButton) {
			BoxButton source = (BoxButton) arg0.getSource();
			Boolean isExisted = false;

			for (BoxButton element : inputList) {
				if (source.equals(element)) {
					isExisted = true;
					element.setBackground(Color.BLACK);
					break;
				}
			}
			if (isExisted == false) {
				inputList.add(source);
				inputQueue.add(source.getX(), source.getY());
				source.setBackground(Color.BLUE);
			}

			if (inputList.size() == driver.difficulty.getNumberOfBoxes()) {
				String result;
				Object[] options = new Object[2];

				try {
					if (driver.isMaxLevel() && driver.compare(inputQueue)) {
						result = "CORRECT ANSWER. YOU WON";
						driver.difficulty.setLevel(1);
						setLevelDisplay(driver.difficulty.getLevel());
						// Set options
						options[0] = "Retry";
						options[1] = "Quit";
					} else if (driver.compare(inputQueue)) {
						result = "CORRECT ANSWER";
						// Increases level
						driver.difficulty.increaseByOne();
						setLevelDisplay(driver.difficulty.getLevel());
						// Set options
						options[0] = "Continue";
						options[1] = "Quit";
					} else {
						result = "WRONG ANSWER";
						// Resets game back to level 1
						driver.difficulty.setLevel(1);
						setLevelDisplay(driver.difficulty.getLevel());
						// Set options
						options[0] = "Retry";
						options[1] = "Quit";
					}
					int input = JOptionPane.showOptionDialog(this, result, "Result", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

					// Updates the JLabel to display the current level
					levelGUI.setText("Level: " + driver.difficulty.getLevel());

					for (BoxButton element : inputList)
						element.setBackground(Color.BLACK);

					inputList.clear();
					inputQueue.clear();

					for (int i = 0; i < 5; i++)
						for (int j = 0; j < 5; j++)
							boxes[i][j].setEnabled(false);

					if (input == JOptionPane.YES_OPTION) {
						if (driver.difficulty.getLevel() == 1) {
							start.setEnabled(true);
						} else
							displayPattern();
					} else {
						this.dispose();
					}
				} catch (CloneNotSupportedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}

	}
}