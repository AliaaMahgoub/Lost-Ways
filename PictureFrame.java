//Source Code: https://www.youtube.com/watch?v=FdQX8sUNO-s

import java.awt.*;
import javax.swing.*;

public class PictureFrame extends JFrame {
  private ImageIcon image;
  private JLabel label1;

  PictureFrame() {
    setLayout(new FlowLayout());
    image = new ImageIcon("ascii.png");
    label1 = new JLabel(image);
    add(label1);
  }
}
