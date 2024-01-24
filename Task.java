import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JButton;
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