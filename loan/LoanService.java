package loan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanService {
    @Autowired
    private LoanRepository loanRepository;

    public Loan applyForLoan(Loan loan) {
        return loanRepository.save(loan);
    }

    public Loan approveLoan(Long loanId) {
        Loan loan = loanRepository.findById(loanId).orElse(null);
        if (loan != null) {
            loan.setRepaid(false);
            return loanRepository.save(loan);
        }
        return null;
    }

    public void repayLoan(Long loanId) {
        Loan loan = loanRepository.findById(loanId).orElse(null);
        if (loan != null) {
            loan.setRepaid(true);
            loanRepository.save(loan);
        }
    }

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }
}
