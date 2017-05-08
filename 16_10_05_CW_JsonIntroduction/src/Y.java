import java.util.Arrays;

public class Y extends X {
String ys;
int yi;
public Y(int xi, float[] xarf, String xs, String ys, int yi) {
	super(xi, xarf, xs);
	this.ys = ys;
	this.yi = yi;
};
public Y(){};
public String getYs() {
	return ys;
}
public int getYi() {
	return yi;
}
@Override
public String toString() {
	return "Y [ys=" + ys + ", yi=" + yi + ", getXi()=" + getXi() + ", getXarf()=" + Arrays.toString(getXarf())
			+ ", getXs()=" + getXs() + ", toString()=" + super.toString() + ", getClass()=" + getClass()
			+ ", hashCode()=" + hashCode() + "]";
}

}
