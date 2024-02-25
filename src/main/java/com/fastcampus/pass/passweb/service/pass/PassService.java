package com.fastcampus.pass.passweb.service.pass;


import com.fastcampus.pass.passweb.repository.pass.Pass;
import com.fastcampus.pass.passweb.repository.pass.PassEntity;
import com.fastcampus.pass.passweb.repository.pass.PassModelMapper;
import com.fastcampus.pass.passweb.repository.pass.PassRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PassService {
    private final PassRepository passRepository;

    public PassService(PassRepository passRepository) {
        this.passRepository = passRepository;
    }

    public List<Pass> getPasses(final String userId) {
        final List<PassEntity> passEntities = passRepository.findByUserId(userId);
        return PassModelMapper.INSTANCE.map(passEntities);

    }

}