package edu.sru.thangiah.group2.fall2023registrationsystem.controller;

import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Parent;
import edu.sru.thangiah.group2.fall2023registrationsystem.domain.Transactions;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.ParentRepository;
import edu.sru.thangiah.group2.fall2023registrationsystem.repository.TransactionRepository;

@Controller
public class TransactionController {

	@Autowired
	private ParentRepository parentRepository;
	@Autowired
	private TransactionRepository transactionRepository;

	// display payment form
	@GetMapping("/payment")
	public String showPaymentForm(Model model, @RequestParam Integer parentID) {
		// retrieve the parent details by parentID
		Parent parent = parentRepository.findById(parentID).orElse(null);
		model.addAttribute("parent", parent);
		return "payment";
	}

	// process the payment
	@PostMapping("/processPayment")
	public String processPayment(@RequestParam(required = false) Integer transactionID,
			@RequestParam String paymentType, @RequestParam Float amount,
			@RequestParam(required = false) String checkNumber,
			@RequestParam(required = false) LocalDateTime transactionTime, @RequestParam("parentID") Parent parentID) {

		// retrieve the parent details by parentID
		Parent parent = parentRepository.findById(parentID.getParentID()).orElse(null);
		if (parent != null) {
            // create new transaction
			Transactions transaction = new Transactions();
			transaction.setTransactionID(transactionID);
			transaction.setParent(parent);
			transaction.setPaymentType(paymentType);
			transaction.setAmount(amount);
			transaction.setCheckNumber(checkNumber);

			// update the parent's balance
			Float currentBalance = parent.getBalance();
			if (currentBalance != null) {
				// subtracts the paid amount
				Float updatedBalance = currentBalance - amount; 
				parent.setBalance(updatedBalance);
			} else {
				parent.setBalance(0.0f);

				return "paymentError";
			}

            // update transaction time + save transaction and parent details
			LocalDateTime transactTime = transaction.getTransactionTime();
			transaction.setTransactionTime(LocalDateTime.now());

			transactionRepository.save(transaction);
			parentRepository.save(parent);

			return "paymentSuccess";
		}
		return "paymentError";
	}

    // display the parent payment form
	@GetMapping("/parentPayment")
	public String parentPaymentForm(Model model) {
		return "parentPayment";
	}
}
