public class Distance {
	
	public static void main(String [] args) {
		Point a = new Point(10, 15);
		Point b = new Point(3, 17);
		System.out.println("Расстояние между точками a (" + a.p1 + "; " + a.p2 +
				") и b (" + b.p1 + "; " + b.p2 + ") = " + distance(a,b));
	}

	public static double distance(Point a, Point b) {
		return Math.sqrt((a.p1 - b.p1) * (a.p1 - b.p1) + (a.p2 - b.p2) * (a.p2 - b.p2));
	}


}