package ua.bakhtin.metr2.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

public class PhotoValidator implements ConstraintValidator<ValidPhoto, MultipartFile[]>{
	
	private static final long FILE_SIZE = 5000000L;
	private static final long TOTAL_FILE_SIZE = 10000000L;
	private static final String [] FILE_TYPES = new String [] {MediaType.IMAGE_JPEG_VALUE};
	
	
	public PhotoValidator() {}
	
	@Override
    public void initialize(ValidPhoto photos) {
    }
	
	@Override
	public boolean isValid(MultipartFile[] photos, ConstraintValidatorContext context) {
		
		System.out.println("ФОТО ВАЛИДАТОР!!!");
		
		if (photos[0] == null || photos[0].getSize() == 0) {
			return true;
		}
		
		long totalSize = 0L;
		for (MultipartFile multipartFile : photos) {
			System.out.println("multipartFile.getContentType() " + multipartFile.getContentType());
			System.out.println("FILE_TYPES[0] " + FILE_TYPES[0]);
			if(!multipartFile.getContentType().equals(FILE_TYPES[0])) {
				return false;
			}
			if(multipartFile.getSize() > FILE_SIZE) {
				return false;
			}
			totalSize += multipartFile.getSize();
		}
		
		if (totalSize > TOTAL_FILE_SIZE) {
			return false;
		} else {
			return true;
		}
	}

}
