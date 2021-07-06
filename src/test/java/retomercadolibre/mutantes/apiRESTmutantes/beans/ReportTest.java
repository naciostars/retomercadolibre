package retomercadolibre.mutantes.apiRESTmutantes.beans;

import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.jupiter.api.Assertions;

public class ReportTest {
    @Test
    void testCalRatio() {
        long cantHumanExpected, cantMutantExpected, cantHumanActual, cantMutantActual;
        float ratioExpected, ratioActual;

        Report report = new Report(40,100,0);
        report.calRatio();
        cantMutantExpected=40;
        cantHumanExpected=100;
        ratioExpected = (float)cantMutantExpected/(float)cantHumanExpected;
        BigDecimal bd = new BigDecimal(ratioExpected).setScale(2, RoundingMode.HALF_UP);
        ratioExpected = bd.floatValue();
        cantHumanActual=report.getCount_human_dna();
        cantMutantActual=report.getCount_mutant_dna();
        ratioActual=report.getRatio();
        Assertions.assertEquals(cantMutantExpected, cantMutantActual);
        Assertions.assertEquals(cantHumanExpected, cantHumanActual);
        Assertions.assertEquals(ratioExpected, ratioActual);

        report = new Report();
        report.setCount_mutant_dna(40);
        report.setCount_human_dna(100);
        report.setRatio(0);
        report.calRatio();
        cantMutantExpected=40;
        cantHumanExpected=100;
        ratioExpected = (float)cantMutantExpected/(float)cantHumanExpected;
        bd = new BigDecimal(ratioExpected).setScale(2, RoundingMode.HALF_UP);
        ratioExpected = bd.floatValue();
        cantHumanActual=report.getCount_human_dna();
        cantMutantActual=report.getCount_mutant_dna();
        ratioActual=report.getRatio();
        Assertions.assertEquals(cantMutantExpected, cantMutantActual);
        Assertions.assertEquals(cantHumanExpected, cantHumanActual);
        Assertions.assertEquals(ratioExpected, ratioActual);

        report = new Report();
        report.calRatio();
        cantMutantExpected=0;
        cantHumanExpected=0;
        ratioExpected = (float)0;
        bd = new BigDecimal(ratioExpected).setScale(2, RoundingMode.HALF_UP);
        ratioExpected = bd.floatValue();
        cantHumanActual=report.getCount_human_dna();
        cantMutantActual=report.getCount_mutant_dna();
        ratioActual=report.getRatio();
        Assertions.assertEquals(cantMutantExpected, cantMutantActual);
        Assertions.assertEquals(cantHumanExpected, cantHumanActual);
        Assertions.assertEquals(ratioExpected, ratioActual);
    
        report = new Report(2,0,0);
        report.calRatio();
        cantMutantExpected=2;
        cantHumanExpected=0;
        ratioExpected = (float)2;
        bd = new BigDecimal(ratioExpected).setScale(2, RoundingMode.HALF_UP);
        ratioExpected = bd.floatValue();
        cantHumanActual=report.getCount_human_dna();
        cantMutantActual=report.getCount_mutant_dna();
        ratioActual=report.getRatio();
        Assertions.assertEquals(cantMutantExpected, cantMutantActual);
        Assertions.assertEquals(cantHumanExpected, cantHumanActual);
        Assertions.assertEquals(ratioExpected, ratioActual);
    }
}
