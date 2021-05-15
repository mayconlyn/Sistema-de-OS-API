package com.magiamensagem.magiasystem.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.magiamensagem.magiasystem.dto.ClientDTO;
import com.magiamensagem.magiasystem.dto.ClientResponseDTO;
import com.magiamensagem.magiasystem.services.ClientService;

@RestController
@RequestMapping("/clients")
public class ClientResource {
	
	@Autowired
	private ClientService service;
	
	@GetMapping
	public ResponseEntity<Page<ClientDTO>> findAllPaged(@RequestParam(name = "page", defaultValue = "0") Integer page, 
			@RequestParam(name = "linesPerPage", defaultValue = "12")Integer linesPerPage, 
			@RequestParam(name = "direction", defaultValue = "ASC") String direction, 
			@RequestParam(name = "orderBy", defaultValue = "id") String orderBy){
		
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Page<ClientDTO> list = service.findAllPaged(pageRequest);
		
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientResponseDTO> findById(@PathVariable Long id){
		
		ClientResponseDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}

}
