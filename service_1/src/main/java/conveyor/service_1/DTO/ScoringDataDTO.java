package conveyor.service_1.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;
/**Дто скоринга*/
@Data
@Accessors(chain = true)
@Schema(name = "ScoringDataDTO", description = "Dto scoring")
public class ScoringDataDTO {
    @Schema(description = "amount")
    BigDecimal amount;

    @Schema(description = "term")
    Integer term; //срок

    @Schema(description = "firstName")
    String firstName;

    @Schema(description = "lastName")
    String lastName;

    @Schema(description = "middleName;")
    String middleName;

    @Schema(description = "gender")
    Enum gender;

    @Schema(description = "birthdate")
    LocalDate birthdate;

    @Schema(description = "passportSeries")
    String passportSeries;

    @Schema(description = "passportNumber")
    String passportNumber;

    @Schema(description = "passportIssueDate")
    LocalDate passportIssueDate;

    @Schema(description = "passportIssueBranch")
    String passportIssueBranch;

    @Schema(description = "maritalStatus")
    Enum maritalStatus;

    @Schema(description = "dependentAmount")
    Integer dependentAmount;

    @Schema(description = "employment")
    EmploymentDTO employment;

    @Schema(description = "account")
    String account;

    @Schema(description = "isInsuranceEnabled")
    Boolean isInsuranceEnabled;

    @Schema(description = "isSalaryClient")
    Boolean isSalaryClient;
}
