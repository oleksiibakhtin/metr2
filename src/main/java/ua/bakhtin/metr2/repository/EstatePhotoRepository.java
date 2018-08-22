package ua.bakhtin.metr2.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.bakhtin.metr2.model.EstatePhoto;

@Repository("estatePhotoRepository")
public interface EstatePhotoRepository extends CrudRepository<EstatePhoto, Long> {
    
}