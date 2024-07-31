package kr.app.darthvader.domain.user.repository;

import kr.app.darthvader.domain.user.model.entity.Tuser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Tuser, Long>, UserRepositoryCustom {

    int countByUserId(String userId);

    Tuser findBySeq(Long seq);

    Tuser findByUserId(String userId);

}
