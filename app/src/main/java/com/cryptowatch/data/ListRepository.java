package com.cryptowatch.data;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.cryptowatch.api.CryptoCompareService;
import com.cryptowatch.models.Currency;
import com.cryptowatch.utilities.Constants;
import com.cryptowatch.utilities.CurrencyDeserializer;
import com.cryptowatch.utilities.CurrencyMarketDeserializer;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListRepository {
    public MutableLiveData<List<Currency>> getAllCurrencies() {
        MutableLiveData<List<Currency>> data = new MutableLiveData<>();

        CryptoCompareService service = CryptoCompareService.Client.getService(
                new TypeToken<List<Currency>>() {}.getType(), new CurrencyDeserializer());

        Call<List<Currency>> call = service.getCurrencySummary(null);

        call.enqueue(new Callback<List<Currency>>() {
            @Override
            public void onResponse(Call<List<Currency>> call, Response<List<Currency>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Currency>> call, Throwable t) {
                Log.d("getCurrencySummary", t.getMessage());
            }
        });

        return data;
    }

    public MutableLiveData<List<Currency>> getCurrenciesByTopList() {
        MutableLiveData<List<Currency>> data = new MutableLiveData<>();

        CryptoCompareService service = CryptoCompareService.Client.getService(
                new TypeToken<List<Currency>>() {}.getType(), new CurrencyMarketDeserializer());

        Call<List<Currency>> call = service.getToplistByMarketCap(Constants.CONVERSION_CURRENCY, Constants.MARKET_COUNT);

        call.enqueue(new Callback<List<Currency>>() {
            @Override
            public void onResponse(Call<List<Currency>> call, Response<List<Currency>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Currency>> call, Throwable t) {
                Log.d("getToplistByMarketCap", t.getMessage());
            }
        });

        return data;
    }
}
