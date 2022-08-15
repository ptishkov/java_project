package Task;

public class Distance {
	
	public static void main(String [] args) {
		Point a = new Point(7, 3);
		Point b = new Point(15, 12);
		System.out.println("Расстояние между точками a (" + a.p1 + "; " + a.p2 +
				") и b (" + b.p1 + "; " + b.p2 + ") = " + a.distance(b));
	}

}