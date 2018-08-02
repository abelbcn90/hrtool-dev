package com.wedonegood.permit;

import java.util.List;

public interface PermitService {
	List<Permit> getPermits();
	Permit get(final Long permitId);
}
