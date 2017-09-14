//package ir.dr_hesaabi.infa.webService;
//
//import org.apache.http.NameValuePair;
//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
//
//import java.util.List;
//
///**
// * Created by Admin on 9/13/2017.
// */
//
//
//class MyJson {
//
//    ///
//    // Decods just an object
//    ///
//    public List<NameValuePair> Decode(String jsonString, String[] keys) throws JSONException {
//        JSONArray mJsonArray = new JSONArray(jsonString);
//        final JSONObject mJsonObject = new JSONObject();
//
//        /*
//        for (int i = 0; i < mJsonArray.length(); i++) {
//            mJsonObject = mJsonArray.getJSONObject(i);
//            mJsonObject.getString("0");
//            mJsonObject.getString("id");
//            mJsonObject.getString("1");
//            mJsonObject.getString("name");
//        }
//        */
//
//        List<NameValuePair> output = null;
//        int i = 0;
//
//        for (final String key : keys) {
//            output.add(i,
//
//                    new NameValuePair() {
//                        @Override
//                        public String getName() {
//                            return key;
//                        }
//
//                        @Override
//                        public String getValue() {
//                            try {
//                                return mJsonObject.getString("id");
//                            } catch (JSONException e) {
//                                e.printStackTrace();
//                                return null;
//                            }
//                        }
//                    }
//            );
//        }
//        return output;
//    }
//
//
//    public String Encode(List<NameValuePair> Values) throws JSONException {
//        JSONObject obj = new JSONObject();
//
//        for (NameValuePair value : Values) {
//            obj.put(value.getName(), value.getValue());
//        }
//
//        /*
//        obj.put("name", "foo");
//        obj.put("num", new Integer(100));
//        obj.put("balance", new Double(1000.21));
//        obj.put("is_vip", new Boolean(true));
//
//        System.out.print(obj);
//        */
//
//        return obj.toString();
//    }
//}