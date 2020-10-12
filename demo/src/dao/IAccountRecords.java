package dao;

import model.AccountRecords;

public interface IAccountRecords extends IBaseDao<AccountRecords> {

    int insertAccountRecord(AccountRecords accountRecord);

}
