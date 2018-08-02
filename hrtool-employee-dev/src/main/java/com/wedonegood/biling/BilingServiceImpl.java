package com.wedonegood.biling;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BilingServiceImpl implements BilingService {
	
	@Autowired
    private BilingRepository bilingRepository;
	
	@Override
	public List<Biling> getBilings() {
		return this.bilingRepository.findAll();
	}
	
	@Override
    public Biling get(final Long bilingId) {
        return this.bilingRepository.getOne(bilingId);
    }
}
