package com.magiamensagem.magiasystem.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.magiamensagem.magiasystem.dto.ProductDTO;
import com.magiamensagem.magiasystem.entities.Product;
import com.magiamensagem.magiasystem.repositories.ProductRepository;
import com.magiamensagem.magiasystem.services.exceptions.DatabaseException;
import com.magiamensagem.magiasystem.services.exceptions.ResourceNotFoundException;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Transactional(readOnly = true)
	public Page<ProductDTO> findAllPaged(PageRequest pageRequest){
		Page<Product> list = productRepository.findAll(pageRequest);
		
		return list.map(obj -> new ProductDTO(obj));
	}
	
	@Transactional(readOnly = true)
	public ProductDTO findById(Long id) {
		Optional<Product> obj = productRepository.findById(id);
		Product entity =  obj.orElseThrow(() -> new ResourceNotFoundException("Produto não existe! ID = "+ id));
		return new ProductDTO(entity);
	}
	
	@Transactional
	public ProductDTO insert(ProductDTO dto) {
		
		Product entity = new Product();
		dtoToEntity(entity, dto);
		entity = productRepository.save(entity);
		
		return new ProductDTO(entity);
	}
	
	@Transactional
	public ProductDTO update(ProductDTO dto, Long id) {
		try {
			Product entity = productRepository.getOne(id);
			dtoToEntity(entity, dto);
			entity = productRepository.save(entity);
			
			return new ProductDTO(entity);
		}catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Produto de ID "+id+" não existe!");
		}
	}
	
	public void delete(Long id) {
		try {
			productRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {

			throw new ResourceNotFoundException("Produto com ID " + id +" não encontrado!");
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Integrity violation");
		}
	}

		private void dtoToEntity(Product entity, ProductDTO dto) {
		entity.setDescription(dto.getDescription());
		entity.setName(dto.getName());
		entity.setPrice(dto.getPrice());
		
	}

}
