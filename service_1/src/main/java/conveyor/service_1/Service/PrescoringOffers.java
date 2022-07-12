package conveyor.service_1.Service;

import conveyor.service_1.DTO.LoanApplicationRequestDTO;
import conveyor.service_1.DTO.LoanOfferDTO;

import java.math.BigDecimal;
import java.util.List;

public interface PrescoringOffers {
    BigDecimal getMonthlyPayment(BigDecimal amount, BigDecimal rate, Integer term, boolean insurence);

    BigDecimal getInsurance(BigDecimal amount);

    BigDecimal getTotalAmount(BigDecimal amount, BigDecimal rate, Integer term, boolean insurence);

    List<LoanOfferDTO> getPrescoringOffers(LoanApplicationRequestDTO loanApplicationRequestDTO);
}
