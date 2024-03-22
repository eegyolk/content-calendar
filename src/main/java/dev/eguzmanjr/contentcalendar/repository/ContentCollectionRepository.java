package dev.eguzmanjr.contentcalendar.repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import dev.eguzmanjr.contentcalendar.model.Content;
import dev.eguzmanjr.contentcalendar.model.Status;
import dev.eguzmanjr.contentcalendar.model.Type;
import jakarta.annotation.PostConstruct; 

@Repository
public class ContentCollectionRepository {

	private final List<Content> contentList = new ArrayList<>();

	public ContentCollectionRepository() {
		
	}
	
	public List<Content> findAll() {
		return contentList;
	}
	
	public Optional<Content> findById(Integer id) {
		return contentList.stream().filter(c -> c.id().equals(id)).findFirst();
	}
	
	public void save(Content content) {
		contentList.removeIf(c  -> c.id().equals(content.id()));
		contentList.add(content);
		
	}
	
	public boolean existById(Integer id) {
		return contentList.stream().filter(c -> c.id().equals(id)).count() == 1 ? true : false;
	}
	
	public void delete(Integer id) {
		contentList.removeIf(c -> c.id().equals(id));
	}
	
	@PostConstruct
	private void init() {
		Content c = new Content(1,
				"My First Java Spring Boot App",
				"My first java spring boot application",
				Status.IDEA,
				Type.ARTICLE,
				LocalDateTime.now(),
				null,
				"");
		
		contentList.add(c);
	}
}
