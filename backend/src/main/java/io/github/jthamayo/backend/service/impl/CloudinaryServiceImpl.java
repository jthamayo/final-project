package io.github.jthamayo.backend.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;

import io.github.jthamayo.backend.service.CloudinaryService;
import jakarta.annotation.Resource;

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    private Cloudinary cloudinary;

    public CloudinaryServiceImpl(Cloudinary cloudinary) {
	this.cloudinary = cloudinary;
    }

    @Override
    public String uploadFile(MultipartFile file, String folderName) {
	try {

	    HashMap<Object, Object> options = new HashMap<>();
	    options.put("folder", folderName);
	    Map uploadedFile = cloudinary.uploader().upload(file.getBytes(), options);
	    String publicId = (String) uploadedFile.get("public_id");
	    return cloudinary.url().secure(true).generate(publicId);

	} catch (IOException e) {
	    throw new RuntimeException("Cloudinary upload failed", e);
	}
    }

}
