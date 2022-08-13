public class Distance {
	
	public static void main(String [] args) {
		Point1 a = new Point1();
		a.p1 = 10;
		a.p2 = 15;
		Point1 b = new Point1();
		b.p1 = 3;
		b.p2 = 17;
		System.out.println("Расстояние между точками a (" + a.p1 + "; " + a.p2 +
				") и b (" + b.p1 + "; " + b.p2 + ") = " + distance(a,b));
	}

	public static double distance(Point1 a, Point1 b) {
		return Math.sqrt((a.p1 - b.p1) * (a.p1 - b.p1) + (a.p2 - b.p2) * (a.p2 - b.p2));
	}


}