package ir.dr_hesaabi.infa.activity;

import android.os.AsyncTask;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import org.apache.http.NameValuePair;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import ir.dr_hesaabi.infa.R;
import ir.dr_hesaabi.infa.utils.Font;
import ir.dr_hesaabi.infa.webService.MyWebClient;
import ir.dr_hesaabi.infa.webService.VolleyAdapter;

import static ir.dr_hesaabi.infa.activity.ActivityLogin.shared;

public class ActivityEditProfile extends AppCompatActivity {

    AppBarLayout appbar;
    Toolbar toolbar;
    ImageButton imgProfilePic;
    EditText edtName,edtMobile,edtEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        initViews();

        ((Button)findViewById(R.id.btn_a_ABA_EditAccount)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
POST post = new POST();
                try {
                    String a  = post.execute("").get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }
        });

        appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (Math.abs(verticalOffset) == appBarLayout.getTotalScrollRange()) {
                    // Collapsed
                    imgProfilePic.setVisibility(View.GONE);
                } else {
                    // Somewhere in between
                    imgProfilePic.setVisibility(View.VISIBLE);
                }
            }
        });

        try {
//            VolleyAdapter volleyAdapter = new VolleyAdapter(ActivityEditProfile.this);
//            JSONObject jsonObject = volleyAdapter.getUserInfo(ActivityEditProfile.this,shared.getString("mobile",""));
//            Log.i("JSoNObj",jsonObject.get("name").toString()+"   "+jsonObject.get("email").toString());
//            edtName.setText(jsonObject.get("name").toString());
//            edtMobile.setText(shared.getString("mobile",""));
//            edtEmail.setText(jsonObject.get("email").toString());

           String rrr = new GET().execute("").get();
            JSONObject js =new JSONObject(rrr);
            edtName.setText(js.get("name").toString());
          edtMobile.setText(shared.getString("mobile",""));
            edtEmail.setText(js.get("email").toString());
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    class POST extends AsyncTask<String, String, String >
    {
        @Override
        protected String doInBackground(String... strings) {
            MyWebClient client = new MyWebClient(VolleyAdapter.MainUrl  );
            try {
                List<NameValuePair> nvc = new ArrayList<>();
                nvc.add(new NameValuePair() {
                    @Override
                    public String getName() {
                        return "phone";
                    }

                    @Override
                    public String getValue() {
                        return "09388063351";
                    }
                });
                nvc.add(new NameValuePair() {
                    @Override
                    public String getName() {
                        return "email";
                    }

                    @Override
                    public String getValue() {
                        return "tayyebi@gmail.com";
                    }
                });
                nvc.add(new NameValuePair() {
                    @Override
                    public String getName() {
                        return "name";
                    }

                    @Override
                    public String getValue() {
                        return "MohamRe";
                    }
                });

                return   client.Post(VolleyAdapter.postCustomerInfo + "/09388063351", nvc );
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }
        }
    }

    class GET extends AsyncTask<String, String, String >
    {
        @Override
        protected String doInBackground(String... strings) {
            MyWebClient client = new MyWebClient(VolleyAdapter.MainUrl  );
            try {
                return   client.Get(VolleyAdapter.getCustomerInfo + "/" + shared.getString("mobile",""));
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }
        }
    }

    private void initViews(){
        Font.setFont((ViewGroup) getWindow().getDecorView(), ActivityEditProfile.this, "behdad.ttf");

        appbar = (AppBarLayout) findViewById(R.id.main_appbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        imgProfilePic = (ImageButton) findViewById(R.id.imgProfilePic);
        edtName = (EditText) findViewById(R.id.edtName);
        edtMobile = (EditText) findViewById(R.id.edtMobile);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
    }
}
