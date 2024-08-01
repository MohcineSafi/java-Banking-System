package loan;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Loan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String accountId;
    private double amount;
    private double interestRate;
    private LocalDateTime dueDate;
    private boolean isRepaid;

    // Constructors, getters, and setters
}
