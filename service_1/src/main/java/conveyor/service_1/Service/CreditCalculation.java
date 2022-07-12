package conveyor.service_1.Service;

import conveyor.service_1.DTO.CreditDTO;
import conveyor.service_1.DTO.ScoringDataDTO;

public interface CreditCalculation {

    CreditDTO getCalculatedOffers(ScoringDataDTO scoringDataDTO);
}

