package edu.sru.thangiah.group2.fall2023registrationsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Parent;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Transactions;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.ParentRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.TransactionRepository;

@Service
public class ParentService {

	private final ParentRepository parentRepo;
	@Autowired
	private TransactionRepository transactionRepo;

	@Autowired
	public ParentService(ParentRepository parentRepo) {
		this.parentRepo = parentRepo;
	}

	// finds parent by email
	public Parent findParentByEmail(String email) {
		return parentRepo.findByEmail(email);
	}

	// gets list of transactions by parentID
	public List<Transactions> getParentTransactions(Integer parentID) {
		return transactionRepo.findByParentID(parentID);
	}
}