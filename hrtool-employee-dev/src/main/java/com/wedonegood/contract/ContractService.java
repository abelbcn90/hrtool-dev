package com.wedonegood.contract;

import java.util.List;

public interface ContractService {
	List<Contract> getContracts();
	Contract get(final Long contractId);
}
