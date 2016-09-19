package com.ladwa.aditya.bluemixsearch;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {


    @BindView(R.id.resultList)
    ListView mListView;

    @BindView(R.id.searchEditText)
    EditText mSearchEditText;

    @BindView(R.id.searchButton)
    Button mSearchButton;
    private Unbinder unbinder;
    private ArrayList<Person> mPersonList;
    private ArrayList<String> mStringArrayList;
    private ArrayAdapter<String> mArrayAdapter;
    private ArrayAdapter<Person> mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Bind view after Setting the layout
        unbinder = ButterKnife.bind(this);

    }


    @OnClick(R.id.searchButton)
    public void onClickSearch() {
        String name = mSearchEditText.getText().toString().trim();
        new BluemixApi().getUsers(name)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Model>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(MainActivity.this, "completed", Toast.LENGTH_SHORT).show();
                        mArrayAdapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1, mStringArrayList);
                        mListView.setAdapter(mArrayAdapter);
                        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                                intent.putExtra("person", mPersonList.get(i));
                                startActivity(intent);
                            }
                        });

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("ERROR", e.toString());
                    }

                    @Override
                    public void onNext(Model model) {
                        mPersonList = new ArrayList<Person>();
                        mStringArrayList = new ArrayList<String>();
                        for (int i = 0; i < Integer.valueOf(model.getTotal_rows()); i++) {
                            Person person = new Person();
                            person.setId(model.getRows().get(i).getId());
                            person.setName(model.getRows().get(i).getFields().getName());
                            person.setAge(model.getRows().get(i).getFields().getAge());
                            mStringArrayList.add(model.getRows().get(i).getFields().getName());
                            mPersonList.add(person);
                        }
                    }
                });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
    }
}
