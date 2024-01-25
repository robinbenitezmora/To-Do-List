import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

class Task extends JPanel {
 JLabel index;
 JTextField taskName;
 JButton done;

 Color pink = new Color(255, 161, 161);
 Color green = new Color(188, 226, 158);
 Color doneColor = new Color(233, 119, 119);

 private boolean checked;

 Task() {
  this.setPreferredSize(new Dimension(400, 20));
  this.setBackground(pink);
  this.setLayout(new BorderLayout());

  checked = false;

  index = new JLabel("");
  index.setPreferredSize(new Dimension(20, 20));
  index.setHorizontalAlignment(JLabel.CENTER);
  this.add(index, BorderLayout.WEST);

  taskName = new JTextField("Write something..");
  taskName.setBorder(BorderFactory.createEmptyBorder());
  taskName.setBackground(pink);

  this.add(taskName, BorderLayout.CENTER);

  done = new JButton("Done");
  done.setPreferredSize(new Dimension(60, 20));
  done.setBackground(green);
  done.setBorder(BorderFactory.createEmptyBorder());
  done.setFocusPainted(false);
  this.add(done, BorderLayout.EAST);
 }

 public void changeIndex(int num) {
  this.index.setText(num + "");
  this.revalidate();
 }

 public JButton getDone() {
  return done;
 }

 public boolean getState() {
  return checked;
 }

 public void changeState() {
  this.setBackground(green);
  this.taskName.setBackground(green);
  this.done.setBackground(doneColor);
  this.checked = true;
  revalidate();
 }
}

class List extends JPanel {
 Color lightColor = new Color(252, 221, 176);

 List() {
  GridLayout layout = new GridLayout(10, 1);
  layout.setVgap(5);

  this.setLayout(layout);
  this.setBackground(lightColor);
  this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
  this.setPreferredSize(new Dimension(400, 400));
 }

 public void updateNumbers() {
  Component[] listItems = this.getComponents();

  for (int i = 0; i < listItems.length; i++) {
   if (listItems[i] instanceof Task) {
    ((Task) listItems[i]).changeIndex(i + 1);
   }
  }
 }

 public void removeCompletedTasks() {
  for (Component c : getComponents()) {
   if (c instanceof Task) {
    if (((Task) c).getState()) {
     this.remove(c);
     updateNumbers();
    }
   }
  }
 }
}

class Footer extends JPanel {
 JButton addTask;
 JButton clear;

 Color orange = new Color(233, 133, 128);
 Color lightColor = new Color(252, 221, 176);
 javax.swing.border.Border emptyBorder = BorderFactory.createEmptyBorder();

 Footer() {
  this.setPreferredSize(new Dimension(400, 60));
  this.setBackground(lightColor);
 }

 public JButton getNewTask() {
  return addTask;
 }

 public JButton getClear() {
  return clear;
 }
}

class TitleBar extends JPanel {
 Color lightColor = new Color(252, 221, 176);

 TitleBar() {
  this.setPreferredSize(new Dimension(400, 60));
  this.setBackground(lightColor);
  JLabel litteText = new JLabel("To Do List");
  litteText.setFont(new Font("Serif", Font.BOLD, 30));
  litteText.setPreferredSize(new Dimension(200, 60));
  litteText.setHorizontalAlignment(JLabel.CENTER);
  this.add(litteText);
 }
}

class AppFrame extends JFrame {
 private TitleBar title;
 private Footer footer;
 private List list;

 private JButton newTask;
 private JButton clear;

 AppFrame() {
  this.setSize(400, 600);
  this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  this.setVisible(true);

  title = new TitleBar();
  footer = new Footer();
  list = new List();

  this.add(title, BorderLayout.NORTH);
  this.add(footer, BorderLayout.SOUTH);
  this.add(list, BorderLayout.CENTER);

  newTask = footer.getNewTask();
  clear = footer.getClear();

  addListener();
 }

 public void addListener() {
  newTask.addMouseListener(new MouseAdapter() {
   @Override
   public void mousePressed(MouseEvent e) {
    Task task = new Task();
    list.add(task);
    list.updateNumbers();

    task.getDone().addMouseListener(new MouseAdapter() {
     @Override
     public void mousePressed(MouseEvent e) {
      task.changeState();
      list.updateNumbers();
      revalidate();
     }
    });
   }
  });

  clear.addMouseListener(new MouseAdapter() {
   @Override
   public void mousePressed(MouseEvent e) {
    list.removeCompletedTasks();
    repaint();
   }
  });
 }
}
