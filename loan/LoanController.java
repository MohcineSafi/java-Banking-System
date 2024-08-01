package loan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {
    @Autowired
    private LoanService loanService;

    @PostMapping("/apply")
    public Loan applyForLoan(@RequestBody Loan loan) {
        return loanService.applyForLoan(loan);
    }

    @PostMapping("/approve/{id}")
    public Loan approveLoan(@PathVariable Long id) {
        return loanService.approveLoan(id);
    }

    @PostMapping("/repay/{id}")
    public void repayLoan(@PathVariable Long id) {
        loanService.repayLoan(id);
    }

    @GetMapping("/all")
    public List<Loan> getAllLoans() {
        return loanService.getAllLoans();
    }
}
