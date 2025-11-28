package vn.vti.dtn2504.usermanager.repository;

import org.bouncycastle.jcajce.provider.asymmetric.rsa.CipherSpi;
import org.springframework.data.jpa.repository.JpaRepository;
import vn.vti.dtn2504.usermanager.entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

}
