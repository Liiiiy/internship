package service.inf;

import model.Merchant;
import model.request.LoginInfo;

public interface MerchantService {

    Merchant registerMerchant(Merchant merchant);

    Merchant loginMerchant(LoginInfo loginInfo);

}
