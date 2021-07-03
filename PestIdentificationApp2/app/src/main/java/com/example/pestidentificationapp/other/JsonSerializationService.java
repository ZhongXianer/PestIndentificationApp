package com.example.pestidentificationapp.other;

import android.content.Context;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.SerializationService;
import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.Gson;

import java.lang.reflect.Type;

@Route(path = "/test/json")
public class JsonSerializationService implements SerializationService {
    Gson gson;

    @Override
    public <T> T json2Object(String input, Class<T> clazz) {
        return gson.fromJson(input, clazz);
    }

    @Override
    public String object2Json(Object instance) {
        return gson.toJson(instance);
    }

    @Override
    public <T> T parseObject(String input, Type clazz) {
        return gson.fromJson(input, clazz);
    }

    @Override
    public void init(Context context) {
        gson = new Gson();
    }

    public static SerializationService getService() {
        return ARouter.getInstance().navigation(SerializationService.class);
    }
}
