package conveyor.service_1.Service.impl;

import conveyor.service_1.DTO.CreditDTO;
import conveyor.service_1.DTO.EmploymentDTO;
import conveyor.service_1.DTO.PaymentScheduleElement;
import conveyor.service_1.DTO.ScoringDataDTO;
import conveyor.service_1.Service.CreditCalculation;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@RunWith(SpringRunner.class)
class CreditCalculationImplTest {

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
            .setEmploymentStatus(CreditCalculationImplTest.employmentStatus.SELF_EMPLOYED)
            .setEmployerINN("324245")
            .setSalary(new BigDecimal("400000"))
            .setPosition(CreditCalculationImplTest.position.TOP_MANAGER)
            .setWorkExperienceTotal(19)
            .setWorkExperienceCurrent(14);

    //insurance enable
    private ScoringDataDTO scoringDataDTO = new ScoringDataDTO()
            .setAmount(new BigDecimal("100000"))
            .setTerm(12)
            .setFirstName("Ivan")
            .setLastName("Ivanov")
            .setMiddleName("Ivanovich")
            .setGender(CreditCalculationImplTest.gender.MAN)
            .setBirthdate(LocalDate.of(1993, 02, 03))
            .setPassportIssueDate(LocalDate.of(2005, 3, 13))
            .setPassportIssueBranch("УФМС России по Воронежской обл. центрального р-а")
            .setMaritalStatus(CreditCalculationImplTest.maritalStatus.MARRIED)
            .setDependentAmount(3)
            .setEmployment(employmentDTO)
            .setAccount("account")
            .setIsInsuranceEnabled(true)
            .setIsSalaryClient(true);

    @Autowired
    private CreditCalculation creditCalculation;


    @Test
    void getCalculatedOffersTwo() {
        CreditDTO result = creditCalculation.getCalculatedOffers(scoringDataDTO);
        PaymentScheduleElement expected=new PaymentScheduleElement()
                .setNumber(2)
                .setDate(LocalDate.now().plusMonths(2))
                .setTotalPayment(new BigDecimal(113026.0740).setScale(4, RoundingMode.HALF_EVEN))
                .setInterestPayment(new BigDecimal(8.5))
                .setDebtPayment(new BigDecimal(9418.8395).setScale(4, RoundingMode.HALF_EVEN))
                .setRemainingDebt(new BigDecimal(6.0000).setScale(4, RoundingMode.HALF_EVEN));
        assertEquals(expected,result.getPaymentSchedule().get(2));
    }


    @Test
    void getCalculatedOffersFour() {
        CreditDTO result = creditCalculation.getCalculatedOffers(scoringDataDTO);
        PaymentScheduleElement expected=new PaymentScheduleElement()
                .setNumber(4)
                .setDate(LocalDate.now().plusMonths(4))
                .setTotalPayment(new BigDecimal(113026.0740).setScale(4, RoundingMode.HALF_EVEN))
                .setInterestPayment(new BigDecimal(4.25).setScale(2, RoundingMode.HALF_EVEN))
                .setDebtPayment(new BigDecimal(9418.8395).setScale(4, RoundingMode.HALF_EVEN))
                .setRemainingDebt(new BigDecimal(3.0000).setScale(4, RoundingMode.HALF_EVEN));
        assertEquals(expected,result.getPaymentSchedule().get(4));
    }
}