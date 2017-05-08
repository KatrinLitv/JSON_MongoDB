import java.util.Arrays;

//import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import com.fasterxml.jackson.annotation.JsonSubTypes;
//import com.fasterxml.jackson.annotation.JsonTypeInfo;
//import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
//import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
//@JsonIgnoreProperties(ignoreUnknown=true)
//@JsonTypeInfo(use=Id.NAME,include=As.PROPERTY)
//@JsonSubTypes({
//	@JsonSubTypes.Type(value=Y.class,name="SLON")
//})
public class X {
private int xi;
private float xarf [];
private String xs;

public int getXi() {
	return xi;
}
public float[] getXarf() {
	return xarf;
}
public String getXs() {
	return xs;
}
public X(int xi, float[] xarf, String xs) {
	super();
	this.xi = xi;
	this.xarf = xarf;
	this.xs = xs;
}

public X() {
	super();
}
@Override
public String toString() {
	return "X [xi=" + xi + ", xarf=" + Arrays.toString(xarf) + ", xs=" + xs + "]";
}

}
