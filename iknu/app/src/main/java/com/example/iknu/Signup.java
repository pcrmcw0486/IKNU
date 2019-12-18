package com.example.iknu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//https://heepie.me/181 참조
public class Signup extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText email;
    EditText password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();
    }



    public void SignupRequest(View view) {
        email = (EditText) findViewById(R.id.Sign_up_ID);
        password = (EditText) findViewById(R.id.Sign_up_PW);
        String check_email = email.getText().toString();
        String check_password = password.getText().toString();

        if (!isValidEmail(check_email) || !isValidPasword(check_password))
        {
            if(!isValidEmail(check_email))
            {
                Toast.makeText(this.getApplicationContext(),"e-mail 형식이 맞지않습니다",Toast.LENGTH_SHORT).show();
            }
            if(!isValidPasword(check_password))
            {
                Toast.makeText(this.getApplicationContext(),"password 형식이 맞지 않습니다",Toast.LENGTH_SHORT).show();
            }
        }

        //step 1 가입요청
      /*  mAuth.createUserWithEmailAndPassword(check_email, check_password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    //step2 메일 인증 요청 처리
                    FirebaseUser user = mAuth.getCurrentUser();
                    user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            Toast.makeText(Signup.this,"메일발송완료!", Toast.LENGTH_SHORT).show();
                        }
                    });
                   // Intent intent = new Intent(Signup.this, MainActivity.class);
                  //  startActivity(intent);
                }
                else
                {
                    //가입에 실패할 경우 토스트 메시지 변환
                    Toast.makeText(Signup.this,"Authentication failed", Toast.LENGTH_SHORT).show();
                }
            }
        });*/

      //step 1 가입 요청
        else {
            mAuth.createUserWithEmailAndPassword(check_email, check_password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                //step2 메일 인증 요청 처리
                                FirebaseUser user = mAuth.getCurrentUser();
                                user.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(Signup.this, "메일발송완료!", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(Signup.this, "??", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                                //   Intent intent = new Intent(Signup.this, MainActivity.class);
                                //   startActivity(intent);
                            } else {
                                //가입에 실패할 경우 토스트 메시지 변환
                                Toast.makeText(Signup.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

        }
    }

    public static boolean isValidEmail(String email)
    {
        boolean err = false;
        String regex = "^[_a-z0-9]+(.[_a-z0-9-]+)*@(?:\\w+\\.)+\\w+$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        if(m.matches())
        {
            err = true;
        }
        return err;
    }

    public static boolean isValidPasword(String password )
    {
        boolean err = false;
        String regex = "^[a-zA-Z0-9]{8,}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(password);
        if(m.matches() )
        {
            err = true;
        }

        return err;
    }
}
