package art.xingzou.listenpoetry.response;

public class Response<T> {

  private static final String DEFAULT_SUCCESS_CODE = "200";
  private static final String DEFAULT_SUCCESS_MESSAGE = "成功";
  private static final String DEFAULT_ERROR_CODE = "0";
  private static final String DEFAULT_ERROR_MESSAGE = "失败";

  private boolean success;

  private String code;

  private String message;

  private T result;

  public static <T> Response<T> success() {
    return success(null);
  }

  public static <T> Response<T> success(T result) {
    return new Response<T>(true, DEFAULT_SUCCESS_CODE, DEFAULT_SUCCESS_MESSAGE, result);
  }

  public static <T> Response<T> fail(String code, String message) {
    return new Response<T>(false, code, message, null);
  }

  public static <T> Response<T> fail(String message) {
    return fail(DEFAULT_ERROR_CODE, message);
  }

  public static <T> Response<T> fail() {
    return fail(DEFAULT_ERROR_CODE, DEFAULT_ERROR_MESSAGE);
  }

  public Response(boolean success, String code, String message, T result) {
    this.success = success;
    this.code = code;
    this.message = message;
    this.result = result;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(boolean success) {
    this.success = success;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public T getResult() {
    return result;
  }

  public void setResult(T result) {
    this.result = result;
  }
}
