package m.n.demotest.ui.home;

import android.app.Application;
import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;
import m.n.demotest.R;
import m.n.demotest.commons.BaseViewModel;
import m.n.demotest.config.Const;
import m.n.demotest.data.AppError;
import m.n.demotest.data.entity.remote.ConsolidatedWeather;
import m.n.demotest.domain.usecase.GetWeatherLocationUseCase;
import m.n.demotest.ui.home.ui_model.Cell;
import m.n.demotest.ui.home.ui_model.ColumnHeader;
import m.n.demotest.ui.home.ui_model.RowHeader;
import m.n.demotest.utils.DateUtils;

public class HomeFragmentViewModel extends BaseViewModel {
    public final String TAG = "HomeFragment";
    private ArrayList<ColumnHeader> _listColumnHeader;
    private final Application application;
    private final GetWeatherLocationUseCase getWeatherLocationUseCase;
    private final MutableLiveData<List<ConsolidatedWeather>> weatherList = new MutableLiveData<>();
    private final MutableLiveData<List<ColumnHeader>> columnHeader = new MutableLiveData<>();
    private final MutableLiveData<List<RowHeader>> rowHeader = new MutableLiveData<>();
    private final MutableLiveData<List<List<Cell>>> cellData = new MutableLiveData<>();
    private final MutableLiveData<Boolean> isInProgress = new MutableLiveData<>();

    @ViewModelInject
    public HomeFragmentViewModel(Application application, GetWeatherLocationUseCase getWeatherLocationUseCase) {
        this.getWeatherLocationUseCase = getWeatherLocationUseCase;
        this.application = application;
    }


    public LiveData<List<ConsolidatedWeather>> weatherLive() {
        return weatherList;
    }

    public LiveData<List<ColumnHeader>> columnHeaderLive() {
        return columnHeader;
    }

    public LiveData<List<RowHeader>> rowHeaderLive() {
        return rowHeader;
    }

    public LiveData<List<List<Cell>>> cellDataLive() {
        return cellData;
    }

    public LiveData<Boolean> isInProgressLive() {
        return isInProgress;
    }

    public void loadData() {
        Log.d(TAG, "load data");
        getDisposables().add(getWeatherLocationUseCase.execute(new GetWeatherLocationUseCase.Params(Const.KEY_SEARCH, DateUtils.yesterday()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe((i) -> isInProgress.postValue(true))
                .subscribeWith(new DisposableObserver<List<ConsolidatedWeather>>() {
                    @Override
                    public void onNext(@NonNull List<ConsolidatedWeather> weatherLocationResponses) {
                        mapToTableData(weatherLocationResponses);
                        isInProgress.postValue(false);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        doOnError(e);
                        isInProgress.postValue(false);
                    }

                    @Override
                    public void onComplete() {
                        isInProgress.postValue(false);
                    }
                }));
    }

    private void doOnError(Throwable throwable) {
        throwable.printStackTrace();
        postError(new AppError(0, throwable.getMessage()));
    }

    private void mapToTableData(List<ConsolidatedWeather> listData) {
        weatherList.postValue(listData);
        ArrayList<RowHeader> listRowHeader = new ArrayList<>();
        ArrayList<List<Cell>> listCell = new ArrayList<>();
        ArrayList<ColumnHeader> listColumnHeader = getListColumnHeader();
        String[] weatherColumnFields = application.getApplicationContext().getResources().getStringArray(R.array.weather_column_field);
        SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        for (ConsolidatedWeather item : listData) {
            Date date = null;
            String rowHeader = "";
            try {
                date = parser.parse(item.getCreated());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if (date != null) {
                rowHeader = formatter.format(date);
            }
            listRowHeader.add(new RowHeader(rowHeader));
            //make column data
            ArrayList<Cell> listCellColumn = new ArrayList<>();
            Gson gson = new Gson();
            String itemJson = gson.toJson(item);
            HashMap<String, Object> yourHashMap = gson.fromJson(itemJson, HashMap.class);
            for (String field : weatherColumnFields) {
                Object cellValue = yourHashMap.get(field);
                if (cellValue == null) {
                    cellValue = "";
                }
                Cell cell = new Cell(cellValue.toString());
                listCellColumn.add(cell);
            }
            listCell.add(listCellColumn);
        }
        rowHeader.postValue(listRowHeader);
        columnHeader.postValue(listColumnHeader);
        cellData.postValue(listCell);
    }


    private ArrayList<ColumnHeader> getListColumnHeader() {
        if (_listColumnHeader == null) {
            _listColumnHeader = createColumnHeaderList();
        }
        return _listColumnHeader;
    }

    private ArrayList<ColumnHeader> createColumnHeaderList() {
        ArrayList<ColumnHeader> listColumnHeader = new ArrayList<>();
        String[] weatherColumnsHeaders = application.getApplicationContext().getResources().getStringArray(R.array.weather_row);
        for (String item : weatherColumnsHeaders) {
            listColumnHeader.add(new ColumnHeader(item));
        }
        return listColumnHeader;
    }
}
