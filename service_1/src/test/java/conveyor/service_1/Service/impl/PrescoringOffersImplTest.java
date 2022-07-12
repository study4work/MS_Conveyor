package conveyor.service_1.Service.impl;

import conveyor.service_1.DTO.LoanApplicationRequestDTO;
import conveyor.service_1.DTO.LoanOfferDTO;
import conveyor.service_1.Service.PrescoringOffers;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class PrescoringOffersImplTest {

    @Autowired
    private PrescoringOffers prescoringOffers;


    private LoanApplicationRequestDTO loanDto=new LoanApplicationRequestDTO()
            .setAmount(new BigDecimal("100000"))
            .setBirthdate(LocalDate.of(1993, 02, 03))
            .setEmail("test@mail.ru")
            .setFirstName("Ivan")
            .setLastName("Ivanov")
            .setMiddleName("Ivanivich")
            .setPassportNumber("123456")
            .setPassportSeries("1234")
            .setTerm(12);


    @Test
    void getMonthlyPayment() {
        BigDecimal result=prescoringOffers.getMonthlyPayment(loanDto.getAmount(), BigDecimal.valueOf(0.10),12,false);
        BigDecimal expected= BigDecimal.valueOf(8789.7285).setScale(4, RoundingMode.HALF_EVEN);
        assertEquals(expected,result);
    }

    @Test
    void getInsurance() {
        BigDecimal result=prescoringOffers.getInsurance(loanDto.getAmount());
        BigDecimal expected= BigDecimal.valueOf(10000).setScale(4, RoundingMode.HALF_EVEN);
        assertEquals(expected,result);
    }

    @Test
    void getTotalAmount() {
        BigDecimal resul=  prescoringOffers.getTotalAmount(loanDto.getAmount(), BigDecimal.valueOf(0.10),loanDto.getTerm(),false);
        BigDecimal expected= BigDecimal.valueOf(105476.7420).setScale(4, RoundingMode.HALF_EVEN);
        assertEquals(expected,resul);
    }

    @Test
    void getPrescoringOffers() {
        List<LoanOfferDTO> expected = new ArrayList<>();
        LoanOfferDTO loanOfferDTOOne=new LoanOfferDTO()
                .setApplicationId(Long.valueOf(0))
                .setRequestedAmount(BigDecimal.valueOf(100000))
                .setTotalAmount(new BigDecimal(105476.7420).setScale(4, RoundingMode.HALF_EVEN))
                .setTerm(12)
                .setMonthlyPayment(new BigDecimal(8789.7285).setScale(4, RoundingMode.HALF_EVEN))
                .setRate(BigDecimal.valueOf(0.10))
                .setIsInsuranceEnabled(false)
                .setIsSalaryClient(false);

        LoanOfferDTO loanOfferDTOTwo=new LoanOfferDTO()
                .setApplicationId(Long.valueOf(1))
                .setRequestedAmount(BigDecimal.valueOf(100000))
                .setTotalAmount(new BigDecimal(126024.4156).setScale(4, RoundingMode.HALF_EVEN))
                .setTerm(12)
                .setMonthlyPayment(new BigDecimal(9668.7013).setScale(4, RoundingMode.HALF_EVEN))
                .setRate(BigDecimal.valueOf(0.10))
                .setIsInsuranceEnabled(true)
                .setIsSalaryClient(false);

        LoanOfferDTO loanOfferDTOThree=new LoanOfferDTO()
                .setApplicationId(Long.valueOf(2))
                .setRequestedAmount(BigDecimal.valueOf(100000))
                .setTotalAmount(new BigDecimal(126024.4156).setScale(4, RoundingMode.HALF_EVEN))
                .setTerm(12)
                .setMonthlyPayment(new BigDecimal(9668.7013).setScale(4, RoundingMode.HALF_EVEN))
                .setRate(BigDecimal.valueOf(0.10))
                .setIsInsuranceEnabled(true)
                .setIsSalaryClient(true);

        LoanOfferDTO loanOfferDTOFour=new LoanOfferDTO()
                .setApplicationId(Long.valueOf(3))
                .setRequestedAmount(BigDecimal.valueOf(100000))
                .setTotalAmount(new BigDecimal(105476.7420).setScale(4, RoundingMode.HALF_EVEN))
                .setTerm(12)
                .setMonthlyPayment(new BigDecimal(8789.7285).setScale(4, RoundingMode.HALF_EVEN))
                .setRate(BigDecimal.valueOf(0.10))
                .setIsInsuranceEnabled(false)
                .setIsSalaryClient(true);
        expected.add(loanOfferDTOOne);
        expected.add(loanOfferDTOTwo);
        expected.add(loanOfferDTOThree);
        expected.add(loanOfferDTOFour);

        List<LoanOfferDTO> result = prescoringOffers.getPrescoringOffers(loanDto);
        assertEquals(expected,result);

    }
}