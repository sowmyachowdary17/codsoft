import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuizApplication extends JFrame {
    private static final long serialVersionUID = 1L;
    
    private JLabel questionLabel;
    private JRadioButton[] options;
    private ButtonGroup group;
    private JButton nextButton;
    private JLabel timerLabel;
    private Timer timer;
    private int timeLeft = 30; // Time for each question in seconds

    private int currentQuestion = 0;
    private int score = 0;

    private String[][] questions = {
        {"Question 1: what is the capital city of india", "New Delhi", "London", "Rome", "Berlin"},
      {"Question 1: what is the capital city of canada", "australia", "London", "Ottawa", "Berlin"},
        {"Question 2: What is 2 + 2?", "3", "4", "5", "6"},
        // Add more questions as needed
    };

    private String[] answers = {"New Delhi","Ottawa", "4"};

    public QuizApplication() {
        setTitle("Quiz Application");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        questionLabel = new JLabel();
        options = new JRadioButton[4];
        group = new ButtonGroup();
        for (int i = 0; i < 4; i++) {
            options[i] = new JRadioButton();
            group.add(options[i]);
        }

        nextButton = new JButton("Next");
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                checkAnswer();
                nextQuestion();
            }
        });

        timerLabel = new JLabel("Time left: " + timeLeft + "s");
        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timeLeft--;
                timerLabel.setText("Time left: " + timeLeft + "s");
                if (timeLeft <= 0) {
                    checkAnswer();
                    nextQuestion();
                }
            }
        });

        setLayout(new GridLayout(7, 1));
        add(questionLabel);
        for (JRadioButton option : options) {
            add(option);
        }
        add(timerLabel);
        add(nextButton);

        loadQuestion();
        timer.start();
    }

    private void loadQuestion() {
        if (currentQuestion < questions.length) {
            questionLabel.setText(questions[currentQuestion][0]);
            for (int i = 0; i < 4; i++) {
                options[i].setText(questions[currentQuestion][i + 1]);
            }
            group.clearSelection();
            timeLeft = 30;
            timerLabel.setText("Time left: " + timeLeft + "s");
        } else {
            endQuiz();
        }
    }

    private void checkAnswer() {
        for (int i = 0; i < 4; i++) {
            if (options[i].isSelected() && options[i].getText().equals(answers[currentQuestion])) {
                score++;
            }
        }
    }

    private void nextQuestion() {
        currentQuestion++;
        loadQuestion();
    }

    private void endQuiz() {
        timer.stop();
        JOptionPane.showMessageDialog(this, "Quiz over! Your score: " + score);
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new QuizApplication().setVisible(true);
            }
        });
    }
}