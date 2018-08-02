package com.wedonegood.contract;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContractServiceImpl implements ContractService {
	
	@Autowired
    private ContractRepository contractRepository;
	
	@Override
	public List<Contract> getContracts() {
		return this.contractRepository.findAll();
	}
	
	@Override
    public Contract get(final Long contractId) {
        return this.contractRepository.getOne(contractId);
    }
}
