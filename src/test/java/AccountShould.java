import com.codurance.bankkata.Account;
import com.codurance.bankkata.Clock;
import com.codurance.bankkata.Transaction;
import com.codurance.bankkata.TransactionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class AccountShould {

    private static final int POSITIVE_AMOUNT = 100;
    private static final int NEGATIVE_AMOUNT = -POSITIVE_AMOUNT;
    private static final LocalDate TODAY = LocalDate.of(2017, 9, 6);

    @Mock
    private Clock clock;

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private Account account;


    @Test
    public void validate_that_the_deposit_amount_is_positive(){

        Throwable throwable = catchThrowable(() -> account.deposit(NEGATIVE_AMOUNT));

        assertThat(throwable)
                .isNotNull()
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    public void deposit_amount_into_the_account(){

        given(clock.today()).willReturn(TODAY);

        account.deposit(POSITIVE_AMOUNT);

        verify(transactionRepository).add(new Transaction(TODAY, POSITIVE_AMOUNT));
    }

    @Test
    public void validate_that_the_withdraw_amount_is_positive(){

        Throwable throwable = catchThrowable(() -> account.withdraw(NEGATIVE_AMOUNT));

        assertThat(throwable)
                .isNotNull()
                .isInstanceOf(IllegalArgumentException.class);
    }


}
