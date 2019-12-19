package com.example.iknu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Target;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Translate extends AppCompatActivity {
    private EditText translationText;
    private Button translationButton;
    private TextView resultText;
    private String result;
    private String InputText;
    public int sourceLang=1;   // 1=한국어, 2=영어, 3=일본어,  소스 언어
    public int targetLang=2;   // 1=한국어, 2=영어, 3=일본어,  목표 언어

    public void SourceLangSetOnclickListener(View view) {
        TextView SourceButtonText = (TextView) findViewById(R.id.ButtonSourceLang);
        if(sourceLang == 1 ){   // 현재 상태 한국어
            sourceLang+=1;    //다음 언어를 가르킨다.
            SourceButtonText.setText(getString(R.string.Language2));

        }
        else if(sourceLang == 2){ // 현재 상태 영어
            sourceLang+=1;
            SourceButtonText.setText(getString(R.string.Language3));
        }
        else if(sourceLang ==3){ // 현재 상태 일본어
            sourceLang=1;
            SourceButtonText.setText(getString(R.string.Language1));
        }

        Toast.makeText(getApplicationContext(),"Succese",Toast.LENGTH_SHORT).show();
    }

    public void TargetLangSetOnclickListener(View view) {
        TextView TargetButtonText = (TextView) findViewById(R.id.ButtonTargetLang);
        if(targetLang == 1 ){   // 현재 상태 한국어
            targetLang+=1;    //다음 언어를 가르킨다.
            TargetButtonText.setText(getString(R.string.Language2));
        }
        else if(targetLang == 2){ // 현재 상태 영어
            targetLang+=1;
            TargetButtonText.setText(getString(R.string.Language3));
        }
        else if(targetLang ==3){ // 현재 상태 일본어
            targetLang=1;
            TargetButtonText.setText(getString(R.string.Language1));
        }
        Toast.makeText(getApplicationContext(),"Succese",Toast.LENGTH_SHORT).show();
    }

    public void StartButton(View view) {
        translationText = (EditText) findViewById(R.id.et_source);
        translationButton = (Button) findViewById(R.id.bt_translate);
        resultText = (TextView) findViewById(R.id.tv_result);
        InputText = translationText.getText().toString();

        translationButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                new BackgroundTask().execute();
            }
        });

    }

    //백 그라운드에서 파파고 API와 연결하여 번역 결과를 가져옵니다.
    class BackgroundTask extends AsyncTask<Integer, Integer, Integer>{
        protected void onPreExecute(){
            sourceLang=1;   // 1=한국어, 2=영어, 3=일본어,  소스 언어
            targetLang=2;   // 1=한국어, 2=영어, 3=일본어,  목표 언어
        }

        @Override
        protected Integer doInBackground(Integer...arg0) {
            StringBuilder output = new StringBuilder();
            String clientId = "gqZ7OhuggbnncGZv_mFj";
            String clientSecret = "L0VzwCnpdw";
            try {
                //번역문을  UTF-8으로 인코딩합니다.
                String text = URLEncoder.encode(InputText, "UTF-8");
                String apiURL = "https://openapi.naver.com/v1/papago/n2mt";

                //파파고 API와 연결을 수행합니다.
                URL url = new URL(apiURL);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("POST");
                con.setRequestProperty("X-NAVER-Client-Id", clientId);
                con.setRequestProperty("X-Naver-Client-Secret", clientSecret);

                // 번역할 문장을 파라미터로 전송합니다.
                String  postParms    = "source=ko&target=en&text=" + text;
                String  postParms1_1 = "source=ko&target=ko&text=" + text;
                String  postParms1_2 = "source=ko&target=en&text=" + text;
                String  postParms1_3 = "source=ko&target=ja&text=" + text;
                String  postParms2_1 = "source=en&target=ko&text=" + text;
                String  postParms2_2 = "source=en&target=en&text=" + text;
                String  postParms2_3 = "source=en&target=ja&text=" + text;
                String  postParms3_1 = "source=ja&target=ko&text=" + text;
                String  postParms3_2 = "source=ja&target=en&text=" + text;
                String  postParms3_3 = "source=ja&target=ja&text=" + text;
                con.setDoOutput(true);
                DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                /*
                switch (sourceLang){
                    case 1:  //한국어->
                        switch (targetLang){
                            case 1: wr.writeBytes(postParms1_1);break; //->한국어
                            case 2: wr.writeBytes(postParms1_2);break; //->영어
                            case 3: wr.writeBytes(postParms1_3);break; //->일본어
                        }
                    case 2:  //영어->
                        switch (targetLang){
                            case 1: wr.writeBytes(postParms2_1);break; //->한국어
                            case 2: wr.writeBytes(postParms2_2);break; //->영어
                            case 3: wr.writeBytes(postParms2_3);break; //->일본어
                        }
                    case 3:  //일본어->
                        switch (targetLang){
                            case 1: wr.writeBytes(postParms3_1);break;
                            case 2: wr.writeBytes(postParms3_2);break;
                            case 3: wr.writeBytes(postParms3_3);break;
                        }
                }*/
                wr.writeBytes(postParms);
                wr.flush();
                wr.close();


                //번역 결과를 받아온다.
                int responseCode = con.getResponseCode();
                BufferedReader br;
                if (responseCode == 200) {
                    br = new BufferedReader(new InputStreamReader((con.getInputStream())));
                } else {
                    br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                }
                String inputLine;
                while ((inputLine = br.readLine()) != null) {
                    output.append(inputLine);
                }
                br.close();
            } catch (Exception ex) {
                Log.e("SampleHTTP", "Exception in processing response.", ex);
                ex.printStackTrace();
            }
            result = output.toString();

            return null;

        }

        protected void onPostExecute(Integer a){
            JsonParser parser =new JsonParser();
            JsonElement element = parser.parse(result);
            if(element.getAsJsonObject().get("errorMessage")!=null){
                Log.e("번역 오류","번역 오류가 발생했습니다."+"오류 코드:"+element.getAsJsonObject().get("errorCode").getAsString()+"]");
            }else if(element.getAsJsonObject().get("message")!=null){
                //번역 결과 출력
                resultText.setText(element.getAsJsonObject().get("message").getAsJsonObject().get("result")
                        .getAsJsonObject().get("translatedText").getAsString());
            }

        }
    }
    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate);

    }
    public void onMove(View view) {
        Intent intent = null;
        switch (view.getId())
        {
            case R.id.Forum_btn :
                break;
            case R.id.Setting_btn :
                intent = new Intent(Translate.this, Setting.class);
                startActivity(intent);
                finish();
                break;
            case R.id.Home_btn :
                intent = new Intent(Translate.this, Home.class);
                startActivity(intent);
                finish();
            default:
                return;
        }


    }

}