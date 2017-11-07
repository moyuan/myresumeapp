package com.moon.yangzi.awesomeresume.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

/**
 * Created by yang on 11/5/2017.
 */

public class ModelUtils {

    private static Gson gsonForSerialization = new GsonBuilder()
            .registerTypeAdapter(Uri.class, new UriAdapter()).create();

    private static Gson gsonForDeserialization = new GsonBuilder()
            .registerTypeAdapter(Uri.class, new UriAdapter()).create();

    private static String PREF_NAME = "models";

    public static void save(Context context, String key, Object object) {
        SharedPreferences sp = context.getApplicationContext().getSharedPreferences(
                PREF_NAME, Context.MODE_PRIVATE);

        String jsonString = gsonForSerialization.toJson(object);
        sp.edit().putString(key, jsonString).apply();
    }

    public static <T> T read(Context context, String key, TypeToken<T> typeToken) {
        SharedPreferences sp = context.getApplicationContext().getSharedPreferences(
                PREF_NAME, Context.MODE_PRIVATE);
        try {
            return gsonForDeserialization.fromJson(sp.getString(key, ""), typeToken.getType());
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static class UriAdapter implements JsonDeserializer<Uri>, JsonSerializer<Uri> {
        @Override
        public Uri deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            return Uri.parse(json.getAsString());
        }

        @Override
        public JsonElement serialize(Uri src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.toString());
        }
    }
}
