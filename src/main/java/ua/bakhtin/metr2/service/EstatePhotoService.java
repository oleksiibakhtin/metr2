package ua.bakhtin.metr2.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.bakhtin.metr2.model.EstatePhoto;
import ua.bakhtin.metr2.repository.EstatePhotoRepository;

@Service("estatePhotoService")
public class EstatePhotoService {

	private EstatePhotoRepository estatePhotoRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public EstatePhotoService() {}
	
	@Autowired
    public EstatePhotoService(EstatePhotoRepository estateRepository) { 
      this.estatePhotoRepository = estatePhotoRepository;
    }
	
	public void saveEstatePhoto(EstatePhoto estatePhoto) {
		estatePhotoRepository.save(estatePhoto);
	}
	
//	public boolean saveEstatePhotos(MultipartFile[] photos, Long estateId) {
//		for (MultipartFile photo : photos) {
//			try {
//				estatePhotoRepository.save(new EstatePhoto(estateId, photo.getBytes()));
//			} catch (IOException e) {
//				e.printStackTrace();
//				return false;
//			}
//		}
//		return true;
//	}
	
//	public List <Estate> getEstatesByUserId (Long id) {
//		CriteriaBuilder criteriaBuilder  = entityManager.getCriteriaBuilder();
//		CriteriaQuery<Estate> criteriaQuery = criteriaBuilder.createQuery(Estate.class);
//		Root<Estate> root = criteriaQuery.from(Estate.class);
//		Path <Long> usrIdPath = root.get(Estate_.usrId);
//		criteriaQuery.select(root);
//		criteriaQuery.where(criteriaBuilder.equal(usrIdPath, id));
//		TypedQuery<Estate> typedQuery = entityManager.createQuery(criteriaQuery);
//		return typedQuery.getResultList();
//	}
//	
//	public List <Estate> getInputEstates () {
//		CriteriaBuilder criteriaBuilder  = entityManager.getCriteriaBuilder();
//		CriteriaQuery<Estate> criteriaQuery = criteriaBuilder.createQuery(Estate.class);
//		Root<Estate> root = criteriaQuery.from(Estate.class);
//		Path <String> statusPath = root.get(Estate_.status);
//		criteriaQuery.select(root);
//		criteriaQuery.where(criteriaBuilder.equal(statusPath, "Заявка"));
//		TypedQuery<Estate> typedQuery = entityManager.createQuery(criteriaQuery);
//		return typedQuery.getResultList();
//	}
//	
//	public List <Estate> getPublishedEstates () {
//		CriteriaBuilder criteriaBuilder  = entityManager.getCriteriaBuilder();
//		CriteriaQuery<Estate> criteriaQuery = criteriaBuilder.createQuery(Estate.class);
//		Root<Estate> root = criteriaQuery.from(Estate.class);
//		Path <String> statusPath = root.get(Estate_.status);
//		criteriaQuery.select(root);
//		criteriaQuery.where(criteriaBuilder.equal(statusPath, "Публикация"));
//		TypedQuery<Estate> typedQuery = entityManager.createQuery(criteriaQuery);
//		return typedQuery.getResultList();
//	}
	
	public EstatePhoto findEstatePhoto (Long id) {
		return entityManager.find(EstatePhoto.class, id);
	}

}
