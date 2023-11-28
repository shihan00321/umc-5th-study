package umc.spring.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.Town;
import umc.spring.repository.TownRepository;

import java.util.Optional;

public interface TempCommandService {

    @Service
    @Transactional(readOnly = true)
    @RequiredArgsConstructor
    class TownQueryServiceImpl implements TownQueryService {
        private final TownRepository townRepository;
        @Override
        public Optional<Town> findTown(Long id) {
            return townRepository.findById(id);
        }
    }

    interface TownQueryService {
        Optional<Town> findTown(Long id);
    }
}
