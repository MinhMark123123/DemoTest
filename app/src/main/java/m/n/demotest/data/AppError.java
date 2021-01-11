package m.n.demotest.data;

public class AppError {
    String errorMessage;
    int code;

    public AppError(int code, String errorMessage) {
        this.code = code;
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public int getCode() {
        return code;
    }
}
