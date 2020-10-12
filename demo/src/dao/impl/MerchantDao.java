package dao.impl;

import dao.IMerchantDao;
import model.Merchant;
import util.FileUtils;

import java.util.LinkedList;
import java.util.List;

public class MerchantDao extends BaseDao<Merchant> implements IMerchantDao {

    public static void main(String[] args) {
        Merchant merchant = new Merchant();
        merchant.setId(88);

        MerchantDao merchantDao = new MerchantDao();
        merchantDao.deleteMerchantById(merchant);
    }


    @Override
    public List<Merchant> selectAllMerchants() {

        String sql = "select * from merchant";

        List<Merchant> merchants = null;

        merchants = this.selectMulObj(sql);

        return merchants;
    }


    @Override
    public Merchant selectById(Integer id) {

        String sql = "select * from merchant where id = " + id;

        Merchant merchant = null;

        merchant = this.selectObj(sql);

        return merchant;
    }


    @Override
    public Merchant selectByAccount(String account) {

        String sql = "select * from merchant where account = " + FileUtils.handleFiledValue(account);

        Merchant merchant = null;

        merchant = this.selectObj(sql);

        return merchant;
    }

    @Override
    public List<Merchant> selectByOwnerName(String ownerName) {

        String sql = "select * from merchant where owner_name = " + FileUtils.handleFiledValue(ownerName);
        List<Merchant> merchants= new LinkedList<>();

        merchants = this.selectMulObj(sql);

        return merchants;
    }

    @Override
    public int insertMerchant(Merchant merchant) {

        int insertRows = this.insertObj(merchant);
        return insertRows;
    }

    @Override
    public int insertMerchants(List<Merchant> merchants) {

        int insertRows = this.insertMulObj(merchants);
        return insertRows;
    }

    @Override
    public int updateMerchantInfo(Merchant merchant) {

        int updateRows = this.updateObjById(merchant);
        return updateRows;
    }

    @Override
    public int updateMerchantsInfo(List<Merchant> merchants) {

        int updateRows = 0;
        for (Merchant mer : merchants) {
            updateRows += this.updateObjById(mer);
        }

        return updateRows;
    }

    @Override
    public int deleteMerchantById(Merchant merchant) {

        int deletedNum = this.deleteObjById(merchant.getId());
        return deletedNum;
    }

    @Override
    public int deleteMerchantsById(List<Integer> idList) {
        return 0;
    }


}
