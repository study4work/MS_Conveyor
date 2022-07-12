package conveyor.service_1.Service;

import conveyor.service_1.DTO.PaymentScheduleElement;
import conveyor.service_1.DTO.ScoringDataDTO;

import java.util.List;

public interface PaymentSchedule {

    List<PaymentScheduleElement> schedule(ScoringDataDTO scoringDataDTO);
}


