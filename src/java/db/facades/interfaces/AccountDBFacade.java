package db.facades.interfaces;

import domain.entities.Account;
import domain.enums.Status;
import java.util.List;

public interface AccountDBFacade{
   
    public Account create(Account a);
    public Account edit(Account u);
    public void remove(Account u);
    public Account findAccount(long id);
    public Account findAccount(String username);
    public List<Account> findAllAccounts();
}
