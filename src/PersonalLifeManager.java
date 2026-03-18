import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class PersonalLifeManager {

    public static void main(String[] args) {
        new Dashboard();
    }
}

class Dashboard extends JFrame implements ActionListener {

    JButton taskBtn, habitBtn, quoteBtn, exitBtn;

    Dashboard() {

        setTitle("Personal Life Manager");
        setSize(400,300);
        setLayout(new GridLayout(4,1,10,10));

        JLabel title = new JLabel("PERSONAL LIFE MANAGER", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));

        taskBtn = new JButton("Task Manager");
        habitBtn = new JButton("Habit Tracker");
        quoteBtn = new JButton("Quote Generator");
        exitBtn = new JButton("Exit");

        add(title);
        add(taskBtn);
        add(habitBtn);
        add(quoteBtn);
        add(exitBtn);

        taskBtn.addActionListener(this);
        habitBtn.addActionListener(this);
        quoteBtn.addActionListener(this);
        exitBtn.addActionListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==taskBtn)
            new TaskManager();

        if(e.getSource()==habitBtn)
            new HabitTracker();

        if(e.getSource()==quoteBtn)
            new QuoteGenerator();

        if(e.getSource()==exitBtn)
            System.exit(0);
    }
}

class TaskManager extends JFrame implements ActionListener {

    JTextField taskField;
    DefaultListModel<String> taskList;
    JList<String> list;
    JButton addBtn, deleteBtn;

    TaskManager(){

        setTitle("Task Manager");
        setSize(400,300);
        setLayout(new BorderLayout());

        taskField = new JTextField();

        taskList = new DefaultListModel<>();
        list = new JList<>(taskList);

        addBtn = new JButton("Add Task");
        deleteBtn = new JButton("Delete Task");

        JPanel top = new JPanel(new BorderLayout());
        top.add(new JLabel("Enter Task:"), BorderLayout.WEST);
        top.add(taskField, BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        bottom.add(addBtn);
        bottom.add(deleteBtn);

        add(top, BorderLayout.NORTH);
        add(new JScrollPane(list), BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        addBtn.addActionListener(this);
        deleteBtn.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){

        if(e.getSource()==addBtn){
            String task = taskField.getText();
            if(!task.isEmpty()){
                taskList.addElement(task);
                taskField.setText("");
            }
        }

        if(e.getSource()==deleteBtn){
            int index = list.getSelectedIndex();
            if(index!=-1)
                taskList.remove(index);
        }
    }
}

class HabitTracker extends JFrame implements ActionListener {

    JCheckBox exercise, reading, meditation, study;
    JButton showBtn;
    JTextArea result;

    HabitTracker(){

        setTitle("Habit Tracker");
        setSize(350,300);
        setLayout(new FlowLayout());

        exercise = new JCheckBox("Exercise");
        reading = new JCheckBox("Reading");
        meditation = new JCheckBox("Meditation");
        study = new JCheckBox("Study");

        showBtn = new JButton("Show Completed Habits");
        result = new JTextArea(5,25);

        add(exercise);
        add(reading);
        add(meditation);
        add(study);
        add(showBtn);
        add(new JScrollPane(result));

        showBtn.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){

        String habits = "Completed Habits:\n";

        if(exercise.isSelected())
            habits += "Exercise\n";

        if(reading.isSelected())
            habits += "Reading\n";

        if(meditation.isSelected())
            habits += "Meditation\n";

        if(study.isSelected())
            habits += "Study\n";

        result.setText(habits);
    }
}

class QuoteGenerator extends JFrame implements ActionListener {

    JLabel quoteLabel;
    JButton generateBtn;

    String quotes[] = {
            "Believe in yourself.",
            "Success comes from consistency.",
            "Small steps every day.",
            "Never stop learning.",
            "Hard work beats talent."
    };

    QuoteGenerator(){

        setTitle("Quote Generator");
        setSize(400,200);
        setLayout(new BorderLayout());

        quoteLabel = new JLabel("Click button for a quote", JLabel.CENTER);
        generateBtn = new JButton("Generate Quote");

        add(quoteLabel, BorderLayout.CENTER);
        add(generateBtn, BorderLayout.SOUTH);

        generateBtn.addActionListener(this);

        setVisible(true);
    }

    public void actionPerformed(ActionEvent e){

        Random rand = new Random();
        int index = rand.nextInt(quotes.length);

        quoteLabel.setText(quotes[index]);
    }
}