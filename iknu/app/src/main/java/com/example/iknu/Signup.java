package com.example.iknu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Document;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//https://heepie.me/181 참조
public class Signup extends AppCompatActivity {

    private FirebaseAuth mAuth;
    EditText email;
    EditText password;
    EditText name;
    EditText age;
    EditText studentID;
    FirebaseFirestore db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        mAuth = FirebaseAuth.getInstance();
        email = (EditText) findViewById(R.id.Sign_up_ID);
        password = (EditText) findViewById(R.id.Sign_up_PW);
        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        name = (EditText)findViewById(R.id.Sign_up_Name);
        age = (EditText)findViewById(R.id.Sign_up_Age);
        studentID = (EditText)findViewById(R.id.Sign_up_StudentID);
        db = FirebaseFirestore.getInstance();
    }



    public void SignupRequest(View view) {

        String check_email = email.getText().toString();
        String check_password = password.getText().toString();
        final String check_name = name.getText().toString();
        final String check_SID = studentID.getText().toString();
        final int check_age = Integer.valueOf(age.getText().toString());

        if (!isValidEmail(check_email) || !isValidPasword(check_password) || !isValidAge(check_age) || !isValidName(check_name))
        {
            if(!isValidEmail(check_email))
            {
                Toast.makeText(this.getApplicationContext(),R.string.format_email,Toast.LENGTH_SHORT).show();
            }
            if(!isValidPasword(check_password))
            {
                Toast.makeText(this.getApplicationContext(),R.string.format_password,Toast.LENGTH_SHORT).show();
            }
            if(!isValidName(check_name))
            {
                Toast.makeText(this.getApplicationContext(),R.string.format_name,Toast.LENGTH_SHORT).show();
            }
            if(!isValidAge(check_age))
            {
                Toast.makeText(this.getApplicationContext(),R.string.format_age,Toast.LENGTH_SHORT).show();
            }

            email.setText("");
            password.setText("");
            name.setText("");
            age.setText("");
        }

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
                                            Toast.makeText(Signup.this, R.string.send_email, Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(Signup.this, "??", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });

                                Map<String, Object> userData = new HashMap<>();
                                userData.put("Name",check_name);
                                userData.put("StudentID", check_SID);
                                userData.put("UID",user.getUid());
                                userData.put("age",check_age);
                                db.collection("Users")
                                        .add(userData)
                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                            @Override
                                            public void onSuccess(DocumentReference documentReference) {
                                                Log.d("TAG","Document snapshot addded with ID : " + documentReference.getId());
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.w("TAG", "Error adding document with ",e);
                                            }
                                        });
                                
                                 Intent intent = new Intent(Signup.this, MainActivity.class);
                                  startActivity(intent);
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

    public static boolean isValidName(String name)
    {
        boolean err  = false;
        if(name.length() < 10)
            err = true;
        return err;
    }

    public static boolean isValidAge(int age)
    {
        boolean err = false;
        if(age > 19 && age < 70)
            err = true;
        return err;
    }
    public void BackButton(View view) {
        Intent intent = new Intent(Signup.this, MainActivity.class);
        startActivity(intent);
    }
}
