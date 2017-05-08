package tel_ran.picture.mongoDB.dao;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import tel_ran.picture.mongoDB.entity.Picture;
import tel_ran.picture.mongoDB.repo.PicturesRepository;

public class PictureMongoDB {
@Autowired
PicturesRepository pictures;
List<Picture> resList = new LinkedList<>();
static String authToken = "Basic YWNjXzMxNTQ1YTFmZjA1MDNjNzozMDRiODI2ZDc1MDg4MTA2ZWNjM2NhNWQyN2ZkMTdlYQ==";
static String urlService = "http://api.imagga.com/v1/tagging?url=";
static RestTemplate restTemplate = new RestTemplate();

public boolean addPicture(Picture picture) {
	if ((picture==null)||(getByUrl(picture.getUrl())!=null))
		return false;
	pictures.save(picture);
	return true;
}

public Iterable<Picture> getByUrl(String url){
	return pictures.findByUrl(url);
}

public void saveMany(List<String> urls) {
	List<Picture> list = getListPictures(urls);
	pictures.save(list);
}

private List<Picture> getListPictures(List<String> urls) {
	HttpHeaders headers = new HttpHeaders();
	headers.add("Authorization", authToken);
	HttpEntity<String> request = new HttpEntity<String>(headers);	
	for (String urlPicture : urls){
		ResponseEntity<Map<String,List<Map<String,Object>>>> response = 
				restTemplate.exchange(urlService+urlPicture,HttpMethod.GET,request, 
						new ParameterizedTypeReference<Map<String,List<Map<String,Object>>>>() {
						});	
		Map<String, List<Map<String, Object>>> map = response.getBody();
		if (map!= null)
			getPictures(map);
	}
	return resList;
}

@SuppressWarnings("unchecked")
private void getPictures(Map<String, List<Map<String, Object>>> map) {
	if ((map.get("results")==null) || (map.get("results").size()==0) || (map.get("results").get(0)==null))
		return;
	String url = (String) map.get("results").get(0).get("image");
	List<Map<String,Object>> tags = (List<Map<String, Object>>) map.get("results").get(0).get("tags");
	if (tags==null)
		return;
	for(Map<String,Object> tagPair : tags ){
		String tag = (String) tagPair.get("tag");
		Double confidence = (Double) tagPair.get("confidence");	
		Picture pic=new Picture(url, tag, confidence);
		System.out.println(pic);
		resList.add(pic);
	}	
}
}

