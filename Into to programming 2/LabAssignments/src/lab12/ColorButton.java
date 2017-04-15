package lab12;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.*;

@SuppressWarnings("serial")
public class ColorButton extends JButton
{
	Color penColor;
	String label;
	Dimension dimension;
	
	public ColorButton(String label, Color penColor, int width, int height){
		super(label);
		this.label = label;
		this.penColor = penColor;
		this.dimension = new Dimension(width, height);
		
	}

	/**
	 * @return the penColor
	 */
	public Color getPenColor() {
		return penColor;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @return the dimension
	 */
	public Dimension getDimension() {
		return dimension;
	}
}
