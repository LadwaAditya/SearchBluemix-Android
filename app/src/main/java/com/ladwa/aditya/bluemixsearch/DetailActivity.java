package com.ladwa.aditya.bluemixsearch;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.tvName)
    TextView mName;

    @BindView(R.id.tvAge)
    TextView mAge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        Person person = (Person) getIntent().getParcelableExtra("person");

        mName.setText(person.getName());
        mAge.setText(person.getAge());
    }
}
