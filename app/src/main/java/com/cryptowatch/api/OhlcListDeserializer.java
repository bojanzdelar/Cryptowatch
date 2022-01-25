package com.cryptowatch.api;

import com.cryptowatch.models.Ohlc;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class OhlcListDeserializer implements JsonDeserializer<List<Ohlc>> {
    @Override
    public List<Ohlc> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        List<Ohlc> ohlcList = new ArrayList<>();

        final JsonArray ohlcvArray = json.getAsJsonObject().getAsJsonObject("Data").getAsJsonArray("Data");

        for (JsonElement el : ohlcvArray) {
            final JsonObject ohlc = el.getAsJsonObject();

            ohlcList.add(new Ohlc(
                    ohlc.get("time").getAsLong(),
                    ohlc.get("open").getAsDouble(),
                    ohlc.get("high").getAsDouble(),
                    ohlc.get("low").getAsDouble(),
                    ohlc.get("close").getAsDouble()
            ));
        }

        return ohlcList;
    }
}
