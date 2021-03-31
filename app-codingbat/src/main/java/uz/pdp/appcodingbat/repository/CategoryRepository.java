package uz.pdp.appcodingbat.repository;

import org.hibernate.metamodel.model.convert.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.appcodingbat.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
}
