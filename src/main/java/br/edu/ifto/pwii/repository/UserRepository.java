package br.edu.ifto.pwii.repository;

import br.edu.ifto.pwii.model.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {
}
