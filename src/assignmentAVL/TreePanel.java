package assignmentAVL;

//importing the packages

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

/* creating the class
 *extending the class
 */
public class TreePanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private Graphics2D g2;

    public TreePanel() {

    }

    public void drawTree(Tree localTree) {
        if (localTree == null) {
            return;
        }
        Tree parent = localTree.getParent();

        if (parent == null) {
            // 1st Level
            localTree.setX(Parameters.WIDTH / 2);
            localTree.setY(Parameters.TOP_MARGIN);

        } else if (parent.getParent() == null) {
            // 2nd Level
            if (localTree.getValue() < parent.getValue()) {
                localTree.setX(Parameters.WIDTH / 4);
			} else {
                localTree.setX(Parameters.WIDTH - Parameters.WIDTH / 4);
			}
			localTree.setY(parent.getY() + Parameters.NODE_MARGIN);
		} else {
            // other Levels
            if (localTree.getValue() < parent.getValue()) {
                localTree.setX(parent.getX() - Math.abs(parent.getX() - parent.getParent().getX()) / 2);
			} else {
                localTree.setX(parent.getX() + Math.abs(parent.getX() - parent.getParent().getX()) / 2);
			}
			localTree.setY(parent.getY() + Parameters.NODE_MARGIN);

		}
        if (parent != null) {
            g2.setColor(Parameters.COLOR_LINE);
            g2.drawLine(parent.getX() + Parameters.DIAMETER / 2, parent.getY() + Parameters.DIAMETER - 1, localTree.getX() + 17, localTree.getY() + 17);
        }

        g2.setColor(Parameters.COLOR_NODE);
        if (localTree.getValue() == TreeLogic.getAdded()) {
            g2.setColor(Parameters.COLOR_ADDED);
        }
        if (localTree.getValue() == TreeLogic.getRemoved()) {
            g2.setColor(Parameters.COLOR_REMOVED);
        }
        if (localTree.getValue() == TreeLogic.getDegenerated()) {
            g2.setColor(Parameters.COLOR_DEG);
        }

        g2.fillOval(localTree.getX(), localTree.getY(), Parameters.DIAMETER, Parameters.DIAMETER);

        g2.setColor(Parameters.COLOR_VALUE);
        if (localTree.getValue() == TreeLogic.getAdded()) {
            g2.setColor(Color.WHITE);
        }
        g2.setFont(Parameters.NODE_FONT);
        String nodeString = localTree.getValue() + "";
        FontSize fontSize = getStringSize(nodeString);
        g2.drawString(nodeString, localTree.getX() + (Parameters.DIAMETER - fontSize.getWidth()) / 2, localTree.getY() + 22);

        drawTree(localTree.getLeftChild());
        drawTree(localTree.getRightChild());
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        drawTree(TreeLogic.getTree());
    }

    private FontSize getStringSize(String str) {
        AffineTransform affinetransform = new AffineTransform();
        FontRenderContext frc = new FontRenderContext(affinetransform, true, true);
        Font font = Parameters.NODE_FONT;
        FontSize fontSize = new FontSize();
        fontSize.setWidth((int) (font.getStringBounds(str, frc).getWidth()));
        fontSize.setHeight((int) (font.getStringBounds(str, frc).getHeight()));

        return fontSize;
    }
}
