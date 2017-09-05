import com.codurance.bankkata.Account;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class AccountShould {

    @Test
    public void validate_the_deposit_amount_is_not_positive(){

        Account account = new Account();

        Throwable throwable = catchThrowable(() -> account.deposit(-100));

        assertThat(throwable)
                .isNotNull()
                .isInstanceOf(IllegalArgumentException.class);

    }



}
