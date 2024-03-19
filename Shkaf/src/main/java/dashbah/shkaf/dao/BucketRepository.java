package dashbah.shkaf.dao;

import dashbah.shkaf.model.Bucket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BucketRepository extends JpaRepository<Bucket, Long> {
}
