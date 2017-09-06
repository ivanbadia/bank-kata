import com.codurance.bankkata.Account;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class AccountShould {

    public static final int NEGATIVE_AMOUNT = -100;

    @Test
    public void validate_the_deposit_amount_is_positive(){

        Account account = new Account();

        Throwable throwable = catchThrowable(() -> account.deposit(NEGATIVE_AMOUNT));

        assertThat(throwable)
                .isNotNull()
                .isInstanceOf(IllegalArgumentException.class);

    }



}
