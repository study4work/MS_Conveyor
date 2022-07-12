package conveyor.service_1.Service.impl;

import conveyor.service_1.DTO.LoanApplicationRequestDTO;
import conveyor.service_1.DTO.LoanOfferDTO;
import conveyor.service_1.Service.PrescoringOffers;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Slf4j
public class PrescoringOffersImpl implements PrescoringOffers {
    @Value("${conveyor.loanDTO.rate}")
    private BigDecimal rate;

    private Long id= 0L;

    /*
    Логика прескоринга. Базовая ставка 10. Страховка 1000.
    Базовая ставка уменьшается если сумма кредита больше 1кк на 1п., больше 10кк на 2п.,
    измененная базовая ставка работает только для зарплатных клиентов.

     */
    public BigDecimal getMonthlyPayment(BigDecimal amount, BigDecimal rate, Integer term, boolean insurence) {
        BigDecimal monthlyRate = BigDecimal.valueOf((rate.doubleValue() * 100) / (100 * 12)).setScale(4, RoundingMode.HALF_EVEN);
        if (insurence) {
            return BigDecimal.valueOf((amount.doubleValue() + getInsurance(amount).doubleValue())
                    * (monthlyRate.doubleValue() / (1 - Math.pow((1 + monthlyRate.doubleValue()), -term)))).setScale(4, RoundingMode.HALF_EVEN);
        }
        return BigDecimal.valueOf(amount.doubleValue()
                * (monthlyRate.doubleValue() / (1 - Math.pow((1 + monthlyRate.doubleValue()), -term)))).setScale(4, RoundingMode.HALF_EVEN);
    }

    public BigDecimal getInsurance(BigDecimal amount) {
        return amount.divide(BigDecimal.valueOf(100),4, RoundingMode.HALF_EVEN).multiply(BigDecimal.valueOf(10));
    }

    public BigDecimal getTotalAmount(BigDecimal amount, BigDecimal rate, Integer term, boolean insurence) {
        return getMonthlyPayment(amount, rate, term, insurence).multiply(new BigDecimal(term));
    }

    @Override
    public List<LoanOfferDTO> getPrescoringOffers(LoanApplicationRequestDTO loanDTO) {

        List<LoanOfferDTO> result = new ArrayList<>(4);

        if(loanDTO.getAmount().intValue() > 1000000) {
            rate = new BigDecimal("0.9");
        }
        if(loanDTO.getAmount().intValue() > 10000000) {
            rate = new BigDecimal("0.8");
        }
        log.info("Перскоринг LoanRequestDTO, возвращение List<LoanOfferDTO>");
        result.add(new LoanOfferDTO()
                .setApplicationId(id++)
                .setRequestedAmount(loanDTO.getAmount())
                .setTotalAmount(getTotalAmount(loanDTO.getAmount().setScale(4, RoundingMode.HALF_EVEN), this.rate, loanDTO.getTerm(), false))
                .setTerm(loanDTO.getTerm())
                .setMonthlyPayment(getMonthlyPayment(loanDTO.getAmount(), this.rate, loanDTO.getTerm(), false))
                .setRate(this.rate)
                .setIsInsuranceEnabled(false)
                .setIsSalaryClient(false));

        result.add(new LoanOfferDTO()
                .setApplicationId(id++)
                .setRequestedAmount(loanDTO.getAmount())
                .setTotalAmount(getTotalAmount(loanDTO.getAmount().setScale(4, RoundingMode.HALF_EVEN), rate, loanDTO.getTerm(), true)
                        .add(getInsurance(loanDTO.getAmount())))
                .setTerm(loanDTO.getTerm())
                .setMonthlyPayment(getMonthlyPayment(loanDTO.getAmount(), rate, loanDTO.getTerm(), true))
                .setRate(this.rate)
                .setIsInsuranceEnabled(true)
                .setIsSalaryClient(false));

        result.add(new LoanOfferDTO()
                .setApplicationId(id++)
                .setRequestedAmount(loanDTO.getAmount())
                .setTotalAmount(getTotalAmount(loanDTO.getAmount().setScale(4, RoundingMode.HALF_EVEN), rate, loanDTO.getTerm(), true)
                        .add(getInsurance(loanDTO.getAmount())))
                .setTerm(loanDTO.getTerm())
                .setMonthlyPayment(getMonthlyPayment(loanDTO.getAmount(), rate, loanDTO.getTerm(), true))
                .setRate(rate)
                .setIsInsuranceEnabled(true)
                .setIsSalaryClient(true));

        result.add(new LoanOfferDTO()
                .setApplicationId(id++)
                .setRequestedAmount(loanDTO.getAmount())
                .setTotalAmount(getTotalAmount(loanDTO.getAmount().setScale(4, RoundingMode.HALF_EVEN), this.rate, loanDTO.getTerm(), false))
                .setTerm(loanDTO.getTerm())
                .setMonthlyPayment(getMonthlyPayment(loanDTO.getAmount(), this.rate, loanDTO.getTerm(), false))
                .setRate(rate)
                .setIsInsuranceEnabled(false)
                .setIsSalaryClient(true));

        result.sort(Comparator.comparing(LoanOfferDTO::getRate).reversed());

        return result;
    }
}

