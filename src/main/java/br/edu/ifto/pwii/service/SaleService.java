package br.edu.ifto.pwii.service;

import br.edu.ifto.pwii.model.Sale;
import br.edu.ifto.pwii.repository.BaseRepository;
import org.springframework.stereotype.Service;

@Service
public class SaleService extends BaseServiceImpl<Sale, Long> {

    public SaleService(BaseRepository<Sale, Long> repository) {
        super(repository);
    }
}
