package dev.eguzmanjr.contentcalendar.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import dev.eguzmanjr.contentcalendar.model.Content;
import dev.eguzmanjr.contentcalendar.repository.ContentCollectionRepository;

@RestController
@RequestMapping("/api/content")
public class ContentController {

	private final ContentCollectionRepository repository;

	public ContentController(ContentCollectionRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("")
	public List<Content> findAll() {
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public Content findById(@PathVariable Integer id) {
		return repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
	}
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping("")
	public void create(@RequestBody Content content) {
		repository.save(content);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PutMapping("/{id}")
	public void update(@RequestBody Content content, @PathVariable Integer id) {
		if (!repository.existById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		repository.save(content);
	}
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Integer id) {
		if (!repository.existById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		repository.delete(id);
	}
}
