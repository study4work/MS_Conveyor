package conveyor.service_1.Service.impl;

import conveyor.service_1.DTO.CreditDTO;
import conveyor.service_1.DTO.ScoringDataDTO;
import conveyor.service_1.Service.CreditCalculation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class CreditCalculationImpl implements CreditCalculation {

    private final PaymentCalculationImpl paymentCalculation;

    private final PaymentScheduleImpl paymentSchedule;

    public CreditCalculationImpl(PaymentCalculationImpl paymentCalculation, PaymentScheduleImpl paymentSchedule) {
        this.paymentCalculation = paymentCalculation;
        this.paymentSchedule = paymentSchedule;
    }

    @Override
    public CreditDTO getCalculatedOffers(ScoringDataDTO scoringDataDTO) {
        BigDecimal rate = paymentCalculation.getRate(scoringDataDTO);
        log.info("Создание CreditDTO наполненого всеми параметрами расчета");
        return new CreditDTO().setAmount(paymentCalculation.getTotalAmount(scoringDataDTO))
                .setTerm(scoringDataDTO.getTerm())
                .setMonthlyPayment(paymentCalculation.getMonthlyPayment(scoringDataDTO))
                .setRate(rate)
                .setPsk(paymentCalculation.getPsk(scoringDataDTO))
                .setIsInsuranceEnabled(scoringDataDTO.getIsInsuranceEnabled())
                .setIsSalaryClient(scoringDataDTO.getIsSalaryClient())
                .setPaymentSchedule(paymentSchedule.schedule(scoringDataDTO));
    }
}

