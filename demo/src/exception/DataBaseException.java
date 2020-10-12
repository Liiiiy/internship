
package exception;

public class DataBaseException extends RuntimeException {

    public DataBaseException(String msg,Exception ex){
        super(msg,ex);
    }

    public DataBaseException(){
        super();
    }
    public DataBaseException(Exception e){
        super(e);
    }

    public DataBaseException(String msg){
        super(msg);
    }

    public static void main(String[] args) {
        test();
    }


    public static void test() {
        Exception e = new Exception("haha，我读取文件找不到文件了");
        try {
            throw e;
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }
}
