import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class ColorGuessingGame extends JFrame {
    private JLabel colorDisplay;
    private JPanel buttonPanel;
    private JButton resetButton;
    private JLabel scoreLabel;
    private Color[] colors;
    private String[] colorNames;
    private int currentColorIndex;
    private int score;
    private int incorrectGuesses;

    public ColorGuessingGame() {
        initializeComponents();
        setupUI();
        pack();
        setTitle("Color Guessing Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private void initializeComponents() {
        colors = new Color[]{Color.RED, Color.GREEN, Color.BLUE, Color.YELLOW, Color.MAGENTA};
        colorNames = new String[]{"Red", "Green", "Blue", "Yellow", "Magenta"};
        colorDisplay = new JLabel();
        colorDisplay.setOpaque(true);
        colorDisplay.setPreferredSize(new Dimension(100, 100));
        buttonPanel = new JPanel();
        for (int i = 0; i < colors.length; i++) {
            JButton button = new JButton(colorNames[i]);
            button.addActionListener(this::buttonClicked);
            buttonPanel.add(button);
        }
        resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> resetGame());
        scoreLabel = new JLabel("Score: 0, Incorrect guesses: 0");
    }
    private void setupUI() {
    	setLayout(new BorderLayout());
    	add(colorDisplay, BorderLayout.CENTER);
    	add(buttonPanel, BorderLayout.SOUTH);
    	add(resetButton, BorderLayout.NORTH);
    	add(scoreLabel, BorderLayout.EAST);
    	selectRandomColor();
    }

    private void selectRandomColor() {
        Random random = new Random();
        currentColorIndex = random.nextInt(colors.length);
        colorDisplay.setBackground(colors[currentColorIndex]);
    }

    private void buttonClicked(ActionEvent e) {
        JButton clickedButton = (JButton) e.getSource();
        if (clickedButton.getText().equalsIgnoreCase(colorNames[currentColorIndex])) {
            score++;
        } else {
            incorrectGuesses++;
        }
        updateScoreLabel();
        selectRandomColor();
    }

    private void resetGame() {
        score = 0;
        incorrectGuesses = 0;
        updateScoreLabel();
        selectRandomColor();
    }

    private void updateScoreLabel() {
        scoreLabel.setText("Score: " + score + ", Incorrect guesses: " + incorrectGuesses);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ColorGuessingGame::new);
    }
}
