package tel_ran.picture.mongoDB.entity;

import java.util.*;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="2016_11_Picture")
public class Picture {
@Indexed
String url;
@Indexed
String tag;
@Indexed
Double confidence;

public Picture(){};
public Picture(String url, String tag, Double confidence) {
	super();
	this.url = url;
	this.tag = tag;
	this.confidence = confidence;
}
public String getUrl() {
	return url;
}
public String getTag() {
	return tag;
}
public Double getConfidence() {
	return confidence;
}
@Override
public String toString() {
	return "Picture [url=" + url + ", tag=" + tag + ", confidence=" + confidence + "]";
}



}
