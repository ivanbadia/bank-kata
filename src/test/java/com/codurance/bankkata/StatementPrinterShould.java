package com.codurance.bankkata;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static java.util.Collections.emptyList;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class StatementPrinterShould {

    @Mock
    private Console console;

    @Test
    public void print_header(){

        StatementPrinter statementPrinter = new StatementPrinter(console);

        statementPrinter.print(emptyList());

        verify(console).printLine("DATE | AMOUNT | BALANCE");

    }

}