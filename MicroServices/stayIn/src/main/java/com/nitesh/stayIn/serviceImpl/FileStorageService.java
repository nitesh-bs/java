package com.nitesh.stayIn.serviceImpl;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.nitesh.stayIn.exception.BadRequestException;

@Service
public class FileStorageService {

	private final Path fileStorageLocation;

	@Autowired
	public FileStorageService(Environment env) {

		this.fileStorageLocation = Paths.get(env.getProperty("stayIn.file.upload-dir", "./rentImages/"))
				.toAbsolutePath().normalize();

		try {
			Files.createDirectories(this.fileStorageLocation);
		} catch (Exception ex) {
			throw new BadRequestException("Could not create the directory where the uploaded files will be stored.",
					ex);
		}
	}

	public String getFileExtension(String fileName) {
		if (fileName == null) {
			return null;
		}
		String[] fileNameParts = fileName.split("\\.");

		return fileNameParts[fileNameParts.length - 1];
	}

	public String storeFile(MultipartFile file) {
		// Normalize file name
		String fileName = new Date().getTime() + "- file." + getFileExtension(file.getOriginalFilename());

		try {
			// Check if the filename contains invalid characters
			if (fileName.contains("..")) {
				throw new BadRequestException("Sorry! Filename contains invalid path sequence " + fileName);
			}

			Path targetLocation = this.fileStorageLocation.resolve(fileName);
			Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

			return fileName;
		} catch (IOException ex) {
			throw new BadRequestException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}

	public Resource loadFileAsResource(String fileName) {
		try {
			Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
			Resource resource = new UrlResource(filePath.toUri());
			if (resource.exists()) {
				return resource;
			} else {
				throw new BadRequestException("File not found " + fileName);
			}
		} catch (MalformedURLException ex) {
			throw new BadRequestException("File not found " + fileName, ex);
		}
	}
}