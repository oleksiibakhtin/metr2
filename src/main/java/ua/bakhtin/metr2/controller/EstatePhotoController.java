package ua.bakhtin.metr2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ua.bakhtin.metr2.service.EstatePhotoService;

@Controller
@RequestMapping (value = "/show/{id}")
public class EstatePhotoController {
	
	@Autowired
	private EstatePhotoService estatePhotoService;
	
	@GetMapping (produces = MediaType.IMAGE_JPEG_VALUE)
	@ResponseBody
	public byte[] showPhoto (@PathVariable Long id) {
		System.out.println("id " + id);
		return estatePhotoService.findEstatePhoto(id).getPhoto();
	}

}
