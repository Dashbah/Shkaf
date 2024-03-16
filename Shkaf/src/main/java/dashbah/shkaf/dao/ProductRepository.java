package dashbah.shkaf.dao;

import dashbah.shkaf.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
