package com.example.ejercicios4_eco;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

public class MainActivity extends AppCompatActivity {

    private EditText IDInput1, IDInput2, IDInput3, IDInput4;
    private Button pingBtn, hostBtn;
    private TextView IPTextView;
  //  private String ipLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IDInput1 = findViewById(R.id.IDInput1);
        IDInput2 = findViewById(R.id.IDInput2);
        IDInput3 = findViewById(R.id.IDInput3);
        IDInput4 = findViewById(R.id.IDInput4);
        pingBtn = findViewById(R.id.pingBtn);
        hostBtn = findViewById(R.id.hostBtn);
        IPTextView = findViewById(R.id.IPTextView);

       // getHostIP();

        new Thread(

                ()->{
                    try {

                        Socket socket = new Socket();
                        socket.connect(new InetSocketAddress("google.com", 80));
                        InetAddress ipLocal = socket.getLocalAddress();
                        socket.close();

                        runOnUiThread(

                                () -> {
                                    IPTextView.setText(ipLocal.toString());
                                }
                        );

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

        ).start();



    }

    private void getHostIP() {



    }
}