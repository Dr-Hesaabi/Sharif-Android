package ir.dr_hesaabi.infa.webService;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import ir.dr_hesaabi.infa.R;

import static com.google.android.gms.internal.abj.UTF_8;


/**
 * Created by Milad on 9/13/2017.
 */

public class VolleyAdapter {

    public static String MainUrl = "http://192.168.43.112/";
    public static String postPhoneNumberUrl = "api/getPhone";
    public Context context;
    public VolleyAdapter(Context context){
        this.context = context;
    }

    public String postPhoneNumber(Context context, String phoneNumber) {

        final String TAG = "postPhoneNumber";
        final String[] json = new String[1];
        final String[] p = {""};
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
                        json[0] = json[0].substring(1,5);
                        Log.i(TAG, json[0]);
                        p[0] = json[0];
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

//    public void postPddddhoneNumber(Context context, String phoneNumber) {
//
//        final String TAG = "postPhoneNumber";
//        try {
//            RequestQueue requestQueue = Volley.newRequestQueue(context);
//            JSONObject jsonBody = new JSONObject();
//            jsonBody.put("phone", phoneNumber);
//            final String requestBody = jsonBody.toString();
//
//            StringRequest stringRequest = new StringRequest(Request.Method.POST, MainUrl + postPhoneNumberUrl, new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    Log.i(TAG, response);
//
//                }
//            }, new Response.ErrorListener() {
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    Log.e(TAG, error.toString());
//                }
//            }) {
//                @Override
//                public String getBodyContentType() {
//                    return "application/json; charset=utf-8";
//                }
//
//                @Override
//                public byte[] getBody() throws AuthFailureError {
//                    try {
//                        return requestBody == null ? null : requestBody.getBytes("utf-8");
//                    } catch (UnsupportedEncodingException uee) {
//                        VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", requestBody, "utf-8");
//                        return null;
//                    }
//                }
//
//                @Override
//                protected Response<String> parseNetworkResponse(NetworkResponse response) {
//                    String responseString = "";
//                    if (response != null) {
//                        responseString = String.valueOf(response.statusCode);
//                        Log.i(TAG, responseString);
//                        String json = new String(response.data, UTF_8);
//                        try {
//
//                            JSONArray jsonArray = new JSONArray(json);
//                            for(int i = 0; i < jsonArray.length(); i++) {
//                                JSONObject jsonObject = jsonArray.getJSONObject(i);
//                                Log.i(TAG, "name : " + jsonObject.get("firstName"));
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                            Log.e(TAG, "Could not parse JSON: \"" + json + "\"");
//                        }
//                        Log.i(TAG, json);
//
//                        // can get more details such as response.headers
//                    }
//                    return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
//                }
//            };
//
//            RetryPolicy policy = new DefaultRetryPolicy(5000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
//            stringRequest.setRetryPolicy(policy);
//            requestQueue.add(stringRequest);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//    }

    public class PostPhoneNumberAsyncTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {

            final String TAG = "postPhoneNumber";
            final String[] json = new String[1];
            try {
                RequestQueue requestQueue = Volley.newRequestQueue(context);
                JSONObject jsonBody = new JSONObject();
                jsonBody.put("phone", params[0]);
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
                            json[0] = json[0].substring(1,5);
                            Log.i(TAG, json[0]);
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

        @Override
        protected void onPostExecute(String result) {

        }

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onProgressUpdate(Void... values) {

        }
    }

//    public void postPhoneNumberJson(Context context, String phoneNumber) {
//
//        final ArrayList<String> mEntries = null;
//        RequestQueue requestQueue = Volley.newRequestQueue(context);
//        // Post params to be sent to the server
//        HashMap<String, String> params = new HashMap<String, String>();
//        params.put("token", "token_value");
//        params.put("login_id", "login_id_value");
//        params.put("UN", "username");
//        params.put("PW", "password");
//
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(MainUrl+postPhoneNumberUrl, new JSONObject(params),
//                new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//                        for(int i = 0; i < response.length(); i++) {
//                            try {
//                                JSONObject jsonObject = response.getJSONObject(i);
//                                mEntries.add(jsonObject.toString());
//                            }
//                            catch(JSONException e) {
//                                mEntries.add("Error: " + e.getLocalizedMessage());
//                            }
//                        }                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                VolleyLog.e("Error: ", error.getMessage());
//            }
//
//        });
//        requestQueue.add(jsonObjectRequest);
//    }

}
