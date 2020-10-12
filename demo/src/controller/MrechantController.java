package controller;

import dao.impl.MerchantDao;
import model.Merchant;
import service.MerchantServiceImpl;

public class MrechantController {


    MerchantServiceImpl merchantService = new MerchantServiceImpl();


    public static void main(String[] args) {
        System.out.println(getTest());
    }

    public static Integer getTest() {

        try {
            System.out.println("1");
            return 1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("2");
            return 2;
        }
    }
}