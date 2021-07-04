public class Planet{
	
	public double xxPos;
	public double yyPos;
	public double yyVel;
	public double xxVel;
	public double mass;
	public String imgFileName;
	

  public Planet(double xxP, double yyP, double xxV,
            double yyV, double m, String img) {
		this.xxPos = xxP;
		this.yyPos = yyP;
		this.xxVel = xxV;
	      this.yyVel = yyV;
		this.mass = m;
		this.imgFileName = img;
	}

	public Planet(Planet p) {
		this.xxPos = p.xxPos;
		this.yyPos = p.yyPos;
		this.xxVel = p.xxVel;
		this.yyVel = p.yyVel;
		this.mass = p.mass;
		this.imgFileName = p.imgFileName;
	}

	public double calcDistance(Planet p) {
		double dx = this.xxPos - p.xxPos;
		double dy = this.yyPos - p.yyPos;
		return Math.sqrt(dx*dx + dy*dy);
	}
	
	public double calcForceExertedBy(Planet p) {
		double g = 6.67 * 1e-11;
		double f = g * p.mass * this.mass / (calcDistance(p) * calcDistance(p));
		return f;
	}

	public double calcForceExertedByX(Planet p) {
		double dx = - this.xxPos + p.xxPos;
		return calcForceExertedBy(p) * dx /calcDistance(p);
	}

	public double calcForceExertedByY(Planet p) {
		double dy = - this.yyPos + p.yyPos;
		return calcForceExertedBy(p) * dy /calcDistance(p);
	}

	public double calcNetForceExertedByX(Planet[] p) {
		int i = 0;
		double sfx = 0;
		while(i < p.length) {
			if(this.equals(p[i])) {
				i++;
				continue;
			}
			sfx += calcForceExertedByX(p[i]);
			i++;
		}
		return sfx;
		
	}

	public double calcNetForceExertedByY(Planet[] p) {
		int i = 0;
		double sfy = 0;
		while(i < p.length) {
			if(this.equals(p[i])) {
				i++;
				continue;
			}
			sfy += calcForceExertedByY(p[i]);
			i++;
		}
		return sfy;
	}

	public void update(double dt, double fX, double fY) {
		double ax = fX / this.mass;
		double ay = fY / this.mass;
		this.xxVel = this.xxVel + ax * dt;
		this.yyVel = this.yyVel + ay * dt;
		this.xxPos = this.xxPos + this.xxVel * dt;
		this.yyPos = this.yyPos + this.yyVel * dt;
		// return this;
	}

	public void draw() {
		// StdDraw.clear();
		StdDraw.picture(this.xxPos, this.yyPos, "images/" + this.imgFileName);
		StdDraw.show();
	} 
}

