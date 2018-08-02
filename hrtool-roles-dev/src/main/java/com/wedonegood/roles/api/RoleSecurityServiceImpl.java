package com.wedonegood.roles.api;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wedonegood.roles.api.model.entity.Role;

@Service
public class RoleSecurityServiceImpl implements RoleSecurityService {

//    @Autowired
//    private ClientService clientService;

//    @Autowired
//    private DocumentSetService documentSetService;

    @Autowired
    private HttpServletRequest request;

//    @Override
//    public Client getCurrentClient() {
////        String clientId = request.getHeader("X-Auth-Client");
//        // TODO replace to auth
//        
//        String clientId = String.valueOf(1);
//        
//        if(clientId == null) {
//            return null;
//        }
//        return clientService.getClient(Long.parseLong(clientId));
//    }

    @Override
    public String getCurrentUser() {
        String user = request.getHeader("X-Auth-User");
//        return user != null ? user : "John Doe";
        return user != null ? user : "guest";
    }

//    @Override
//    public boolean hasAccess(DocumentSet documentSet) {
//        Client client = getCurrentClient();
//        if(client == null || documentSet == null) {
//            return false;
//        }
//        return hasAccess(client, documentSet);
//    }

//    public boolean hasAccess(Client client, DocumentSet documentSet) {
////        return documentSet.getId().compareTo(client.getId()) == 0;
//    	return true;
//    }

//    @Override
//    public boolean hasAccess(Document document) {
//        Client client = getCurrentClient();
//        if(client == null || document == null) {
//            return false;
//        }
//        DocumentSet documentSet = documentSetService.get(document.getDocumentSet().getId());
//        return hasAccess(client, documentSet);
//    }
    
    @Override
    public boolean hasAccess(final Role role) {
//    	Client client = getCurrentClient();
//    	if(client == null || document == null) {
//    		return false;
//    	}
//    	DocumentSet documentSet = documentSetService.get(document.getDocumentSet().getId());
//    	return hasAccess(client, documentSet);
    	
    	return true;
    }

}
