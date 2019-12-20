package palindrome.data;

public class IOResponse {
    public IOResponse(responseType response, IOData responseData) {
        this.response = response;
        this.responseData = responseData;
    }

    @Override
    public String toString() {
        String ret = "Response type: "+response;
        if(responseData!=null)
            ret+="Response data: "+responseData;
        return ret;
    }

    public enum responseType {notPalindrome, correct, used}
    private responseType response; public responseType getResponse() { return response; }
    public IOData responseData=null; public IOData getIOData(){ return responseData; }
}
