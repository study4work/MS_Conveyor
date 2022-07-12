package conveyor.service_1.Service.impl;

import conveyor.service_1.DTO.EmploymentDTO;
import conveyor.service_1.DTO.PaymentScheduleElement;
import conveyor.service_1.DTO.ScoringDataDTO;
import conveyor.service_1.Service.PaymentSchedule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class PaymentScheduleImplTest {

    @Autowired
    private PaymentSchedule paymentSchedule;

    private enum maritalStatus {
        MARRIED;
    }

    private enum employmentStatus {
        SELF_EMPLOYED;
    }

    private enum position {
        TOP_MANAGER;
    }

    private enum gender {
        MAN;
    }

    private EmploymentDTO employmentDTO = new EmploymentDTO()
            .setEmploymentStatus(PaymentScheduleImplTest.employmentStatus.SELF_EMPLOYED)
            .setEmployerINN("324245")
            .setSalary(new BigDecimal("400000"))
            .setPosition(PaymentScheduleImplTest.position.TOP_MANAGER)
            .setWorkExperienceTotal(19)
            .setWorkExperienceCurrent(14);

    //insurance enable
    private ScoringDataDTO scoringDataDTO = new ScoringDataDTO()
            .setAmount(new BigDecimal("100000"))
            .setTerm(12)
            .setFirstName("Ivan")
            .setLastName("Ivanov")
            .setMiddleName("Ivanovich")
            .setGender(PaymentScheduleImplTest.gender.MAN)
            .setBirthdate(LocalDate.of(1993, 02, 03))
            .setPassportIssueDate(LocalDate.of(2005, 3, 13))
            .setPassportIssueBranch("УФМС России по Воронежской обл. центрального р-а")
            .setMaritalStatus(PaymentScheduleImplTest.maritalStatus.MARRIED)
            .setDependentAmount(3)
            .setEmployment(employmentDTO)
            .setAccount("account")
            .setIsInsuranceEnabled(true)
            .setIsSalaryClient(true);

    @Test
    void scheduleDebtPayment() {
        List<PaymentScheduleElement> result= paymentSchedule.schedule(scoringDataDTO);
        PaymentScheduleElement elemRes=result.get(2);
        BigDecimal elemExpec= BigDecimal.valueOf(9418.8395).setScale(4, RoundingMode.HALF_EVEN);
        assertEquals(elemExpec,elemRes.getDebtPayment());
    }

    @Test
    void scheduleInterestPayment() {
        List<PaymentScheduleElement> result= paymentSchedule.schedule(scoringDataDTO);
        PaymentScheduleElement elemRes=result.get(2);
        BigDecimal elemExpec= BigDecimal.valueOf(8.5).setScale(1, RoundingMode.HALF_EVEN);
        assertEquals(elemExpec,elemRes.getInterestPayment());
    }

    @Test
    void scheduleTotalPayment() {
        List<PaymentScheduleElement> result= paymentSchedule.schedule(scoringDataDTO);
        PaymentScheduleElement elemRes=result.get(2);
        BigDecimal elemExpec= BigDecimal.valueOf(113026.0740).setScale(4, RoundingMode.HALF_EVEN);
        assertEquals(elemExpec,elemRes.getTotalPayment());
    }
}