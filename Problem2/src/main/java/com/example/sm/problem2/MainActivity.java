package com.example.sm.problem2;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    MyBaseAdapter adapter;
    ArrayList<Employee> emp_list = new ArrayList<Employee>();
    ListView listview;
    String name;
    int age, salary;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // need something here

        adapter = new MyBaseAdapter(this, emp_list);
        listview = (ListView) findViewById(R.id.listView1) ;
        listview.setAdapter(adapter);
        listview.setOnItemClickListener((AdapterView.OnItemClickListener)adapter);
    }
    @Override
    public void onClick(View v){
        EditText edit_name = (EditText) findViewById(R.id.edit_name);
        EditText edit_age = (EditText) findViewById(R.id.edit_age);
        EditText edit_salary = (EditText) findViewById(R.id.edit_salary);

        name = edit_name.getText().toString();
        age = Integer.parseInt(edit_age.getText().toString());
        salary = Integer.parseInt(edit_salary.getText().toString());

        Employee employee = (null);
        switch (v.getId()){
            case R.id.btn_inc:
                employee.increase();
                salary = employee.getSalary();
                break;

            case R.id.btn_dec:
                employee.decrease();
                salary = employee.getSalary();
                break;

            case R.id.btn_store:
                employee = new Employee(name , age, salary);
                emp_list.add(employee);
                break;

            case R.id.btn_modify:


                break;

            case R.id.btn_delete:
                name = edit_name.getText().toString();
                for (int i=0; i< emp_list.size(); i++){
                    if(emp_list.get(i).getName().equals(name)){
                        emp_list.remove(emp_list.indexOf(i));
                    }
                }
                break;
        }
    }
}
interface Payment {
    void increase();
    void decrease();
}
