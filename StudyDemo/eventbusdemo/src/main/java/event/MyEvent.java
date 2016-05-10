package event;

/***************************************
 * Author zhouweibin
 * Description .
 * Date:2016/5/10
 ***************************************/
public class MyEvent {
    private String failed;
    private String success;
    private String response;

    public MyEvent(String failed, String response, String success) {
        this.failed = failed;
        this.response = response;
        this.success = success;
    }

    public MyEvent() {
    }

    public String getFailed() {
        return failed;
    }

    public void setFailed(String failed) {
        this.failed = failed;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}
