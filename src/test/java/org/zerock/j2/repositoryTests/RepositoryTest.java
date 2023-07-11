package org.zerock.j2.repositoryTests;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Commit;
import org.zerock.j2.dto.PageRequestDTO;
import org.zerock.j2.entity.FileBoard;
import org.zerock.j2.entity.FileBoardImage;
import org.zerock.j2.repository.FileBoardRepository;

import jakarta.transaction.Transactional;

@SpringBootTest
public class RepositoryTest {

  @Autowired
  FileBoardRepository repository;

  @Test
  public void insert() {

    for (int i = 0; i < 100; i++) {
      FileBoard fileBoard = FileBoard.builder()
          .title("AA")
          .content("BBB")
          .writer("writer00")
          .build();

      FileBoardImage img1 = FileBoardImage.builder()
          .uuid(UUID.randomUUID().toString())
          .fname("aaa.jpg")
          .build();

      FileBoardImage img2 = FileBoardImage.builder()
          .uuid(UUID.randomUUID().toString())
          .fname("bbb.jpg")
          .build();

      fileBoard.addImage(img1);
      fileBoard.addImage(img2);

      repository.save(fileBoard);

    }

  }

  @Commit
  @Transactional
  @Test
  public void testRemove() {

    Long bno = 1L;

    repository.deleteById(bno);

  }

  @Transactional
  @Test
  public void testRead() {

    Long bno = 204L;

    Optional<FileBoard> result = repository.findById(bno);

    FileBoard board = result.orElseThrow();

    System.out.println(board);

  }

  @Transactional
  @Test
  public void testList() {

    Pageable pagealbe = PageRequest.of(0, 10);

    Page<FileBoard> result = repository.findAll(pagealbe);

    // System.out.println(result);

    result.get().forEach(board -> {
      System.out.println(board);
      System.out.println(board.getImages());
    });

  }

  @Transactional
  @Test
  public void testListQueryDSL() {

    PageRequestDTO requestDTO = new PageRequestDTO();

    System.out.println(repository.list(requestDTO));

  }

  @Test
  public void testSelectOne() {

    Long bno = 204L;

    FileBoard board = repository.selectOne(bno);

    System.out.println(board);
    System.out.println(board.getImages());

  }

  @Commit
  @Transactional
  @Test
  public void testDelete() {

    Long bno = 203L;

    repository.deleteById(bno);

  }

  @Commit
  @Transactional
  @Test
  public void testUpdate() {

    Optional<FileBoard> result = repository.findById(205L);

    FileBoard board = result.orElseThrow();

    board.clearImages();

    FileBoardImage img1 = FileBoardImage.builder()
        .uuid(UUID.randomUUID().toString())
        .fname("ZZZZZ.jpg")
        .build();

    board.addImage(img1);
    
    repository.save(board);

  }

}
