package dao;

import dao.impl.BaseDao;
import model.Merchant;

import java.util.List;

public interface IMerchantDao extends IBaseDao<Merchant> {

    List<Merchant> selectAllMerchants();

    Merchant selectById(Integer id);

    Merchant selectByAccount(String account);

    List<Merchant> selectByOwnerName(String ownerName);

    int insertMerchant(Merchant merchant);

    int insertMerchants(List<Merchant> merchants);

    int updateMerchantInfo(Merchant merchant);

    int updateMerchantsInfo(List<Merchant> merchants);

    int deleteMerchantById(Merchant merchant);

    int deleteMerchantsById(List<Integer> idList);

}
