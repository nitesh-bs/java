package com.nitesh.cassandradataloader;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.cassandra.CqlSessionBuilderCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.format.annotation.DateTimeFormat;

import com.nitesh.cassandradataloader.author.Author;
import com.nitesh.cassandradataloader.author.AuthorRepository;
import com.nitesh.cassandradataloader.book.Book;
import com.nitesh.cassandradataloader.book.BookRepository;
import com.nitesh.cassandradataloader.connection.DataStaxAstraProperties;

@SpringBootApplication
@EnableConfigurationProperties(DataStaxAstraProperties.class)
public class CassandraDataLoaderApplication {

	@Autowired
	AuthorRepository authorRepository;
	
	@Autowired
	BookRepository bookRepository;

	@Value("${datadump.location.author}")
	private String authorDumpLocation;

	@Value("${datadump.location.works}")
	private String workdDumpLocation;

	public static void main(String[] args) {
		SpringApplication.run(CassandraDataLoaderApplication.class, args);
	}

	private void initAuthors() {
		Path path = Paths.get(authorDumpLocation);
		try (Stream<String> lines = Files.lines(path)) {
			lines.forEach(line -> {
				String jsonString = line.substring(line.indexOf("{"));

				JSONObject jsonObject;
				try {
					jsonObject = new JSONObject(jsonString);
					Author author = new Author();
					author.setName(jsonObject.optString("name"));
					author.setPersonalName(jsonObject.optString("personal_name"));
					author.setId(jsonObject.optString("key").replace("/authors/", ""));
					authorRepository.save(author);

//					System.out.println("json : " + jsonObject);
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void initWorks() {
		Path path = Paths.get(workdDumpLocation);
		try (Stream<String> lines = Files.lines(path)) {
			lines.forEach(line -> {

				String jsonString = line.substring(line.indexOf("{"));

				JSONObject jsonObject;
				try {
					jsonObject = new JSONObject(jsonString);

					Book book = new Book();
					book.setId(jsonObject.getString("key").replace("/works/", ""));
					book.setName(jsonObject.optString("title"));
					JSONObject descriptionObj = jsonObject.optJSONObject("description");
					if (descriptionObj != null) {
						book.setDescription(descriptionObj.optString("value"));
					}
					JSONObject publishedObj = jsonObject.optJSONObject("created");
					if (publishedObj != null) {
						DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS");
						String dateStr = publishedObj.getString("value");
						book.setPublishedDate(LocalDate.parse(dateStr,dateFormat));
					}
					JSONArray coversJSONArr = jsonObject.optJSONArray("covers");
					if (coversJSONArr != null) {
						List<String> coverIds = new ArrayList<>();
						for (int i = 0; i < coversJSONArr.length(); i++) {
							coverIds.add(String.valueOf(coversJSONArr.getInt(i)));
						}
						
						book.setCoverIds(coverIds);
					}

					JSONArray authorsJSONArr = jsonObject.getJSONArray("authors");
					if (authorsJSONArr != null) {
						List<String> authorIds = new ArrayList<>();
						for (int i = 0; i < authorsJSONArr.length(); i++) {
							authorIds.add(authorsJSONArr.getJSONObject(i).getJSONObject("author").getString("key")
									.replace("/authors/", ""));
						}
						book.setAuthorId(authorIds);
						List<String> authorNames = authorIds.stream().map(id -> authorRepository.findById(id))
								.map(optionalAuthor -> {
									if (!optionalAuthor.isPresent())
										return "Author not found!";
									return optionalAuthor.get().getName();
								}).collect(Collectors.toList());
						book.setAuthorNames(authorNames);
					}

					bookRepository.save(book);
					
				} catch (JSONException e) {
					e.printStackTrace();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@PostConstruct
	public void start() {
//		Author author=new Author();
//		author.setId("id");
//		author.setName("name");
//		author.setPersonalName("personalName");
//		authorRepository.save(author);

//		initAuthors();
		initWorks();
		System.out.println("postConstruct Start ::: " + authorDumpLocation);
	}

	/**
	 * This is necessary to have the Spring Boot app use the Astra secure bundle to
	 * connect to the database
	 */

	@Bean
	public CqlSessionBuilderCustomizer sessionBuilderCustomizer(DataStaxAstraProperties astraProperties) {
		Path bundle = astraProperties.getSecureConnectBundle().toPath();
		return builder -> builder.withCloudSecureConnectBundle(bundle);
	}

}
