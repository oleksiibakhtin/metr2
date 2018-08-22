package ua.bakhtin.metr2.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.bakhtin.metr2.model.Estate;

@Repository("estateRepository")
public interface EstateRepository extends CrudRepository<Estate, Long> {
	public Estate getEstateById (Long id);
	public List <Estate> getEstatesByUserId(Long id);
	public Long countByStatusType (String statusType);
	public List<Estate> getEstatesByStatusTypeOrderByInTopDescDateDesc(String statusType);
}