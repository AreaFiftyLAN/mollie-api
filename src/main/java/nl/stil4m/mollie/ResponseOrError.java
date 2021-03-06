package nl.stil4m.mollie;

import nl.stil4m.mollie.domain.ErrorData;

import java.util.function.Consumer;

public class ResponseOrError<V> {

    private final int status;
    private final V data;
    private final ErrorData error;
    private final Boolean success;

    public static <V, T> ResponseOrError withError(int status, ErrorData error) {
        return new ResponseOrError<>(status, null, error, false);
    }

    public static <V, T> ResponseOrError withData(int status, V data) {
        return new ResponseOrError<>(status, data, null, true);
    }

    private ResponseOrError(int status, V data, ErrorData error, Boolean success) {
        this.status = status;
        this.data = data;
        this.error = error;
        this.success = success;
    }

    public void get(Consumer<V> onSuccess, Consumer<ErrorData> onError) {
        if (success) {
            onSuccess.accept(data);
        } else {
            onError.accept(error);
        }
    }

    public int getStatus() {
        return status;
    }

    public V getData() {
        return data;
    }

    public ErrorData getError() {
        return error;
    }

    public Boolean getSuccess() {
        return success;
    }
}
