package org.zerock.mallapi.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.mallapi.domain.Product;

import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

@SpringBootTest
@Log4j2
class ProductRepositoryTests {

    @Autowired
    ProductRepository productRepository;

    @Test
    public void testInsert() {
        IntStream.range(0, 10).forEach(i -> {
            Product product = Product.builder()
                    .pname("상품" + i)
                    .price(100 * i)
                    .pdesc("상품설명 " + i)
                    .build();

            // 2개의 이미지 파일 추가
            product.addImageString(UUID.randomUUID().toString() + "_" + "IMAGE1.jpg");
            product.addImageString(UUID.randomUUID().toString() + "_" + "IMAGE2.jpg");

            productRepository.save(product);

            log.info("------------------------------");
        });
    }

    @Transactional      // 트랜잭션이 없는 상태에서 지연 로딩을 시도하면 LazyInitializationException이 발생
    @Test
    public void testRead() {
        Long pno = 1L;

        Optional<Product> result = productRepository.findById(pno);
        Product product = result.orElseThrow();

        log.info(product);
        log.info(product.getImageList());
    }

    @Test
    public void testRead2() {
        Long pno = 1L;

        Optional<Product> result = productRepository.selectOne(pno);
        Product product = result.orElseThrow();

        log.info(product);
        log.info(product.getImageList());
    }

    @Commit
    @Transactional
    @Test
    public void testDelete() {
        Long pno = 5L;
        productRepository.updateToDelete(pno, true);
    }

}