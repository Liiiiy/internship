package dao.impl;

import dao.IAccountRecords;
import model.AccountRecords;

public class AccountRecordsDao extends BaseDao<AccountRecords> implements IAccountRecords {

    @Override
    public int insertAccountRecord(AccountRecords accountRecord) {

        int insertedRows = this.insertObj(accountRecord);
        return insertedRows;
    }
}
