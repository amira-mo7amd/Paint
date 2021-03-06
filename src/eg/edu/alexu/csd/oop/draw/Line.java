package eg.edu.alexu.csd.oop.draw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

public class Line extends ShapeEngine implements Cloneable{
	private Map<String,Double> line;
	public Line (){
		line = new HashMap<String,Double> ();
		line.put("length",null);
		line.put("slope",null);
		setProperties(line);
	}
	private int i(double v){
		// 5.8-0.5 >= 5.0
		if ((v-0.5) >= Math.floor(v)){
			return (int)Math.ceil(v);
		}
		return (int)Math.floor(v);
	}
	@Override
	public void draw(Graphics canvas) {
		Point p = getPosition();
		line=getProperties();
		Double l,s,x2,y2;
		l=line.get("length");
		s=line.get("slope");
		if (s<0)s+=360;
		y2 = p.getY()-l*Math.sin((((s)*Math.PI)/180.0));
		x2 = p.getX()+l*Math.cos((((s)*Math.PI)/180.0));
		// set color
		canvas.setColor(getColor());
		canvas.drawLine( i(p.getX()),i(p.getY()),i(x2), i(y2));
	}
	@Override
	public Object clone() throws CloneNotSupportedException {
		Shape New=null;
		try {
			New = (Shape) (Class.forName(this.getClass().getName())).newInstance();
			New.setPosition(this.getPosition());
			Color c1,c2;
			if (getColor()==null)c1=null;
			else c1 = new Color(getColor().getRGB());
			if (getFillColor()==null) c2=null;
			else c2 = new Color(getFillColor().getRGB());
			New.setColor(c1);
			New.setFillColor(c2);
			Double[] values = new Double[this.getProperties().size()];
			int temp=0;
			for (Map.Entry<String,Double>entry : this.getProperties().entrySet()){
				values[temp++]=entry.getValue();
			}
			Map<String,Double> to = New.getProperties();
			temp=0;
			for (Map.Entry<String,Double>entry : to.entrySet()){
				entry.setValue(values[temp++]);
			}
		}catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return New;
	}
}
