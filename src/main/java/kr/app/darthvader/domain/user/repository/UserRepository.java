package kr.app.darthvader.domain.user.repository;

import kr.app.darthvader.domain.user.model.entity.Tuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Tuser, Long>, UserRepositoryCustom {

    int countByUserId(String userId);

    Optional<Tuser> findByUserId(String userId);

}
