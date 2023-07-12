package org.zerock.j2.service;

import org.springframework.stereotype.Service;
import org.zerock.j2.dto.*;
import org.zerock.j2.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

  private final ProductRepository productRepository;

  @Override
  public PageResponseDTO<ProductListDTO> list(PageRequestDTO requestDTO) {

    return productRepository.listWithReview(requestDTO);

  }
  
}
