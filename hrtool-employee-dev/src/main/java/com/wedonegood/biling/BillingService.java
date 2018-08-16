package com.wedonegood.biling;

import java.util.List;

public interface BillingService {
	List<Billing> getBilings();
	Billing get(final Long bilingId);
}
