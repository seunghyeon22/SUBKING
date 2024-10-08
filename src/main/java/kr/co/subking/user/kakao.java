package kr.co.subking.user;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class kakao {

    public static void main(String[] args) {
        String apiKey = "323f7176876dcf324ddc55bbb874736d"; // REST API 키
        String redirectUri = "http://localhost:8080"; // 리다이렉트 URI
        String authCode = "AUTHORIZATION_CODE"; // 인증 코드

        try {
            String tokenUrl = "https://kauth.kakao.com/oauth/token";
            URL url = new URL(tokenUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // HTTP 요청 방식 설정
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            // 요청 데이터 작성
            String data = "grant_type=authorization_code"
                        + "&client_id=" + apiKey
                        + "&redirect_uri=" + redirectUri
                        + "&code=" + authCode;

            // 요청 데이터 전송
            OutputStream os = conn.getOutputStream();
            os.write(data.getBytes());
            os.flush();
            os.close();

            // 응답 처리
            if (conn.getResponseCode() == 200) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;

                while ((line = br.readLine()) != null) {
                    response.append(line);
                }
                br.close();

                // 액세스 토큰 출력
                System.out.println("Access Token: " + response.toString());
            } else {
                System.out.println("Error: " + conn.getResponseCode() + " - " + conn.getResponseMessage());
            }

            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
