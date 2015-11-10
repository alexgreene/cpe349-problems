import java.awt.*;

public class Vertex {
	
	private int id;
	private Color color;

	public Vertex(int id){
		this.id = id;
		color = Color.WHITE;
	}

	public void setColor(Color color){
		this.color = color;
	}

	public int getId(){
		return this.id;
	}

	public int hashCode(){
		return this.id;
	}

	public boolean equals(Object o){

		if(o == null || o.getClass() != this.getClass())
			return false;

		Vertex v = (Vertex) o;
		if (this.id == v.id)
			return true;
		return false;
	}
}