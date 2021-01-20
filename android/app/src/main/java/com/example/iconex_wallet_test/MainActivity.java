package com.example.iconex_wallet_test;

import androidx.annotation.NonNull;
import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodChannel;

import org.json.JSONObject;
import com.google.gson.*;
import android.content.Context;

import android.widget.Toast;
import android.net.Uri;
import android.content.Intent;

public class MainActivity extends FlutterActivity {
  private static final String CHANNEL = "iconex";
  MethodChannel.Result myResult;

  public void bind() {
    Uri url = Uri.parse("iconex://bind");
    startActivityForResult(new Intent(Intent.ACTION_VIEW, url), 1000);
  }

  @Override
  public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
    super.configureFlutterEngine(flutterEngine);

    new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL)
        .setMethodCallHandler((call, result) -> {
          if (call.method.equals("getAddress")) {
            myResult = result;
            bind();
          }
        });
  }

  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    if (requestCode == 1000) {
      if (resultCode < 0) {
        Uri uri = data.getData();
        JsonObject response = new Gson().fromJson(uri.toString(), JsonObject.class);
        String message = response.get("message").getAsString();
        Toast.makeText(this, "Get response:" + resultCode + " : " + message, Toast.LENGTH_SHORT).show();
        myResult.success(message);
      } else {
        Uri uri = data.getData();
        JsonObject response = new Gson().fromJson(uri.toString(), JsonObject.class);
        String result = response.get("result").getAsString();
        myResult.success(result);
      }
    }
  }
}