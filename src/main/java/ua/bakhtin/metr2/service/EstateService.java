package ua.bakhtin.metr2.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ua.bakhtin.metr2.enums.EstateStatusType;
import ua.bakhtin.metr2.model.Estate;
import ua.bakhtin.metr2.model.EstatePhoto;
import ua.bakhtin.metr2.model.Estate_;
import ua.bakhtin.metr2.model.SearchingCondition;
import ua.bakhtin.metr2.model.User;
import ua.bakhtin.metr2.repository.EstateRepository;
import ua.bakhtin.metr2.repository.UserRepository;

@Service("estateService")
public class EstateService {

	private EstateRepository estateRepository;
	private UserRepository userRepository;
	
	@PersistenceContext
	private EntityManager entityManager;
	
	public EstateService() {}
	
	@Autowired
    public EstateService(EstateRepository estateRepository, UserRepository userRepository) {
      this.estateRepository = estateRepository;
      this.userRepository = userRepository;
    }
	
	@Transactional
	public void saveEstate(Estate newEstate, User currentUser, MultipartFile[] multipartfiles) {
		newEstate.setUser(userRepository.findById(currentUser.getId()).get());
		estateRepository.save(newEstate);
		
		Set<EstatePhoto> estatePhoto = new HashSet <>();
		for (MultipartFile file : multipartfiles) {
			if (!file.isEmpty()) {
			EstatePhoto photo = new EstatePhoto();
			photo.setEstate(newEstate);
			try {
				photo.setPhoto(file.getBytes());
			} catch (IOException e) {
				e.printStackTrace();
			}
			estatePhoto.add(photo);
			}
		}
		if (!estatePhoto.isEmpty()) {
		newEstate.setEstatePhoto(estatePhoto);
		}
	}
	
	public Estate getEstateById(Long id) {
		return estateRepository.getEstateById(id);
	}
	
	public List <Estate> getEstatesByUserId (Long id) {
		return estateRepository.getEstatesByUserId(id);
	}
	
	public Long countByStatusType (String statusType) {
		return estateRepository.countByStatusType (statusType);
	}
	
	public List <Estate> getInputEstates () {
		CriteriaBuilder criteriaBuilder  = entityManager.getCriteriaBuilder();
		CriteriaQuery<Estate> criteriaQuery = criteriaBuilder.createQuery(Estate.class);
		Root<Estate> root = criteriaQuery.from(Estate.class);
		Path <String> statusPath = root.get(Estate_.statusType);
		criteriaQuery.select(root);
		criteriaQuery.where(criteriaBuilder.equal(statusPath, EstateStatusType.APPLICATION.toString()));
		TypedQuery<Estate> typedQuery = entityManager.createQuery(criteriaQuery);
		return typedQuery.getResultList();
	}
	
	public List <Estate> getPublishedSortedEstates (String statusType) {
		return estateRepository.getEstatesByStatusTypeOrderByInTopDescDateDesc(statusType);
	}
	
	public List <Estate> getSearchingSortedEstates (SearchingCondition searchingCondition) {
		
		CriteriaBuilder criteriaBuilder  = entityManager.getCriteriaBuilder();
		CriteriaQuery<Estate> criteriaQuery = criteriaBuilder.createQuery(Estate.class);
		Root<Estate> root = criteriaQuery.from(Estate.class);
		
		Path <String> statusPath = root.get(Estate_.statusType);
		Path <String> namePath = root.get(Estate_.estateName);
		Path <String> operationTypePath = root.get(Estate_.operationType);
		Path <String> estateTypePath = root.get(Estate_.estateType);
		Path <String> regionTypePath = root.get(Estate_.regionType);
		Path <String> cityTypePath = root.get(Estate_.cityType);
		Path <String> districtTypePath = root.get(Estate_.districtType);
		Path <Integer> pricePath = root.get(Estate_.price);
		
		criteriaQuery.select(root);
		
		List <Predicate> keyWordPredicateList = new ArrayList<>();
		if (searchingCondition.getKeyWords() != null && StringUtils.hasText(searchingCondition.getKeyWords())) {
			String [] words = searchingCondition.getKeyWords().toLowerCase().split(" ");
			for (String word : words) {
				System.out.println(word);
				if(StringUtils.hasText(word) && word.length()>2)
				keyWordPredicateList.add(criteriaBuilder.like(namePath, "%"+word.substring(0, word.length()-1)+"%"));
			}
		} 
		
		List <Predicate> generalPredicateList = new ArrayList<>();
			generalPredicateList.add(criteriaBuilder.equal(statusPath, EstateStatusType.PUBLICATION.toString()));
			
			if (!searchingCondition.getOperationType().equals("all")) {
				generalPredicateList.add(criteriaBuilder.equal(operationTypePath, searchingCondition.getOperationType()));
			}
			
			if (!searchingCondition.getEstateType().equals("all")) {
				generalPredicateList.add(criteriaBuilder.equal(estateTypePath, searchingCondition.getEstateType()));
			}
			
			if (!searchingCondition.getRegionType().equals("all")) {
				generalPredicateList.add(criteriaBuilder.equal(regionTypePath, searchingCondition.getRegionType()));
			}
			
			if (!searchingCondition.getCityType().equals("all")) {
				generalPredicateList.add(criteriaBuilder.equal(cityTypePath, searchingCondition.getCityType()));
			}
			
			if (!searchingCondition.getDistrictType().equals("all")) {
				generalPredicateList.add(criteriaBuilder.equal(districtTypePath, searchingCondition.getDistrictType()));
			}
		
			if (searchingCondition.getFromPrice() != null) {
				generalPredicateList.add(criteriaBuilder.or(criteriaBuilder.greaterThanOrEqualTo(pricePath, searchingCondition.getFromPrice()), criteriaBuilder.isNull(pricePath)));
			}
					
			if (searchingCondition.getToPrice() != null) {
				generalPredicateList.add(criteriaBuilder.or(criteriaBuilder.lessThanOrEqualTo(pricePath, searchingCondition.getToPrice()), criteriaBuilder.isNull(pricePath)));
			}
			
			
			Predicate predicate;
			
			if (!keyWordPredicateList.isEmpty()) {
				predicate = criteriaBuilder.and(criteriaBuilder.or(keyWordPredicateList.toArray(new Predicate[keyWordPredicateList.size()])), 
											criteriaBuilder.and(generalPredicateList.toArray(new Predicate[generalPredicateList.size()]))
											);
			} else {
				predicate = criteriaBuilder.and(generalPredicateList.toArray(new Predicate[generalPredicateList.size()]));
			}
			
			criteriaQuery.where(predicate);
					
		TypedQuery<Estate> typedQuery = entityManager.createQuery(criteriaQuery);
		List <Estate> result = typedQuery.getResultList();
		System.out.println("result.size() " + result.size());
		return result;
	}
	
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public void updateEstate(Estate estate) {
		System.out.println("В СЕРВИСЕ updateEstate estate.getInTop() " + estate.getInTop());
		Estate updatingEstate = estateRepository.getEstateById(estate.getId());
		updatingEstate.setDate(LocalDateTime.now());
		updatingEstate.setEstateChanges(estate);
		estateRepository.save(updatingEstate);
	}
	
	//-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
	public void deleteEstate (Long id) { // тут воще дикий зашквар: чтобы удалить child-entity, нужно сначала удалить его из коллекции родителя при установленном orphanRemoval=true)
		userRepository.findById(estateRepository.getEstateById(id).getUser().getId()).get().removeEstate((estateRepository.findById(id).get()));
		estateRepository.deleteById(id);
	}
	

}
