package conveyor.service_1.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

/**Класс дто-кредит*/
@Data
@Accessors(chain = true) //позволяет создавать объект через сетеры в стиле билдера
@Schema(name = "CreditDTO", description = "contains information about the loan")
public class CreditDTO {
    @Schema(description = "amount")
    BigDecimal amount;
    @Schema(description = "term")
    Integer term;
    @Schema(description = "monthlyPayment")
    BigDecimal monthlyPayment;
    @Schema(description = "rate")
    BigDecimal rate;
    @Schema(description = "the total cost of the loan(psk)")
    BigDecimal psk;
    @Schema(description = "isInsuranceEnabled;")
    Boolean isInsuranceEnabled;
    @Schema(description = "isSalaryClient")
    Boolean isSalaryClient;
    @Schema(description = "paymentSchedule")
    List<PaymentScheduleElement> paymentSchedule;

}