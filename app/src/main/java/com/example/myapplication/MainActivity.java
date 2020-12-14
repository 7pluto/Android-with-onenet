package com.example.myapplication;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends Activity {

    private TextView tv_temp;
    private TextView tv_hum;
    private TextView tv_light;
    private LinearLayout l3;
    private Button bt_1, bt_2;
    private TextView tv_1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_temp = findViewById(R.id.tv_temp);
        tv_hum = findViewById(R.id.tv_hum);
        tv_light = findViewById(R.id.tv_light);
        l3 = findViewById(R.id.l3);
        bt_1 = findViewById(R.id.bt_1);
        bt_2 = findViewById(R.id.bt_2);

        tv_hum.setText("32");
        tv_temp.setText("56");

        bt_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Click", "被点击了");
                Intent intent = new Intent(MainActivity.this,ColorPickerActivity.class);
                startActivity(intent);
                OneNETUrlConnectionPost t = new OneNETUrlConnectionPost();
                t.start();
            }
        });

        bt_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Click", "被点击了");
                OneNETHttpRequestGET t = new OneNETHttpRequestGET();
                t.start();
            }
        });
    }

    public class OneNETUrlConnectionPostByte extends Thread {

        private int SHOW_REQUEST = 0;
        private Handler handler;

        // 首先声明设备ID及apikey
        private static final String DeviceID = "655112498";
        // 个人使用就建议填产品key,设备key在二进制获取那里会权限不足
        private static final String ApiKey = "I1FHo=g0qy=Q2xzxT9=1ZKlxRtw=";

        @Override
        public void run() {

            URL url;
            // 自定义的字符数组将它上传到云端
            byte[] my_data = { '8', '8', '6' };
            HttpURLConnection connection;
            try {

                // 先new出一个URL对象，传入网络地址
                // 调用openConnection()方法获取到HttpURLConnection对象
                // 自己创建的中移物联网的地址http://api.heclouds.com/devices/25857699/datapoints?type=5
                url = new URL("https://api.heclouds.com/devices/" + DeviceID
                        + "/datapoints?type=5");
                connection = (HttpURLConnection) url.openConnection();
                Log.e("connet：", "连接成功！");
                // 下面使一些自由的定制，比如设置连接超时，读取超时的毫秒数，以及服务器希望得到的一些消息头
                connection.setConnectTimeout(8000);
                connection.setReadTimeout(8000);
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setRequestMethod("POST");
                connection.setRequestProperty("api-key", ApiKey);
                connection.setRequestProperty("Content-Length",
                        String.valueOf(my_data.length));
                connection.setChunkedStreamingMode(5);
                // 设置打开输出流
                connection.setDoOutput(true);
                // 拿到输出流
                OutputStream os = connection.getOutputStream();
                // 使用输出流往服务器提交数据
                os.write(my_data);
                os.flush();
                os.close();
                // //如果请求发送成功
                if (connection.getResponseCode() == 200) {

                    // 接下来利用输入流对数据进行读取
                    InputStream is = connection.getInputStream();
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(is));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        response.append(line);
                    }// 正常则返回{"errno":0,"error":"succ"}，此函数为void，用不上这个
                }

                // 最后关闭HTTP连接
                connection.disconnect();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }


    public class OneNETUrlConnectionPost extends Thread {


        private int SHOW_REQUEST = 0;
        private Handler handler;
        // 首先声明设备ID及apikey
        private static final String DeviceID = "655112498";
        // 个人使用就建议填产品key,设备key在二进制获取那里会权限不足
        private static final String ApiKey = "I1FHo=g0qy=Q2xzxT9=1ZKlxRtw=";

        @Override
        public void run() {
            Log.e("start：", "连接成功！");
            URL url;
            HttpURLConnection connection;
            try {
                //data1代表云端的数据流是data1
                String s1 = new String(",;" + "data1" + "," + "113");
                byte[] data = s1.getBytes();
                // 先new出一个URL对象，传入网络地址
                // 调用openConnection()方法获取到HttpURLConnection对象
                // 自己创建的中移物联网的地址http://api.heclouds.com/devices/25857699/datapoints?type=5
                url = new URL("https://api.heclouds.com/devices/" + DeviceID
                        + "/datapoints?type=5");
                connection = (HttpURLConnection) url.openConnection();
                Log.e("connet：", "连接成功！");
                // 下面使一些自由的定制，比如设置连接超时，读取超时的毫秒数，以及服务器希望得到的一些消息头
                connection.setConnectTimeout(8000);
                connection.setReadTimeout(8000);
                connection.setDoOutput(true);
                connection.setDoInput(true);
                connection.setRequestMethod("POST");
                connection.setRequestProperty("api-key", ApiKey);
                connection.setRequestProperty("Content-Length",
                        String.valueOf(data.length));
                connection.setChunkedStreamingMode(5);
                // 设置打开输出流
                connection.setDoOutput(true);
                // 拿到输出流
                OutputStream os = connection.getOutputStream();
                // 使用输出流往服务器提交数据
                os.write(data);
                os.flush();
                os.close();
                // //如果请求发送成功
                if (connection.getResponseCode() == 200) {

                    // 接下来利用输入流对数据进行读取
                    InputStream is = connection.getInputStream();
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(is));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        response.append(line);
                    }// 正常则返回{"errno":0,"error":"succ"}，此函数为void，用不上这个

                }

                // 最后关闭HTTP连接
                connection.disconnect();

            } catch (Exception e) {
                e.printStackTrace();
            }

        }

    }

    public class OneNETHttpRequestGET extends Thread {

        private int SHOW_REQUEST = 0;
        private Handler handler;
        // 首先声明设备ID及apikey
        private static final String DeviceID = "655112498";
        // 个人使用就建议填产品key,设备key在二进制获取那里会权限不足
        private static final String ApiKey = "I1FHo=g0qy=Q2xzxT9=1ZKlxRtw=";

        @Override
        public void run() {

            URL url;
            HttpURLConnection connection;

            tv_1 = findViewById(R.id.tv_1);
            try {
                // 先new出一个URL对象，传入网络地址
                // 调用openConnection()方法获取到HttpURLConnection对象
                url = new URL("https://api.heclouds.com/devices/" + DeviceID
                        + "/datastreams/" + "data1");
                connection = (HttpURLConnection) url.openConnection();
                Log.e("connet：", "连接成功！");
                // 下面使一些自由的定制，比如设置连接超时，读取超时的毫秒数，以及服务器希望得到的一些消息头
                connection.setConnectTimeout(15000);
                connection.setReadTimeout(15000);
                connection.setRequestMethod("GET");
                connection.setRequestProperty("api-key", ApiKey);

                // 如果网页正确响应
                if (connection.getResponseCode() == 200) {
                    // 接下来利用输入流对数据进行读取
                    InputStream is = connection.getInputStream();
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(is));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = br.readLine()) != null) {
                        response.append(line);
                    }
                    String jsonData = response.toString();
                    Log.e("jon:",jsonData);

                    JSONObject jsonObject1 = new JSONObject(jsonData);
                    JSONObject jsonObject2 = jsonObject1.getJSONObject("data");
                    String jsonSting = jsonObject2.getString("current_value");
                    Log.e("current_value:",jsonSting);
                    tv_1.setText(jsonSting);
                    /*JSONArray jsonArray = jsonObject1.getJSONArray("data");
                    Log.e("data:",jsonArray.toString());
                    for (int i=0; i < jsonArray.length(); i++)    {
                        JSONObject jsonObject2 = jsonArray.getJSONObject(i);
                        String current_value = jsonObject2.getString("current_value");
                        Log.e("current_value:",current_value);
                    }*/

                    /*Gson gson=new Gson();
                    MyObject obj = gson.fromJson(jon,MyObject.class);
                    Log.e("obj:",obj.getdata().get(0).getcreate_time());
                    connection.disconnect();*/
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

}