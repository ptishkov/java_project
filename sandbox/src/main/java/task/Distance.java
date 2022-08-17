package task;

public class Distance {
	
	public static void main(String [] args) {
		Point p1 = new Point(7, 3);
		Point p2 = new Point(15, 12);
		System.out.println("Расстояние между точками a (" + p1.x + "; " + p1.y +
				") и b (" + p2.x + "; " + p2.y + ") = " + p1.distance(p2));
	}

}