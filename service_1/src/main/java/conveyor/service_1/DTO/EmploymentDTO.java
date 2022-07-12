package conveyor.service_1.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
/**Класс описывающий опыт работы(трудоустроуства)*/
@Data
@Accessors(chain = true)
@Schema(name = "EmploymentDTO", description = "contains information about the substitute")
public class EmploymentDTO {
    @Schema(description = "employmentStatus")
    Enum employmentStatus;
    @Schema(description = "employerINN")
    String employerINN;
    @Schema(description = "salary")
    BigDecimal salary;
    @Schema(description = "job title")
    Enum position;
    @Schema(description = "workExperienceTotal")
    Integer workExperienceTotal;
    @Schema(description = "workExperienceCurrent")
    Integer workExperienceCurrent;
}

