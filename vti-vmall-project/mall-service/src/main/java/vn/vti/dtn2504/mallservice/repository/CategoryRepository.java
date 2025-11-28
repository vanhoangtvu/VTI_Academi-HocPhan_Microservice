package vn.vti.dtn2504.mallservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.vti.dtn2504.mallservice.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
