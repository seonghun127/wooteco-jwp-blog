package techcourse.myblog.user.domain;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<techcourse.myblog.user.domain.User, Long> {
    Optional<techcourse.myblog.user.domain.User> findByEmail(String email);
}