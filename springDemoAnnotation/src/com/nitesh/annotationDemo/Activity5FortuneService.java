package com.nitesh.annotationDemo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class Activity5FortuneService implements FortuneService {

	private String fileName = "/home/peter/Documents/Nitesh Java/activity5.txt";
	private List<String> theFortunes;

	@PostConstruct
	public void condstructFortuneService() {
		File theFile = new File(fileName);

		System.out.println("Reading fortunes from file: " + theFile);
		System.out.println("File exists: " + theFile.exists());

		// initialize array list
		theFortunes = new ArrayList<String>();

		// read fortunes from file
		try (BufferedReader br = new BufferedReader(new FileReader(theFile))) {

			String tempLine;

			while ((tempLine = br.readLine()) != null) {
				theFortunes.add(tempLine);
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private Random myRandom = new Random();

	@Override
	public String getFortune() {
		int index = myRandom.nextInt(theFortunes.size());
		return "Activity5FortuneService : getFortune()"+theFortunes.get(index);
	}

}
