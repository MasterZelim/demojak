package ce.finance.demojak.model;

public class Error {

    private String massage;

    private int status;

    public Error() {
    }

    public Error(String massage, int status) {
        this.massage = massage;
        this.status = status;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
