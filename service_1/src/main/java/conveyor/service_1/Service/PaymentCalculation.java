package conveyor.service_1.Service;

import conveyor.service_1.DTO.ScoringDataDTO;

import java.math.BigDecimal;

public interface PaymentCalculation {

    BigDecimal getMonthlyPayment(ScoringDataDTO scoringDataDTO);

    BigDecimal getInsurance(ScoringDataDTO scoringDataDTO);

    BigDecimal getTotalAmount(ScoringDataDTO scoringDataDTO);

    BigDecimal getPsk(ScoringDataDTO scoringDataDTO);

    BigDecimal getRate(ScoringDataDTO scoringDataDTO);
}
