public class X {
@Id
private int xi;
@Index//(unique="true")
private String xs;

public X(){};

public X(int xi, String xs) {
	super();
	this.xi = xi;
	this.xs = xs;
}

public int getXi() {
	return xi;
}

public String getXs() {
	return xs;
}

@Override
public String toString() {
	return "X [xi=" + xi + ", xs=" + xs + "]";
}


}
