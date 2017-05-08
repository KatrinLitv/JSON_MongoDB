package tel_ran.picture.mongoDB.repo;

import org.springframework.data.repository.CrudRepository;
import tel_ran.picture.mongoDB.entity.Picture;

public interface PicturesRepository extends CrudRepository<Picture,String> {
	Iterable<Picture> findByUrl(String url);
}
