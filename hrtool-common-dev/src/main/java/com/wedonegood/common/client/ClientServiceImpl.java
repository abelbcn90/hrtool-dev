package com.wedonegood.common.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {
	
	@Autowired
    private ClientRepository clientRepository;
	
	@Override
    public Client get(final Long clientId) {
        return this.clientRepository.getOne(clientId);
    }
}
