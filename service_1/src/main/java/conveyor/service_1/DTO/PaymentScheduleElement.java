package conveyor.service_1.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;
/**дто графика ежемесячный платежей*/
@Data
@Accessors(chain = true)
@Schema(name = "PaymentScheduleElement", description = "dto on monthly payment schedule")
public class PaymentScheduleElement {
    @Schema(description = "number")
    Integer number;

    @Schema(description = "date")
    LocalDate date;

    @Schema(description = "totalPayment")
    BigDecimal totalPayment;   //всего к оплате

    @Schema(description = "interestPayment")
    BigDecimal interestPayment; //Выплата процентов

    @Schema(description = "debtPayment")
    BigDecimal debtPayment;    //долг

    @Schema(description = "remainingDebt")
    BigDecimal remainingDebt;  //остаток долга
}
