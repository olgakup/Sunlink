package com.csun_sunlink.csuncareercenter.Search;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

import java.util.Calendar;

import com.csun_sunlink.csuncareercenter.R;

class SearchBgTask extends AsyncTask<String, Void, String> {
    private Context ctx;
    private View rootView;

    SearchBgTask(Context ctx, View rootView) {
        this.ctx = ctx;
        this.rootView = rootView;
    }

    @Override
    protected String doInBackground(String... params) {
        final String searchUrl = "http://10.0.2.2/CsunSunlink/search.php";
        String searchKey, result;
        searchKey = params[0];

        try {
            URL url = new URL(searchUrl);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            //send request
            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);
            OutputStream outputStream = httpURLConnection.getOutputStream();
            BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
            String data = URLEncoder.encode("jobTitle", "UTF-8") + "=" + URLEncoder.encode(searchKey, "UTF-8");
            bufferedWriter.write(data);
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStream.close();
            //get result
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder stringBuilder = new StringBuilder();
            while ((result = bufferedReader.readLine()) != null) {
                stringBuilder.append(result).append("\n");
            }
            bufferedReader.close();
            inputStream.close();
            httpURLConnection.disconnect();
            return stringBuilder.toString().trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String finalResult) {
        String companyStreet, companyStreet1, companyCityName, companyZipcode, companyState, companyCountry, jobId;
        String jobTitle, postedDate, companyName;
        ListView listView;
        try {
            JSONObject jsonObj = new JSONObject(finalResult);
            JSONArray jsonArray = jsonObj.getJSONArray("server_res");
            int count = 0;
            ItemAdapter itemAdapter;
            itemAdapter = new ItemAdapter(ctx, R.layout.row_layout);
            listView = (ListView) rootView.findViewById(R.id.search_result_list);
            listView.setAdapter(itemAdapter);
            while (count < jsonArray.length()) {
                JSONObject jsonObject = jsonArray.getJSONObject(count);
                jobTitle = jsonObject.getString("job_title");
                postedDate = jsonObject.getString("posted_date").trim();
                Calendar thatDay = Calendar.getInstance();
                Log.i("timeshow", "searchbgtask");
                Log.i("timeshow", postedDate.substring(3, 4));
                Log.i("timeshow", postedDate.substring(0, 1));
                Log.i("timeshow", postedDate.substring(6, 9));
                thatDay.set(Calendar.DAY_OF_MONTH, Integer.parseInt(postedDate.substring(3, 4)));
                thatDay.set(Calendar.MONTH, Integer.parseInt(postedDate.substring(0, 1))); // 0-11 so 1 less
                thatDay.set(Calendar.YEAR, Integer.parseInt(postedDate.substring(6, 9)));

                Calendar today = Calendar.getInstance();

                long diff = today.getTimeInMillis() - thatDay.getTimeInMillis();
                Log.i("timeshow", Long.toString(today.getTimeInMillis()));
                Log.i("timeshow", Long.toString(thatDay.getTimeInMillis()));

                long days = diff / (24 * 60 * 60 * 1000);

                companyName = jsonObject.getString("company_name");
                companyStreet = jsonObject.getString("company_street");
                companyStreet1 = jsonObject.getString("company_street1");
                companyCityName = jsonObject.getString("city_name");
                companyZipcode = jsonObject.getString("zipcode");
                companyState = jsonObject.getString("state_name");
                companyCountry = jsonObject.getString("country_name");
                jobId = jsonObject.getString("job_id");
                StringBuilder address = new StringBuilder();
                address.append(companyStreet).append(",");
                if (!companyStreet1.equals("null")) {
                    address.append(companyStreet1).append(",");
                }
                address.append(companyCityName).append(",");
                if (!companyState.equals("null")) {
                    address.append(companyState).append(",");

                }
                address.append("\n").append(companyCountry).append(",");
                address.append(companyZipcode).append(".");
                ItemInfo itemInfo = new ItemInfo(jobId, jobTitle, companyName, Long.toString(days), address.toString());
                itemAdapter.add(itemInfo);
                count++;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }
}
