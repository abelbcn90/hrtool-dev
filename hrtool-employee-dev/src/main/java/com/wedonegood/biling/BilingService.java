package com.wedonegood.biling;

import java.util.List;

public interface BilingService {
	List<Biling> getBilings();
	Biling get(final Long bilingId);
}
