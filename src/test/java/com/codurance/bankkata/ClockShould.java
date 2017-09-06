package com.codurance.bankkata;

import org.junit.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class ClockShould {

    private final Clock clock = new Clock();

    @Test
    public void inform_current_date(){
        assertThat(clock.today())
                .isEqualTo(LocalDate.now());
    }

}