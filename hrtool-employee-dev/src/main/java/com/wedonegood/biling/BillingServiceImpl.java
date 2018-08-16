package com.wedonegood.biling;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillingServiceImpl implements BillingService {
	
	@Autowired
    private BillingRepository bilingRepository;
	
	@Override
	public List<Billing> getBilings() {
		return this.bilingRepository.findAll();
	}
	
	@Override
    public Billing get(final Long bilingId) {
        return this.bilingRepository.getOne(bilingId);
    }
}
