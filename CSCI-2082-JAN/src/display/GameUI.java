package display;

//updated
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
	private BoxQueue inputCollection = new BoxQueue();

	// Hold the sequence of BoxButton objects selected by player
	ArrayList<BoxButton> inputList = new ArrayList<BoxButton>();

	JLabel levelGUI = new JLabel("Level: " + driver.difficulty.getLevel());

	int levelCount = driver.difficulty.getLevel();

	public GameUI() {
		super();
		this.setVisible(true);
		this.setTitle("Memory Test Game");
		this.setSize(700, 700);
		this.setResizable(false);
		this.setLocation(500, 100);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());

		JPanel control = new JPanel();
		control.setLayout(new FlowLayout());
		start.addActionListener(this);
		control.add(start);
		control.add(levelGUI);
		this.add(control, BorderLayout.NORTH);

		JPanel boxMatrix = new JPanel();
		boxMatrix.setLayout(new GridLayout(5, 5));
		for (int i = 0; i < 5; i++)
			for (int j = 0; j < 5; j++) {
				boxes[i][j] = new BoxButton("", i, j);
				boxes[i][j].addActionListener(this);
				boxes[i][j].setEnabled(false);
				boxMatrix.add(boxes[i][j]);
			}
		this.add(boxMatrix, BorderLayout.CENTER);

	}

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		GameUI test = new GameUI();
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
				Thread.sleep(400);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		for (BoxButton button : tempBoxes) {
			button.setBackground(null);
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
			// start.setVisible(false);
		}
		// Receive the input of player and return the result
		else if (arg0.getSource() instanceof BoxButton) {
			BoxButton source = (BoxButton) arg0.getSource();
			Boolean isExisted = false;

			for (BoxButton element : inputList) {
				if (source.equals(element)) {
					isExisted = true;
					break;
				}
			}
			if (isExisted == false) {
				inputList.add(source);
				inputCollection.add(source.getX(), source.getY());
				source.setBackground(Color.BLUE);
			}

			if (inputList.size() == driver.difficulty.getNumberOfBoxes()) {
				String result;
				Object[] options = new Object[2];

				try {
					if (driver.compare(inputCollection)) {
						result = "Correct Answer";
						// Increases level
						driver.difficulty.setLevel(++levelCount);
						// Set options
						options[0] = "Continue";
						options[1] = "Quit";
					} else {
						result = "Wrong Answer";
						// Resets game back to level 1
						driver.difficulty.setLevel(1);
						// Set options
						options[0] = "Retry";
						options[1] = "Quit";
					}
					int input = JOptionPane.showOptionDialog(this, result, "Result", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

					// Updates the JLabel to display the current level
					levelGUI.setText("Level: " + driver.difficulty.getLevel());

					for (BoxButton element : inputList)
						element.setBackground(null);

					inputList.clear();
					inputCollection.clear();

					for (int i = 0; i < 5; i++)
						for (int j = 0; j < 5; j++)
							boxes[i][j].setEnabled(false);

//					JOptionPane.showMessageDialog(this, result, "Result", JOptionPane.INFORMATION_MESSAGE);

					if (input == JOptionPane.YES_OPTION) {
						if (driver.difficulty.getLevel() == 1) {
							start.setEnabled(true);
							// start.setVisible(true);
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
