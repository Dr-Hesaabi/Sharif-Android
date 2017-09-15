package ir.dr_hesaabi.infa.webService;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import static com.google.android.gms.internal.abj.UTF_8;
import static ir.dr_hesaabi.infa.activity.ActivityLogin.editor;


/**
 * Created by Milad on 9/13/2017.
 */

public class VolleyAdapter {

    public static String MainUrl = "http://192.168.0.12/";
    public static String postPhoneNumberUrl = "api/getPhone";
    public static String getCustomerInfo = "api/getCustomer";
    public static String postCustomerInfo = "api/editCustomer";

    public static Context context;
    public VolleyAdapter(Context context){
        this.context = context;
    }

    public String getSmsCode(Context context, final String phoneNumber) {

        final String TAG = "getSmsCode";
        final String[] json = new String[1];
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("phone", phoneNumber);
            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, MainUrl + postPhoneNumberUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.i(TAG, response);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, error.toString());
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                        Log.i(TAG, responseString);
                        json[0] = new String(response.data, UTF_8);
                        if(json[0].length()!=0){
                            json[0] = json[0].substring(1,5);
                            editor.putString("smsCode",json[0]).commit();
                            editor.putString("mobile",phoneNumber).commit();
                        }

                        // can get more details such as response.headers
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            RetryPolicy policy = new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            stringRequest.setRetryPolicy(policy);
            requestQueue.add(stringRequest);
            return json[0];
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return json[0];
    }

    public JSONObject getUserInfo(Context context, String phoneNumber) {

        final String TAG = "getInfo";
        final JSONObject[] obj = new JSONObject[1];

        try {
            RequestQueue requestQueue = Volley.newRequestQueue(context);
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("phone", phoneNumber);
            final String requestBody = jsonBody.toString();

            StringRequest stringRequest = new StringRequest(Request.Method.POST, MainUrl + getCustomerInfo, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    Log.i(TAG, response);

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e(TAG, error.toString());
                }
            }) {
                @Override
                public String getBodyContentType() {
                    return "application/json; charset=utf-8";
                }

                @Override
                public byte[] getBody() throws AuthFailureError {
                    try {
                        return requestBody == null ? null : requestBody.getBytes("utf-8");
                    } catch (UnsupportedEncodingException uee) {
                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
                        return null;
                    }
                }

                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                    String responseString = "";
                    if (response != null) {
                        responseString = String.valueOf(response.statusCode);
                        Log.i(TAG, responseString);
                        if(responseString.length()!=0) {
                            try {
                                obj[0] = new JSONObject(responseString);
                                Log.i(TAG, String.valueOf(obj[0].get("name")));
                                Log.i(TAG, String.valueOf(obj[0].get("email")));

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        // can get more details such as response.headers
                    }
                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
                }
            };

            RetryPolicy policy = new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            stringRequest.setRetryPolicy(policy);
            requestQueue.add(stringRequest);
            return obj[0];
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return obj[0];
    }
}
