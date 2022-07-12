package conveyor.service_1.Controller;

import conveyor.service_1.DTO.CreditDTO;
import conveyor.service_1.DTO.LoanApplicationRequestDTO;
import conveyor.service_1.DTO.LoanOfferDTO;
import conveyor.service_1.DTO.ScoringDataDTO;
import conveyor.service_1.Service.CreditCalculation;
import conveyor.service_1.Service.PrescoringOffers;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Api("Controller for accepting and processing credit ")
@RestController( "/conveyor")
@Slf4j
public class Conveyor {

    private final PrescoringOffers prescoring;

    private final CreditCalculation creditCalculation;

    public Conveyor(PrescoringOffers prescoring, CreditCalculation creditCalculation) {
        this.prescoring = prescoring;
        this.creditCalculation = creditCalculation;
    }

    @ApiOperation("submitting a loan application and obtaining a calculation of possible loan conditions ")
    @PostMapping("/offers")
    public List<LoanOfferDTO> offers(LoanApplicationRequestDTO loanApplicationRequestDTO) {
        log.info("4 LoanOfferDTO для клиента");
        return prescoring.getPrescoringOffers(loanApplicationRequestDTO);
    }

    @ApiOperation("validation of submitted data + data scoring + full calculation of loan parameters")
    @PostMapping("/calculation")
    public CreditDTO calculation(ScoringDataDTO scoringDataDTO) {
        log.info("Полностью расчитанный CreditDTO");
        return creditCalculation.getCalculatedOffers(scoringDataDTO);
    }
}
