package com.wedonegood.permit;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PermitRepository extends JpaRepository<Permit, Long> {
	
}
