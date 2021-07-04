public class NBody {
	
	public static double readRadius(String str){
		In in = new In(str);
		in.readInt();

		return in.readDouble();
	}

	public static Planet[] readPlanets(String str) {
		In in = new In(str);
		int n = in.readInt();
		in.readDouble();
		Planet[] planets = new Planet[n];
		int i = 0;
		
		while(i<n) {
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String name = in.readString();
			planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, name);
			i++;
		}

		return planets;
	}

	public static void main(String[] args) {

		double T = Double.parseDouble(args[0].trim());
		double dt = Double.parseDouble(args[1].trim());
		String fileName = args[2];
		Planet[] planets = readPlanets(fileName);
		double radius = readRadius(fileName);

		String imageToDraw = "images/starfield.jpg";

		StdDraw.setScale(-radius,radius);
		StdDraw.clear();
		StdDraw.picture(0, 0, imageToDraw);
		StdDraw.show();
		int i = 0;
		while(i<planets.length){
			planets[i].draw();
			i++;
		}

		StdDraw.enableDoubleBuffering();

		double time = 0;
		double[] xForces = new double[planets.length];
		double[] yForces = new double[planets.length];

		for (time=0;time<T;time+=dt) {
			int l = 0;
			int j = 0;
			int k = 0;
			while(l<planets.length) {
				xForces[l] = planets[l].calcNetForceExertedByX(planets);
				yForces[l] = planets[l].calcNetForceExertedByY(planets);
				l++;
			}
			while(j<planets.length) {
				planets[j].update(dt, xForces[j], yForces[j]);
				j++;
			}
			StdDraw.setScale(-radius,radius);
		  	StdDraw.clear();
			StdDraw.picture(0, 0, imageToDraw);
			StdDraw.show();
			while(k<planets.length) {
				planets[k].draw();
				k++;
			}			
			StdDraw.pause(10);

		}

		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int m = 0; m < planets.length; m++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[m].xxPos, planets[m].yyPos, planets[m].xxVel,
                  planets[m].yyVel, planets[m].mass, planets[m].imgFileName);
    	}

	}


}