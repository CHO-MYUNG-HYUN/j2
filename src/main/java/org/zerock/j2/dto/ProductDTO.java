package org.zerock.j2.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

  private Long pno;

  private String pname;

  private String pdesc;

  private int price;

  // 데이터베이스로 보내서 처리할 용도
  private List<String> images;

  // 등록,수정 업로드된 파일 데이터를 수집하는 용도
  private List<MultipartFile> files;
  
}
