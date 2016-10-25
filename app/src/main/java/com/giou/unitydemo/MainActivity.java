package com.giou.unitydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.giou.unitydemo.model.AndroidToUnityModel;
import com.giou.unitydemo.model.ClientInfo;
import com.giou.unitydemo.ui.YoutuUnityPlayerActivity;
import com.giou.unitydemo.utils.ModelSelected;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity implements View.OnClickListener,LTNConstants,ModelSelected {

    private final String TAG = MainActivity.class.getSimpleName();
    private static final String INIT_PLAYER_COMMAND_ID = "initPlayer";
    private static final String SELECTED_SCENE_PARAM = "selectedScene";
    private static final String PLAYER_LIST_PARAM = "playerList";
    private List<ClientInfo> mClientInfoList = new ArrayList<>();
    private Gson mGson;
    private Button mJoin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGson = new Gson();

        mJoin = (Button) findViewById(R.id.btn_join);

        mJoin.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn_join:
                join3D();

                break;

        }

    }

    private void join3D() {
        if (YoutuUnityPlayerActivity.Instance != null) {
            YoutuUnityPlayerActivity.Instance.loadUnityScene(MainActivity.this);

        }


        Map<String, String> commandParams = new HashMap<>();

        commandParams.put(LTN_CURRENT_MODE, "0");
        commandParams.put(SELECTED_SCENE_PARAM, "1");

        for (int i = 1; i < 2; i++) {
            ClientInfo client = new ClientInfo();
            client.setClientId("大笨蛋立于天地_"+i);
            client.setTeamId(-1);
            mClientInfoList.add(client);
        }

        //第一次修改

//        for (int i = 0; i < 20; i++) {
//            ClientInfo client = new ClientInfo();
//            client.setClientId(i+"_号");
//            client.setTeamId(-1);
//            mClientInfoList.add(client);
//        }

        commandParams.put(PLAYER_LIST_PARAM, new Gson().toJson(mClientInfoList));

        AndroidToUnityModel unityModel = new AndroidToUnityModel();
        unityModel.setCommandId(INIT_PLAYER_COMMAND_ID);
        unityModel.setCommandParams(commandParams);

        String jsonString = mGson.toJson(unityModel);

        Log.d(TAG, "The initplayer json is : " + jsonString);

        if (YoutuUnityPlayerActivity.Instance != null) {
            YoutuUnityPlayerActivity.Instance.postMessageToUnity(jsonString);
        }

        start3D();

    }

    private void start3D() {

        for (int i = 0; i < mClientInfoList.size(); i++) {
            Map<String, String> commandParams2 = new HashMap<>();
//            commandParams2.put(LTN_PARAM_SPEED, (20+i) + "");
            commandParams2.put(LTN_PARAM_SPEED, 30 + "");
            commandParams2.put(LTN_COMMAND_CLINET_ID, mClientInfoList.get(i).getClientId());

            AndroidToUnityModel unityModel = new AndroidToUnityModel();
            unityModel.setCommandId(LTN_COMMAND_SPEED);
            unityModel.setCommandParams(commandParams2);

            String jsons = mGson.toJson(unityModel);
            if (YoutuUnityPlayerActivity.Instance != null) {
                YoutuUnityPlayerActivity.Instance.postMessageToUnity(jsons);
                Log.d(TAG, "学员机获取到:" + jsons);
            }
        }

    }

}
