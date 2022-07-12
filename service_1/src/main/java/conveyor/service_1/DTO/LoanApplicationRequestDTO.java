package conveyor.service_1.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

/**Класс дто -Заявка на получение кредита*/
@Data
@Accessors(chain = true)
@Schema(name = "LoanApplicationRequestDTO", description = "contains information about the loan application")
public class LoanApplicationRequestDTO {
    @Schema(description = "amount")
    @Min(value = 10000)
    BigDecimal amount;  //сумма кредита

    @Schema(description = "term")
    @Min(value = 6)
    Integer term;  //срок

    @Schema(description = "firstName")
    @Size(min = 2, max = 30)
    String firstName;

    @Schema(description = "lastName")
    @Size(min = 2, max = 30)
    String lastName;

    @Schema(description = "middleName")
    @Size(min = 2, max = 30)
    String middleName;

    @Schema(description = "email")
    @Pattern(regexp = "[\\w\\.]{2,50}@[\\w\\.]{2,20}")
    String email;

    @Schema(description = "birthdate")
    @Past
    @Pattern(regexp = "((?:19|20)[0-9][0-9])-(0?[1-9]|1[012])-(0?[1-9]|[12][0-9]|3[01])")
    LocalDate birthdate;

    @Schema(description = "passportSeries")
    @Size(min = 4, max = 4)
    String passportSeries;

    @Schema(description = "passportNumber")
    @Size(min = 6, max = 6)
    String passportNumber;
}


