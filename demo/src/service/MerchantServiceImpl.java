package service;

import dao.IMerchantDao;
import dao.impl.MerchantDao;
import exception.BaseException;
import model.Merchant;
import model.request.LoginInfo;
import service.inf.MerchantService;
import util.StringUtils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MerchantServiceImpl implements MerchantService {

    IMerchantDao merchantDao = new MerchantDao();

    public Merchant registerMerchant(Merchant merchant) {

        // 把密码使用md5加密
        if (checkRegistrationInfo(merchant)) {
            String encryptedPassword = encryptPassword(merchant.getPassword());
            merchant.setPassword(encryptedPassword);
            merchantDao.insertMerchant(merchant);
        }

        return merchant;
    }

    public Merchant loginMerchant(LoginInfo loginInfo) {

        if (loginInfo == null || StringUtils.isEmpty(loginInfo.getAccount())) {
            throw new BaseException(-1, "请传入账号名");
        }
        if (loginInfo.getPassword() == null) {
            throw new BaseException(-1, "请传入账号密码");
        }

        Merchant merchant = merchantDao.selectByAccount(loginInfo.getAccount());
        String password = merchant.getPassword();

        if (merchant == null) {
            throw new BaseException(-1, "账户名不存在");
        } else if (!encryptPassword(loginInfo.getPassword()).equals(password)) {
            throw new BaseException(-1, "密码错误");
        }

        merchant.setPassword(null);
        return merchant;
    }


    private boolean checkRegistrationInfo(Merchant merchant) {

        boolean validation = true;
        if (merchant == null || StringUtils.isEmpty(merchant.getAccount())) {
            validation = false;
            throw new BaseException(-1, "请传入商家的注册信息");
        }

        if (StringUtils.isEmpty(merchant.getOwnerName())) {
            validation = false;
            throw new BaseException(-1, "请传入商家店主名");
        }
        if (StringUtils.isEmpty(merchant.getStoreName())) {
            validation = false;
            throw new BaseException(-1, "请传入商店名");
        }
        if (StringUtils.isEmpty(merchant.getPassword())) {
            validation = false;
            throw new BaseException(-1, "请传入密码");
        }

        Merchant checkExist = merchantDao.selectByAccount(merchant.getAccount());
        if (checkExist != null) {
            validation = false;
            throw new BaseException(-1, "对不起，账户名已被注册");
        }
        return validation;
    }

    private static String encryptPassword(String password) {
        try {

            MessageDigest md = MessageDigest.getInstance(Merchant.DIGEST);
            md.update(password.getBytes());
            return new BigInteger(1, md.digest()).toString(16);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }
}
