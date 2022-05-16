package hello.core.lifecycle;

public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("NetworkClient.NetworkClient() url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 서비스 시작 전 호출되어야 하는 콜백
     */
    public void connect() {
        System.out.println("NetworkClient.connect() url = " + url);
    }

    public void call(String message) {
        System.out.println("NetworkClient.call() url = " + url + " / message = " + message);
    }

    /**
     * 서비스 종료 전 호출되어야 하는 콜백
     */
    public void disconnect() {
        System.out.println("NetworkClient.disconnect()");
    }

}
