package edu.sru.thangiah.group2.fall2023registrationsystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Parent;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Transactions;

//repository interface
public interface TransactionRepository extends JpaRepository<Transactions, Integer> {
	
	// find transactions by parentID
	@Query("SELECT t FROM Transactions t WHERE t.parent.parentID = :parentID")
	List<Transactions> findByParentID(@Param("parentID") Integer parentID);
}