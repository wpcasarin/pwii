package br.edu.ifto.pwii.service;

import br.edu.ifto.pwii.model.User;
import br.edu.ifto.pwii.repository.BaseRepository;
import br.edu.ifto.pwii.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseServiceImpl<User, Long> {

    public UserService(BaseRepository<User, Long> repository, UserRepository userRepository) {
        super(repository);
    }
}
