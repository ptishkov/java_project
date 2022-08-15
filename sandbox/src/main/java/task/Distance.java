package task;

public class Distance {
	
	public static void main(String [] args) {
		Point p1 = new Point(7, 3);
		Point p2 = new Point(15, 12);
		System.out.println("Расстояние между точками a (" + p1.p1 + "; " + p1.p2 +
				") и b (" + p2.p1 + "; " + p2.p2 + ") = " + p1.distance(p2));
	}

}